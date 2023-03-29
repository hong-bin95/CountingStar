package com.a201.countingstar.dto.grade;

import com.a201.countingstar.dto.search.SearchRequestDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "등급 조회")
public class GradeRequestDto extends SearchRequestDto {
//    @ApiImplicitParam(defaultValue = "2023")
    @Schema(description = "게시물 제목", defaultValue = "디폴트 제목", allowableValues = {"게시물1", "게시물2"}, example = "asdf")
    private @ApiParam(value = "기준년도(YYYY)", readOnly = true)    String baseDateYear;
    @Schema(description = "기준월(MM)", defaultValue = "03")
    private @ApiParam(value = "기준월(MM)", readOnly = true)     String baseDateMonth;
    @Schema(description = "기준일(dd)", defaultValue = "23")
    private @ApiParam(value = "기준일(dd)", readOnly = true)     String baseDateDay;
    @Schema(description = "기준시간(HH)", defaultValue = "11")
    private @ApiParam(value = "기준시간(HH)", readOnly = true)    String baseDateHour;
    @Schema(description = "기준분(mm)", defaultValue = "00")
    private @ApiParam(value = "기준분(mm)", readOnly = true)     String baseDateMinute;
    @Schema(description = "리턴데이터 갯수", defaultValue = "100")
    private @ApiParam(value = "리턴데이터 갯수") int limit;
}
