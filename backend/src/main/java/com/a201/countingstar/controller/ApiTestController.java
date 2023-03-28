package com.a201.countingstar.controller;

import com.a201.countingstar.db.entity.ApiTest;
import com.a201.countingstar.service.ApiTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/apitest")
@Api("테스트 컨트롤러")
public class ApiTestController {
    private final ApiTestService apiTestService;

    @ApiOperation("ApiTest 목록")
    @GetMapping("")
    public ResponseEntity<?> getApiTests(){

        Map resultmap = new HashMap<>();
        HttpStatus status;

        try {
            List<ApiTest> recvList = apiTestService.getApiTests();

            if (recvList.isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            } else {
                resultmap.put("data", recvList);
                status = HttpStatus.OK;
            }

        } catch (Exception e) {
            resultmap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map>(resultmap, status);
    }
}
