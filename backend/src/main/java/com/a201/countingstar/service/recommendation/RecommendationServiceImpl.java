package com.a201.countingstar.service.recommendation;

import com.a201.countingstar.common.CommonEnum;
import com.a201.countingstar.db.entity.recommendation.Recommendation;
import com.a201.countingstar.db.repository.recommendation.RecommendationRepository;
import com.a201.countingstar.dto.recommendation.RecommendationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor    // 생성자 주입
public class RecommendationServiceImpl implements RecommendationService{
    private final RecommendationRepository recommendationRepository;
    @Override
    public List<RecommendationResponseDto> getRecommendationList() {
        List<Recommendation> recommendationEntityList = recommendationRepository.findAll();
        List<RecommendationResponseDto> recommendationList = new ArrayList<>();

//        CommonEnum.RecommendationContentsType type;
//        type = CommonEnum.RecommendationContentsType.getValueByName(1);

        recommendationEntityList.stream().forEach(recommendation -> {
            recommendationList.add(RecommendationResponseDto.builder()
                                        .recommendationId(recommendation.getRecommendationId())
                                        .title(recommendation.getTitle())
                                        .contents(recommendation.getContents())
                                        .type(CommonEnum.RecommendationContentsType.getValueByName(recommendation.getType()))
                                        .build());
        });

        return recommendationList;
    }

    @Override
    public RecommendationResponseDto getRecommendationDetail(int recommendationId) {
        // Optional은 null처리를 도와주는 Wrapper class다
        Optional<Recommendation> recommendationEntity = recommendationRepository.findById(recommendationId);

        if(recommendationEntity.isEmpty()){
            return null;
        }

        RecommendationResponseDto recommendation =
                RecommendationResponseDto.builder()
                        .recommendationId(recommendationEntity.get().getRecommendationId())
                        .title(recommendationEntity.get().getTitle())
                        .contents(recommendationEntity.get().getContents())
                        .type(CommonEnum.RecommendationContentsType.getValueByName(recommendationEntity.get().getType()))
                        .build();

        return recommendation;
    }
}
