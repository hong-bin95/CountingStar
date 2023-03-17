package com.a201.countingstar.service.recommendation;

import com.a201.countingstar.dto.recommendation.recommendationResponseDto;

import java.util.List;

public interface RecommendationService {
    public List<recommendationResponseDto> getRecommendationList();
}
