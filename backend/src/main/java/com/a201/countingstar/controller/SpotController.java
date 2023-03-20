package com.a201.countingstar.controller;

import com.a201.countingstar.service.spot.SpotService;
import com.a201.countingstar.dto.spot.SpotResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("Spot RestController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/spot")
public class SpotController {

    private final SpotService spotService;

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
        try {
            SpotResponseDto spot = spotService.getSpotDetail(Integer.valueOf(spotId));
            if (spot == null) {
                status = HttpStatus.NO_CONTENT;
            } else {
                resultMap.put("data", spot);
                status = HttpStatus.OK;
            }
            status = HttpStatus.OK;
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
