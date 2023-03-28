package com.a201.countingstar.service;

import com.a201.countingstar.db.entity.ApiTest;
import com.a201.countingstar.db.repository.ApiTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiTestService {
    private final ApiTestRepository apiTestRepository;

    public List<ApiTest> getApiTests(){

        List<ApiTest> apiTests = apiTestRepository.findAll();
        return apiTests;
    }
}
