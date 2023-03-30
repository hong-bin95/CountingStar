package com.a201.countingstar.controller;

import com.a201.countingstar.common.CallAPI;
import com.a201.countingstar.dto.spotRanking.spotRankingResponseDto;
import com.a201.countingstar.service.spot.SpotService;
import com.a201.countingstar.dto.spot.SpotResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Api("Spot RestController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/spot")
public class SpotController {

    private final SpotService spotService;
    private final CallAPI call;

    @ApiOperation(value = "전체 스팟 조회", notes = "모든 스팟의 정보 반환")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getSpotList() {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {


            List<SpotResponseDto> spotList = spotService.getSpotAll();
            // 데이터 없을 때, 204 error 발생하도록 설정
            if (spotList.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            } else {
                // data로 묶어서 처리
                resultMap.put("data", spotList);
                status = HttpStatus.OK;
            }
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "스팟 상세 조회", notes = "해당 스팟의 상세 정보 반환")
    @GetMapping("/{spotId}")
    public ResponseEntity<Map<String, Object>> getSpotDetail(@PathVariable @ApiParam(value = "스팟 번호 (spotId)", required = true)
                                            String spotId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;

        SpotResponseDto spot = spotService.getSpotDetail(Integer.valueOf(spotId));
        if (spot == null) {
            status = HttpStatus.NO_CONTENT;
        } else {
            resultMap.put("data", spot);
            status = HttpStatus.OK;
        }
        status = HttpStatus.OK;

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 예외처리 - 1) 스팟 id가 잘못된 값인 경우
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "invalid value of spotId")
    public String handleTypeMismatchException() {
        System.out.println("스팟 id가 잘못된 값인 경우 예외처리!!!");
        return "invalidValue/spotId";
    }

    // 예외처리 - 2) 존재하지 않는 스팟 id인 경우
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "none of spotId")
    public String handleMissingPathVariableException() {
        System.out.println("존재하지 않는 스팟 예외처리!!!");
        return "noneValue/spotId";
    }


    @ApiOperation(value = "스팟 랭킹 조회", notes = "일자별 스팟의 순위 반환")
    @GetMapping("/ranking")
    public ResponseEntity<Map<String, Object>> getSpotRanking(@ApiParam(value = "기준년도(YYYY)", required = true)    String baseDateYear,
                                                              @ApiParam(value = "기준월(MM)", readOnly = true)     String baseDateMonth,
                                                              @ApiParam(value = "기준일(dd)", readOnly = true)     String baseDateDay,
                                                              @ApiParam(value = "기준시간(HH)", readOnly = true)    String baseDateHour,
                                                              @ApiParam(value = "기준분(mm)", readOnly = true)     String baseDateMinute,
                                                              @ApiParam(value = "리턴데이터 갯수") int limit) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {
            List<spotRankingResponseDto> spot = spotService.getSpotRanking( baseDateYear,
                                                                            baseDateMonth,
                                                                            baseDateDay,
                                                                            baseDateHour,
                                                                            baseDateMinute,
                    limit);
            if (spot == null || spot.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            } else {
                resultMap.put("data", spot);
                status = HttpStatus.OK;
            }
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
