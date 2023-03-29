package com.a201.countingstar.controller;

import com.a201.countingstar.service.moon.MoonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.TypeMismatchException;
import org.springframework.http.*;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    public Object getMoonUrl(@PathVariable @ApiParam(value = "선택 날짜(YYYYMMDD)", required = true, example = "20230329")
                                     String select_date) throws Exception {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;

        // 잘못된 날짜인지 확인하기 위한 변수
        LocalDate localDate= LocalDate.parse(select_date, DateTimeFormatter.ofPattern("yyyyMMdd"));

        String moon = moonService.getMoonUrl(select_date);
        if (moon == null) {
            status = HttpStatus.NO_CONTENT;
        } else {
            resultMap.put("data", moon);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 예외처리 - 1) 날짜 형식이 아닌 경우
    @ExceptionHandler(ParseException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "invalid data type of select_date")
    public String handleParseException() {
        System.out.println("예외처리!!");
        return "invalidType/select_date";
    }

    // 예외처리 - 2) 잘못된 날짜인 경우
    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "invalid date of select_date")
    public String handleMissingPathVariableException() {
        System.out.println("잘못된 날짜 예외처리!!");
        return "invalidDate/select_date";
    }


}
