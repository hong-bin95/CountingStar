package com.a201.countingstar.dto.spot;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class spotRepositoryDto {
    private int spotId;
    private String areaCode;
    private String latitude;
    private String longitude;
}
