package com.a201.countingstar.service.weather;

import com.a201.countingstar.common.CallAPI;
import com.a201.countingstar.dto.weather.ConditionResponseDto;
import com.a201.countingstar.dto.weather.DustResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    @Value("${cstar.weather.key}")
    private String weatherKey;

    private final CallAPI call;


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

        if(Hour <= 48){
            // 단기예보
            Map<String, String> map = new HashMap<>();

            map.put("Content-type","application/json");

            String result = call.GET("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"
                    ,map);

            System.out.println("api 호출 결과 : " + result);
        }
        else{
            // 중단기예보
        }

        return null;
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
