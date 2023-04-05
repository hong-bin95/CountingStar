package com.a201.countingstar.dto.search;

import com.a201.countingstar.common.CommonEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequestDto {
    private String searchType;
    private String keyword;
}
