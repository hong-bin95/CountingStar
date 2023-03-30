package com.a201.countingstar.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConditionResponseDto {
    // 날씨 : 맑음, 흐림, 안개 등
    private String weatherCondition;
}
