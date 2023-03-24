package com.a201.countingstar.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DustResponseDto {
    // 미세먼지 : 좋음, 나쁨, 매우나쁨 등
    private String DustCondition;
}
