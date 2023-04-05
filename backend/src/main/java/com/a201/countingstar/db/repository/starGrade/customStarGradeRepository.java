package com.a201.countingstar.db.repository.starGrade;

import com.a201.countingstar.dto.grade.GradeRequestDto;
import com.a201.countingstar.dto.grade.GradeResponseDto;
import com.a201.countingstar.dto.spotRanking.spotRankingResponseDto;

import java.util.List;

public interface customStarGradeRepository {
    List<spotRankingResponseDto> getSpotRanking(String baseDateYear,
                                                String baseDateMonth,
                                                String baseDateDay,
                                                String baseDateHour,
                                                String baseDateMinute,
                                                int number);


    List<GradeResponseDto> getGradeList(GradeRequestDto request);
}
