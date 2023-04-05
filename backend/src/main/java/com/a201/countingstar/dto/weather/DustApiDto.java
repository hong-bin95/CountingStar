package com.a201.countingstar.dto.weather;

import com.a201.countingstar.dto.weather.api.dustResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DustApiDto {
    private dustResponse response;
}
