package com.ssafy.countingstar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.countingstar.DataProcessorApplication;

@SpringBootTest(classes = DataProcessorApplication.class)
public class ReactiveSpotRepositoryTest {

    @Autowired
    private ReactiveSpotRepository reactiveSpotRepository;

    @Test
    void testFindAll() {
        reactiveSpotRepository.findAll();
    }

}