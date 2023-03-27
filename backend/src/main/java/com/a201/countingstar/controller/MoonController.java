package com.a201.countingstar.controller;

import com.a201.countingstar.service.moon.MoonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api("Moon RestController")
@RestController
@RequestMapping("/moon")
public class MoonController {

    private final MoonService moonService;

    public MoonController(MoonService moonService) {
        this.moonService = moonService;
    }

    @ApiOperation(value = "달 위상 사진 url", notes = "해당 시간의 달 위상 사진 url 반환")
    @GetMapping("/{select_date}")
    public ResponseEntity<Map<String, Object>> getMoonUrl(@PathVariable @ApiParam(value = "선택한 날짜 (select_date)", required = true)
                                   String select_date) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {
            String moon = moonService.getMoonUrl(select_date);
            if (moon == null) {
                status = HttpStatus.NO_CONTENT;
            } else {
                resultMap.put("data", moon);
                status = HttpStatus.OK;
            }
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


}
