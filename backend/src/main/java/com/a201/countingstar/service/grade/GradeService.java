package com.a201.countingstar.service.grade;

import com.a201.countingstar.dto.grade.GradeRequestDto;
import com.a201.countingstar.dto.grade.GradeResponseDto;

import java.util.List;

public interface GradeService {
    public List<GradeResponseDto> getGradeList(GradeRequestDto request);
}
