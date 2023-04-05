package com.a201.countingstar.controller;

import com.a201.countingstar.common.CommonEnum;
import com.a201.countingstar.dto.grade.GradeRequestDto;
import com.a201.countingstar.dto.grade.GradeResponseDto;
import com.a201.countingstar.service.stargrade.StarGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("Grade RestController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/grade")
public class GradeController {

    private final StarGradeService gradeService;

    @ApiOperation("등급 리스트")
    @GetMapping("/")
    // 검색해서 해당하는 스팟을 다 찾고, 검색조건의 시간에 맞는 등급 정보를 스팟별로 내려준다.
    public ResponseEntity<?> getGrade(GradeRequestDto request) {
        Map resultmap = new HashMap<>();
        HttpStatus status;

        try {

            if(CommonEnum.SearchType.IsSearchTypeByCode(request.getSearchType()) == false){
                // 없는 타입으로 조회하는거면
                status = HttpStatus.BAD_REQUEST;
                resultmap.put("message", "유효하지 않은 검색조건입니다.");
            }
            else{
                List<GradeResponseDto> gradeList = gradeService.getGradeList(request);

                if (gradeList == null || gradeList.isEmpty()) {
                    status = HttpStatus.NO_CONTENT;
                } else {
                    resultmap.put("data", gradeList);
                    status = HttpStatus.OK;
                }
            }


        } catch (Exception e) {
            resultmap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map>(resultmap, status);
    }
}
