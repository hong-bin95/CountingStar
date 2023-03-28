package com.a201.countingstar.dto.recommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpotDto {
    private int spotId;

    private String latitude;
    private String longitude;
    private String areaCode;
}
