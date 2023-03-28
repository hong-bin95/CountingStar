package com.a201.countingstar.dto.recommendation;

import com.a201.countingstar.common.CommonEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationResponseDto {
    private int recommendationId;
    private String title;
    private String contents;

    @Nullable
    private CommonEnum.RecommendationContentsType type;
    @Nullable
    private SpotDto spot;
}
