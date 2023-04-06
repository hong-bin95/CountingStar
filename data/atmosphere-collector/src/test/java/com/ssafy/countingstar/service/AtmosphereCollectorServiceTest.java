package com.ssafy.countingstar.service;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.countingstar.AtmosphereCollectorApplication;
import com.ssafy.countingstar.service.collector.AtmosphereCollectorService;

@SpringBootTest(classes = AtmosphereCollectorApplication.class)
class AtmosphereCollectorServiceTest {
	
	@Autowired
	AtmosphereCollectorService atmosphereCollectorService;

	@Test
	void test() {
		
		
		int year = 2023;
		int month = 4;
		int day = 3;
		
		for(int i=0; i<=8; i++) {
			atmosphereCollectorService.collect(LocalDateTime.of(year, month, day, i, 0));
		}
		
		
		for(int i=18; i<=23; i++) {
			atmosphereCollectorService.collect(LocalDateTime.of(year, month, day, i, 0).minusDays(1));
		}
	}

}
