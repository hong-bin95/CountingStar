package com.a201.countingstar.service.stargrade;

import com.a201.countingstar.db.entity.spot.Spot;
import com.a201.countingstar.db.repository.spot.SpotRepository;
import com.a201.countingstar.db.repository.starGrade.StarGradeRepository;
import com.a201.countingstar.dto.grade.GradeRequestDto;
import com.a201.countingstar.dto.grade.GradeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StarGradeServiceImpl implements StarGradeService {
    private final StarGradeRepository gradeRepository;
    private final SpotRepository spotRepository;
    @Override
    public List<GradeResponseDto> getGradeList(GradeRequestDto request) {
//        List<Spot> starGradeEntityList = spotRepository.findBySpotNameContaining(request.getKeyword());

        return gradeRepository.getGradeList(request);
    }
}
