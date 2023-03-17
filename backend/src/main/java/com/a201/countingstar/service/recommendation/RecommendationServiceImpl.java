package com.a201.countingstar.service.recommendation;

import com.a201.countingstar.common.CommonEnum;
import com.a201.countingstar.db.entity.recommendation.Recommendation;
import com.a201.countingstar.db.repository.recommendation.RecommendationRepository;
import com.a201.countingstar.dto.recommendation.recommendationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor    // 생성자 주입
public class RecommendationServiceImpl implements RecommendationService{
    private final RecommendationRepository recommendationRepository;
    @Override
    public List<recommendationResponseDto> getRecommendationList() {
        List<Recommendation> recommendationEntityList = recommendationRepository.findAll();
        List<recommendationResponseDto> recommendationList = new ArrayList<>();

//        CommonEnum.RecommendationContentsType type;
//        type = CommonEnum.RecommendationContentsType.getValueByName(1);

        recommendationEntityList.stream().forEach(recommendation -> {
            recommendationList.add(recommendationResponseDto.builder()
                                        .recommendationId(recommendation.getRecommendationId())
                                        .title(recommendation.getTitle())
                                        .contents(recommendation.getContents())
                                        .type(CommonEnum.RecommendationContentsType.getValueByName(recommendation.getType()))
                                        .build());
        });

        return recommendationList;
    }
}
