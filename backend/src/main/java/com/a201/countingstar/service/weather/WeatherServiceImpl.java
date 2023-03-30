package com.a201.countingstar.service.weather;

import com.a201.countingstar.common.CallAPI;
import com.a201.countingstar.db.entity.spot.Spot;
import com.a201.countingstar.db.repository.spot.SpotRepository;
import com.a201.countingstar.dto.moon.MoonApiResponseDto;
import com.a201.countingstar.dto.weather.ConditionResponseDto;
import com.a201.countingstar.dto.weather.DustResponseDto;
import com.a201.countingstar.dto.weather.WeatherApiDto;
import com.a201.countingstar.dto.weather.*;
import com.a201.countingstar.dto.weather.api.item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.RequiredArgsConstructor;
import nonapi.io.github.classgraph.json.JSONDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    @Value("${cstar.weather.key}")
    private String weatherKey;

    private final CallAPI call;

    private final SpotRepository spotRepository;


    @Override
    public ConditionResponseDto getCondition(String baseDateYear, String baseDateMonth, String baseDateDay, String baseDateHour , int spotId) throws Exception {

        /* 날씨 api의 경우 새벽 2시가 지나야 가능하다
        * 기준 시간은 1시간 전 (보통 30분에 발표나기 때문)
        *
        * 0시간 ~ 48시간 : 단기예보
        * 그 이상 : 중단기예보 (오전오후 나누어서)
        * */

        String date_str = String.format("%s-%s-%s %s",baseDateYear, baseDateMonth, baseDateDay, baseDateHour);
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        
        // 기준 시간
        Date date = transFormat.parse(date_str);

        date = addHoursToJavaUtilDate(date, -1);

        Date now = new Date();

        // 현재와 기준 시간의 차이 (단위 : 시간)
        long Hour = (date.getTime() - now.getTime()) / 3600000;


        // x, y 값 가져오기
        Spot selectSpot = spotRepository.findBySpotId(spotId);

        if(selectSpot != null){
            if(Hour <= 48){
                // 단기예보
                Map<String, String> map = new HashMap<>();

//                map.put("Content-type","application/json");

                String result = call.GET("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?numOfRows=900&pageNo=1&base_date=" + baseDateYear + baseDateMonth + baseDateDay + "&base_time=0200&dataType=JSON" +
                                "&nx=" + selectSpot.getX() + "&ny=" + selectSpot.getY() + "&serviceKey=" + weatherKey
                        ,map);

                System.out.println("api 호출 결과 : " + result);

                WeatherApiDto weather = getWeatherApiDto(result);

                List<item> basicWeatherList = new ArrayList<>();


                if(weather != null && (weather.getResponse()).getHeader().getResultCode().equals("00") ) {
                    // 정상 데이터
//                    List<item> totalItem = weather.getResponse().getBody().getItems().getItem();
//                    for(int i = 0 ; i < weather.getResponse().getBody().getItems().getItem().size(); i++){
                    for(item item : weather.getResponse().getBody().getItems().getItem()){
                        if(item.getFcstDate().equals(now.getYear() + now.getMonth() + now.getDay())
                        && item.getFcstTime().equals(baseDateHour + "00")){
                            basicWeatherList.add(item);
                        }
                    }

                    String pty ="" , sky = "";

                    if(basicWeatherList.size() > 0){
                        for(item item : basicWeatherList){
                            if(item.getFcstDate().equals(baseDateYear + baseDateMonth + baseDateDay)
                                    && item.getFcstTime().equals(baseDateHour + "00")){
                                // 눈이나 비
                                if(item.getCategory().equals("PTY")){
                                    pty = item.getFcstValue();
//                                        && item.getFcstValue().equals("0") == false){
                                    // 비나 눈 오면
//                                    if(item.getFcstValue().equals("1")){
//                                        return  new ConditionResponseDto("비");
//                                    }
//                                    else if(item.getFcstValue().equals("2")){
//                                        return  new ConditionResponseDto("비 혹은 눈");
//
//                                    }
//                                    else if(item.getFcstValue().equals("3")){
//                                        return  new ConditionResponseDto("눈");
//                                    }
//                                    else {
//                                        return  new ConditionResponseDto("소나기");
//                                    }
                                }
                                // 맑음(1), 구름많음(3), 흐림(4)
                                else if(item.getCategory().equals("SKY")){
                                    sky = item.getFcstValue();
//                                    if(item.getFcstValue().equals("1")){
//                                        return  new ConditionResponseDto("맑음");
//                                    }
//                                    else if(item.getFcstValue().equals("3")){
//                                        return  new ConditionResponseDto("구름많음");
//                                    }
//                                    else {
//                                        return  new ConditionResponseDto("흐림");
//                                    }
                                }
                            }
                        }

                        if(pty.equals("0") == false){
                            if(pty.equals("1")){
                                return  new ConditionResponseDto("비");
                            }
                            else if(pty.equals("2")){
                                return  new ConditionResponseDto("비 혹은 눈");

                            }
                            else if(pty.equals("3")){
                                return  new ConditionResponseDto("눈");
                            }
                            else {
                                return  new ConditionResponseDto("소나기");
                            }
                        }
                        else{
                            if(sky.equals("4")){
                                return  new ConditionResponseDto("흐림");
                            }
                            else if(sky.equals("3")){
                                return  new ConditionResponseDto("구름많음");
                            }
                            else {
                                return  new ConditionResponseDto("맑음");
                            }
                        }

                    }

                }

                System.out.println(weather);
            }
            else{
                // 중단기예보
            }
        }

        return null;
    }

    WeatherApiDto getWeatherApiDto(String result) throws JsonProcessingException {
        // mapper -> null 값으로 두면 nullpointexception 발생
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule());

        // 만드는 dto 형식을 맞춰야 함 ->
//        List<MoonApiResponseDto> dto = mapper.readValue(result.toString(),new ArrayList<MoonApiResponseDto>().getClass());
        WeatherApiDto dto = mapper.readValue(result.toString(),WeatherApiDto.class);
        return dto;
    }


    @Override
    public DustResponseDto getDust(String baseDateYear, String baseDateMonth, String baseDateDay, String baseDateHour , int spotId) throws Exception {

        /* 날씨 api의 경우 새벽 2시가 지나야 가능하다
         * 기준 시간은 1시간 전 (보통 30분에 발표나기 때문)
         *
         * 0시간 ~ 48시간 : 단기예보
         * 그 이상 : 중단기예보 (오전오후 나누어서)
         * */

        String date_str = String.format("%s-%s-%s %s",baseDateYear, baseDateMonth, baseDateDay, baseDateHour);
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        Date date = transFormat.parse(date_str);

        Date now = new Date();

        return null;
    }

    public Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
}
