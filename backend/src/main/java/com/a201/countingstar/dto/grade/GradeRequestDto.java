package com.a201.countingstar.dto.grade;

import com.a201.countingstar.dto.search.SearchRequestDto;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeRequestDto extends SearchRequestDto {
    private @ApiParam(value = "기준년도(YYYY)", readOnly = true)    String baseDateYear;
    private @ApiParam(value = "기준월(MM)", readOnly = true)     String baseDateMonth;
    private @ApiParam(value = "기준일(dd)", readOnly = true)     String baseDateDay;
    private @ApiParam(value = "기준시간(HH)", readOnly = true)    String baseDateHour;
    private @ApiParam(value = "기준분(mm)", readOnly = true)     String baseDateMinute;
    private @ApiParam(value = "리턴데이터 갯수") int limit;
}
