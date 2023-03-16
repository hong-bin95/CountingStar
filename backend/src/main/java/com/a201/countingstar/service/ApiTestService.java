package com.a201.countingstar.service;

import com.a201.countingstar.entity.ApiTest;
import com.a201.countingstar.repository.ApiTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApiTestService {
    private final ApiTestRepository apiTestRepository;

    public List<ApiTest> getApiTests(){

        List<ApiTest> apiTests = apiTestRepository.findAll();
        return apiTests;
    }
}
