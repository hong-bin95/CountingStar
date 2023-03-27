package com.a201.countingstar.db.repository.constellation;

import com.a201.countingstar.dto.constellation.ConstellationRankResponseDto;

import java.util.List;

public interface CustomConstellationRepository {
    public List<ConstellationRankResponseDto> getConstellationRank(String baseDateYear, String baseDateMonth, int limit);

}
