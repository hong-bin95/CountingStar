package com.a201.countingstar.dto.recommendation;

import com.a201.countingstar.common.CommonEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import reactor.util.annotation.Nullable;

import javax.persistence.Lob;


@Builder
@Getter
@AllArgsConstructor
public class recommendationResponseDto {
    private int recommendationId;
    private String title;
    private String contents;

    @Nullable
    private CommonEnum.RecommendationContentsType type;
}
