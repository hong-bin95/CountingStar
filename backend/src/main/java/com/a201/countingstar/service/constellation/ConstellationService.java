package com.a201.countingstar.service.constellation;

import com.a201.countingstar.dto.constellation.ConstellationRankResponseDto;

import java.util.List;

public interface ConstellationService {
    List<ConstellationRankResponseDto> getConstellationRank(String baseDateYear, String baseDateMonth, int limit);
}
