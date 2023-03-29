package com.a201.countingstar.service.stargrade;

import com.a201.countingstar.dto.grade.GradeRequestDto;
import com.a201.countingstar.dto.grade.GradeResponseDto;

import java.util.List;

public interface StarGradeService {
    public List<GradeResponseDto> getGradeList(GradeRequestDto request);
}
