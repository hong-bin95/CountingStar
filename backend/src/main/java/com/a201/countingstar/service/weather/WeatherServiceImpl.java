package com.a201.countingstar.service.weather;

import com.a201.countingstar.dto.weather.ConditionResponseDto;
import com.a201.countingstar.dto.weather.DustResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
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
        Date date = transFormat.parse(date_str);

        Date now = new Date();

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
}
