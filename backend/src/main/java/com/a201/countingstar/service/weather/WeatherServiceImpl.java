package com.a201.countingstar.service.weather;

import com.a201.countingstar.common.CallAPI;
import com.a201.countingstar.db.entity.spot.Spot;
import com.a201.countingstar.db.repository.spot.SpotRepository;
import com.a201.countingstar.dto.moon.MoonApiResponseDto;
import com.a201.countingstar.dto.weather.ConditionResponseDto;
import com.a201.countingstar.dto.weather.DustResponseDto;
import com.a201.countingstar.dto.weather.WeatherApiDto;
import com.a201.countingstar.dto.weather.*;
import com.a201.countingstar.dto.weather.api.dustItem;
import com.a201.countingstar.dto.weather.api.item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
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
        transFormat = new SimpleDateFormat("yyyyMMdd");

        // 기준 시간

        // 현재와 기준 시간의 차이 (단위 : 시간)
        long Hour = (date.getTime() - now.getTime()) / 3600000;


        // x, y 값 가져오기
        Spot selectSpot = spotRepository.findBySpotId(spotId);

        if(selectSpot != null){
            if(Hour <= 48){
                // 단기예보
                Map<String, String> map = new HashMap<>();

                String result = call.GET("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?numOfRows=900&pageNo=1&base_date=" + transFormat.format(now) + "&base_time=0200&dataType=JSON" +
                                "&nx=" + selectSpot.getX() + "&ny=" + selectSpot.getY() + "&serviceKey=" + weatherKey
                        ,map);

                 System.out.println("api 호출 결과 : " + result);

                WeatherApiDto weather = getWeatherApiDto(result);

                List<item> basicWeatherList = new ArrayList<>();


                if(weather != null && (weather.getResponse()).getHeader().getResultCode().equals("00") ) {
                    // 정상 데이터
                    for(item item : weather.getResponse().getBody().getItems().getItem()){
                        if(item.getFcstDate().equals(baseDateYear + baseDateMonth + baseDateDay)
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
                                }
                                // 맑음(1), 구름많음(3), 흐림(4)
                                else if(item.getCategory().equals("SKY")){
                                    sky = item.getFcstValue();
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
                now = addHoursToJavaUtilDate(now, -24);
                // 중단기예보
                String result = call.GET("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst?serviceKey=" +
                               weatherKey + "&numOfRows=1000&pageNo=1&regId=" + selectSpot.getLocationCode() + "&tmFc=" + transFormat.format(now) + "1800&dataType=JSON"
                        ,null);

                System.out.println("api 호출 결과 : " + result);

                WeatherApiDto weather = getWeatherApiDto(result);
                String wf = "";

                if(weather.getResponse().getHeader().getResultCode().equals("00")){
                    transFormat = new SimpleDateFormat("yyyy-MM-dd HH");
                    date = transFormat.parse(date_str);
                    Hour = (date.getTime() - (new Date()).getTime()) / 3600000; // 시간 차이

                    // 3일 :  ~ 72시간
                    if(Hour <= 72){
                        wf = weather.getResponse().getBody().getItems().getItem().get(0).getWf3Pm();
                    }
                    // 4일 : ~ 96시간
                    else if(Hour <= 96){
                        wf = weather.getResponse().getBody().getItems().getItem().get(0).getWf4Pm();
                    }
                    // 5일 : ~ 120시간
                    else if(Hour <= 120){
                        wf = weather.getResponse().getBody().getItems().getItem().get(0).getWf5Pm();
                    }
                    // 6일 : ~ 144시간
                    else if(Hour <= 144){
                        wf = weather.getResponse().getBody().getItems().getItem().get(0).getWf6Pm();
                    }
                    // 그 외
                    else{
                        wf = weather.getResponse().getBody().getItems().getItem().get(0).getWf7Pm();
                    }


                    /*
                    *
                    * - 맑음
                    * - 구름많음, 구름많고 비, 구름많고 눈, 구름많고 비/눈, 구름많고 소나기
                    * - 흐림, 흐리고 비, 흐리고 눈, 흐리고 비/눈, 흐리고 소나기
                    */
                    //비/비 혹은 눈/눈/소나기/흐림/구름많음/맑음
                    if(wf.equals("비") || wf.equals("비 혹은 눈") || wf.equals("눈") || wf.equals("소나기") || wf.equals("흐림")
                            || wf.equals("구름많음")|| wf.equals("맑음")){
                        return new ConditionResponseDto(wf);                                
                    }
                    else if(wf.equals("구름많고 비") || wf.equals("흐리고 비")){
                        return new ConditionResponseDto("비");
                    }
                    else if(wf.equals("구름많고 소나기") ||  wf.equals("흐리고 소나기")){
                        return new ConditionResponseDto("소나기");
                    }
                    else if(wf.equals("구름많고 눈") || wf.equals("흐리고 눈") ){
                        return new ConditionResponseDto("눈");
                    }
                    else if(wf.equals("구름많고 비/눈") || wf.equals("흐리고 비/눈") ){
                        return new ConditionResponseDto("비 혹은 눈");
                    }

                }
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
        Date now = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

        String result = call.GET("https://api.odcloud.kr/api/MinuDustFrcstDspthSvrc/v1/getMinuDustFrcstDspth?numOfRows=100&" +
                "searchDate=" + transFormat.format(now) + "&ver=1.1" +
                "&serviceKey=" + weatherKey +"&returnType=json",null);
        System.out.println("api 호출 결과 : " + result);

        DustApiDto dust = getDustApiDto(result);
        Spot selectSpot = spotRepository.findBySpotId(spotId);

        if(selectSpot != null && dust != null && dust.getResponse().getBody().getItems() != null){
            for(dustItem item : dust.getResponse().getBody().getItems()){
                if(item.getInformData().equals(baseDateYear + "-" + baseDateMonth + "-" + baseDateDay)){

                    //서울 : 나쁨,제주 : 보통,전남 : 보통,전북 : 나쁨,광주 : 보통,경남 : 보통,경북 : 보통,울산 : 보통,대구 : 보통,부산 : 보통,충남 : 나쁨,충북 : 보통,세종 : 보통,대전 : 보통,영동 : 보통,영서 : 보통,경기남부 : 나쁨,경기북부 : 나쁨,인천 : 나쁨
                    String[] dustList = item.getInformGrade().split(",");
                    if(dustList.length > 0){
                        for(String dustGrade : dustList){
                            String[] gradeItems = dustGrade.split(":");
                            System.out.println(Arrays.toString(gradeItems));
                            if(gradeItems[0].contains(selectSpot.getLocationName())){
                                return new DustResponseDto(gradeItems[1]);
                            }
                        }
                    }
                    //                    return new DustResponseDto()
                }
            }
        }

        return null;
    }

    DustApiDto getDustApiDto(String result) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule());

        DustApiDto dto = mapper.readValue(result.toString(),DustApiDto.class);
        return dto;
    }

    public Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
}
