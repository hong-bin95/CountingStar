package com.a201.countingstar.service.recommendation;

import com.a201.countingstar.dto.recommendation.RecommendationResponseDto;

import java.util.List;

public interface RecommendationService {
    public List<RecommendationResponseDto> getRecommendationList();
    public RecommendationResponseDto getRecommendationDetail(int recommendationId);
}
