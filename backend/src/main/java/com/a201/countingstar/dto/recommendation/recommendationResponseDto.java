package com.a201.countingstar.dto.recommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;

@Getter
@Setter
@AllArgsConstructor
public class recommendationResponseDto {
    private int recommendationId;

    private String title;
    @Lob
    private String contents;
}
