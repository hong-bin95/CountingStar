package com.a201.countingstar.dto.spotRanking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class spotRankingResponseDto {
    private int spotId;
    private String spotName;
    private int grade;
}
