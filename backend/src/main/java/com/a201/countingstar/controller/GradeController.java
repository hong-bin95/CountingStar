package com.a201.countingstar.controller;

import com.a201.countingstar.dto.constellation.ConstellationRankResponseDto;
import com.a201.countingstar.dto.grade.GradeRequestDto;
import com.a201.countingstar.dto.grade.GradeResponseDto;
import com.a201.countingstar.service.grade.GradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("Grade RestController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/grade")
public class GradeController {

    private final GradeService gradeService;

    @ApiOperation("등급 리스트")
    @GetMapping("/")
    public ResponseEntity<?> getWeatherCondition(GradeRequestDto request) {
        Map resultmap = new HashMap<>();
        HttpStatus status;

        try {
            List<GradeResponseDto> gradeList = gradeService.getGradeList(request);

            if (gradeList.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            } else {
                resultmap.put("data", gradeList);
                status = HttpStatus.OK;
            }

        } catch (Exception e) {
            resultmap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map>(resultmap, status);
    }
}
