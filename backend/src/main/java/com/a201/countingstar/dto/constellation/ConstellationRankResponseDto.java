package com.a201.countingstar.dto.constellation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter @Setter
public class ConstellationRankResponseDto implements Serializable {
    private int ConstellationId;
    private String ConstellationName;
}
