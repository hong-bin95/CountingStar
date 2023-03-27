package com.a201.countingstar.dto.grade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GradeResponseDto {
    private int spotId;
    private String spotName;
    private int grade;
}
