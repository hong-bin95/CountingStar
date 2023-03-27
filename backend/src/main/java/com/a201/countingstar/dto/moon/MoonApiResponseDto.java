package com.a201.countingstar.dto.moon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoonApiResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Error;

    private String ErrorMsg;

    private String TargetDate;

    private String[] Moon;

    private int Index;

    private String Age;

    private String Phase;

    private String Distance;

    private Float Illumination;

    private String AngularDiameter;

    private String DistanceToSun;

    private String SunAngularDiameter;


}
