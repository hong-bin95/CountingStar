package com.a201.countingstar.dto.spot;

import lombok.Getter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

// getter 설정해야 service 단에서 dto 속 entity를 가져올 수 있음.
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpotResponseDto {
    private int spotId;
    private String areaCode;
    private String latitude;
    private String longitude;
    private String spotName;
}
