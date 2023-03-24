package com.a201.countingstar.service.weather;

import com.a201.countingstar.dto.weather.ConditionResponseDto;
import com.a201.countingstar.dto.weather.DustResponseDto;

public interface WeatherService {
    public ConditionResponseDto getCondition(String baseDateYear,
                                             String baseDateMonth,
                                             String baseDateDay,
                                             String baseDateHour,
                                             int spotId) throws Exception;
    public DustResponseDto getDust(String baseDateYear,
                                   String baseDateMonth,
                                   String baseDateDay,
                                   String baseDateHour,
                                   int spotId) throws Exception;
}
