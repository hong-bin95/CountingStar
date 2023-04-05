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
    private SpotDto spot;
    // 별 다섯개중에 몇개인지
    private int grade;
}
