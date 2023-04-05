package com.a201.countingstar.dto.weather.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class dustBody {
    private String dataType;
    private List<dustItem> items;
    private String pageNo;
    private String numOfRows;
    private String totalCount;

}
