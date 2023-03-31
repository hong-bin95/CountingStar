package com.a201.countingstar.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.a201.countingstar.dto.weather.api.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherApiDto {
//    private object object;
    private response response;
}









