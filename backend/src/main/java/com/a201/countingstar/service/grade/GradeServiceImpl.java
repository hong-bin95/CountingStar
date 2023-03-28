package com.a201.countingstar.service.grade;

import com.a201.countingstar.db.entity.spot.Spot;
import com.a201.countingstar.db.entity.star.StarGrade;
import com.a201.countingstar.db.repository.grade.GradeRepository;
import com.a201.countingstar.db.repository.spot.SpotRepository;
import com.a201.countingstar.dto.grade.GradeRequestDto;
import com.a201.countingstar.dto.grade.GradeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final SpotRepository spotRepository;
    @Override
    public List<GradeResponseDto> getGradeList(GradeRequestDto request) {
        List<Spot> starGradeEntityList = spotRepository.findBySpotNameContaining(request.getKeyword());
        return null;
    }
}
