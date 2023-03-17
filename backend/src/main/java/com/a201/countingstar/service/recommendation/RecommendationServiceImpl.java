package com.a201.countingstar.service.recommendation;

import com.a201.countingstar.db.repository.recommendation.RecommendationRepository;
import com.a201.countingstar.dto.recommendation.recommendationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor    // 생성자 주입
public class RecommendationServiceImpl implements RecommendationService{
    private final RecommendationRepository recommendationRepository;
    @Override
    public List<recommendationResponseDto> getRecommendationList() {
//        List<Recommendation>
        List<recommendationResponseDto> recommendationList = null; // =recommendationRepository.findAll();
        return null;
    }
}
