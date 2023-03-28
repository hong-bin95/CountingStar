package com.a201.countingstar.controller;

import com.a201.countingstar.dto.recommendation.RecommendationResponseDto;
import com.a201.countingstar.service.recommendation.RecommendationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommendation")
@Api("추천 컨트롤러")
public class RecommendationController {
    private final RecommendationService recommendationService;
    @ApiOperation("추천 컨텐츠 목록")
    @GetMapping()
    public ResponseEntity<?> getRecommendationList() {
        Map resultmap = new HashMap<>();
        HttpStatus status;

        try {
            List<RecommendationResponseDto> recommendationList = recommendationService.getRecommendationList();

            if (recommendationList.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            } else {
                resultmap.put("data", recommendationList);
                status = HttpStatus.OK;
            }

        } catch (Exception e) {
            resultmap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map>(resultmap, status);
    }

    @ApiOperation("추천 컨텐츠 상세")
    @GetMapping("/{recommendationId}")
    public ResponseEntity<?> getRecommendationDetail(@PathVariable @ApiParam(value = "추천 컨텐츠 번호", required = true) int recommendationId) {
        Map resultmap = new HashMap<>();
        HttpStatus status;

        try {
            RecommendationResponseDto recommendation = recommendationService.getRecommendationDetail(recommendationId);

            if (recommendation == null) {
                status = HttpStatus.NO_CONTENT;
            } else {
                resultmap.put("data", recommendation);
                status = HttpStatus.OK;
            }

        } catch (Exception e) {
            resultmap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map>(resultmap, status);
    }
}
