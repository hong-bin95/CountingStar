package com.a201.countingstar.controller;

import com.a201.countingstar.dto.weather.ConditionResponseDto;
import com.a201.countingstar.dto.weather.DustResponseDto;
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
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
@Api("날씨 컨트롤러")
public class WeatherController {
    private final WeatherService weatherService;
    @ApiOperation("날씨 상태 정보")
    @GetMapping("/condition")
    public ResponseEntity<?> getWeatherCondition(@ApiParam(value = "기준년도(YYYY)")    String baseDateYear,
                                                   @ApiParam(value = "기준월(MM)")     String baseDateMonth,
                                                   @ApiParam(value = "기준일(dd)")     String baseDateDay,
                                                   @ApiParam(value = "기준시간(HH)")    String baseDateHour,
                                                   @ApiParam(value = "스팟 번호 (spotId)") int spotId) {
        Map resultmap = new HashMap<>();
        HttpStatus status;

        try {
            ConditionResponseDto weater = weatherService.getCondition(baseDateYear,
                                                                    baseDateMonth,
                                                                    baseDateDay,
                                                                    baseDateHour,
                                                                    spotId);

            if (weater == null) {
                status = HttpStatus.NO_CONTENT;
            } else {
                resultmap.put("data", weater);
                status = HttpStatus.OK;
            }

        } catch (Exception e) {
            resultmap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map>(resultmap, status);
    }

    @ApiOperation("미세먼지 정보")
    @GetMapping("/dust")
    public ResponseEntity<?> getDust(@ApiParam(value = "기준년도(YYYY)")    String baseDateYear,
                                     @ApiParam(value = "기준월(MM)")     String baseDateMonth,
                                     @ApiParam(value = "기준일(dd)")     String baseDateDay,
                                     @ApiParam(value = "기준시간(HH)")    String baseDateHour,
                                     @ApiParam(value = "스팟 번호 (spotId)") int spotId) {
        Map resultmap = new HashMap<>();
        HttpStatus status;

        try {
            DustResponseDto dust = weatherService.getDust(baseDateYear,
                    baseDateMonth,
                    baseDateDay,
                    baseDateHour,
                    spotId);

            if (dust == null) {
                status = HttpStatus.NO_CONTENT;
            } else {
                resultmap.put("data", dust);
                status = HttpStatus.OK;
            }

        } catch (Exception e) {
            resultmap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map>(resultmap, status);
    }
}
