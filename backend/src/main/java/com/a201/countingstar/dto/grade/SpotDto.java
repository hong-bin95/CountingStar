package com.a201.countingstar.dto.grade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpotDto {
    private int spotId;
    private String spotName;
    private String latitude;
    private String longitude;
    private String areaCode;
}
