package com.a201.countingstar.service.grade;

import com.a201.countingstar.dto.grade.GradeRequestDto;
import com.a201.countingstar.dto.grade.GradeResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    @Override
    public List<GradeResponseDto> getGradeList(GradeRequestDto request) {
        return null;
    }
}
