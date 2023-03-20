package com.a201.countingstar.controller;

import com.a201.countingstar.dto.spot.spotRepositoryDto;
import com.a201.countingstar.db.entity.spot.Spot;
import com.a201.countingstar.service.spot.SpotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("Spot RestController")
@RestController
@RequestMapping("/spot")
public class SpotController {

    private SpotService spotService;

    private static final String MESSAGE = "message";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @ApiOperation(value = "전체 스팟 조회", notes = "모든 스팟의 정보 반환")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getSpotList() {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put(MESSAGE, SUCCESS);
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put(MESSAGE, FAIL);
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "스팟 상세 조회", notes = "해당 스팟의 상세 정보 반환")
    @GetMapping("/{spotId}")
    public ResponseEntity<Map<String, Object>> getSpot(@PathVariable @ApiParam(value = "스팟 번호 (spotId)", required = true)
                                            String spotId) {
        Map<String, Object> resultMap = new HashMap<>();

        return null;
    }

}
