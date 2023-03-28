package com.a201.countingstar.db.repository.grade;

import com.a201.countingstar.dto.grade.GradeRequestDto;
import com.a201.countingstar.dto.grade.GradeResponseDto;

import java.util.List;

public interface CustomGradeRepository {
    public List<GradeResponseDto> getGradeList(GradeRequestDto request);
}
