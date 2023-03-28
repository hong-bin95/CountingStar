package com.a201.countingstar.service.spot;

import com.a201.countingstar.dto.spot.SpotResponseDto;
import com.a201.countingstar.dto.spotRanking.spotRankingResponseDto;

import java.util.List;

public interface SpotService {
    public List<SpotResponseDto> getSpotAll();
    public SpotResponseDto getSpotDetail(int spotId);
    public List<spotRankingResponseDto> getSpotRanking(String baseDateYear,
                                                       String baseDateMonth,
                                                       String baseDateDay,
                                                       String baseDateHour,
                                                       String baseDateMinute,
                                                       int limit);
}
