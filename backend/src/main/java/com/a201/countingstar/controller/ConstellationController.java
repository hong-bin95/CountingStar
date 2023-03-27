package com.a201.countingstar.controller;


import com.a201.countingstar.dto.constellation.ConstellationRankResponseDto;
import com.a201.countingstar.dto.weather.ConditionResponseDto;
import com.a201.countingstar.service.constellation.ConstellationService;
import com.a201.countingstar.service.weather.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/constellation")
@Api("별자리 컨트롤러")
public class ConstellationController {
    private final ConstellationService constellation;
    @ApiOperation("별자리 랭킹")
    @GetMapping("/rank")
    public ResponseEntity<?> getWeatherCondition(@ApiParam(value = "기준년도(YYYY)") String baseDateYear,
                                                 @ApiParam(value = "기준월(MM)") String baseDateMonth,
                                                 @ApiParam(value = "갯수") int limit) {
        Map resultmap = new HashMap<>();
        HttpStatus status;

        try {
            List<ConstellationRankResponseDto> rank = constellation.getConstellationRank(baseDateYear,
                                                                                            baseDateMonth,
                                                                                            limit);

            if (rank.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            } else {
                resultmap.put("data", rank);
                status = HttpStatus.OK;
            }

        } catch (Exception e) {
            resultmap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map>(resultmap, status);
    }
}
