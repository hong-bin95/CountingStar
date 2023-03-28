package com.a201.countingstar.service.recommendation;

import com.a201.countingstar.common.CommonEnum;
import com.a201.countingstar.db.entity.recommendation.Recommendation;
import com.a201.countingstar.db.repository.recommendation.RecommendationRepository;
import com.a201.countingstar.dto.recommendation.RecommendationResponseDto;
import com.a201.countingstar.dto.recommendation.SpotDto;
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
            SpotDto spot;
            if(recommendation.getSpot_master() != null){
                spot = new SpotDto(
                        recommendation.getSpot_master().getSpotId(),
                        recommendation.getSpot_master().getLatitude(),
                        recommendation.getSpot_master().getLongitude(),
                        recommendation.getSpot_master().getAreaCode());
            }
            else{
                spot = null;
            }

            recommendationList.add(RecommendationResponseDto.builder()
                                        .recommendationId(recommendation.getRecommendationId())
                                        .title(recommendation.getTitle())
                                        .contents(recommendation.getContents())
                                        .type(CommonEnum.RecommendationContentsType.getValueByName(recommendation.getType()))
                                        .spot(spot)
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

        SpotDto spot;
        if(recommendationEntity.get().getSpot_master() != null){
            spot = new SpotDto(
                    recommendationEntity.get().getSpot_master().getSpotId(),
                    recommendationEntity.get().getSpot_master().getLatitude(),
                    recommendationEntity.get().getSpot_master().getLongitude(),
                    recommendationEntity.get().getSpot_master().getAreaCode());
        }
        else{
            spot = null;
        }

        RecommendationResponseDto recommendation =
                RecommendationResponseDto.builder()
                        .recommendationId(recommendationEntity.get().getRecommendationId())
                        .title(recommendationEntity.get().getTitle())
                        .contents(recommendationEntity.get().getContents())
                        .type(CommonEnum.RecommendationContentsType.getValueByName(recommendationEntity.get().getType()))
                        .spot(spot)
                        .build();

        return recommendation;
    }
}
