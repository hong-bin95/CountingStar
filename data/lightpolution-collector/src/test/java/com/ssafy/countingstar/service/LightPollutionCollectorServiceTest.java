package com.ssafy.countingstar.service;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.countingstar.LightpollutionCollectorApplication;
import com.ssafy.countingstar.service.collector.LightPollutionCollectorService;

@SpringBootTest(classes = LightpollutionCollectorApplication.class)
class LightPollutionCollectorServiceTest {
	
	@Autowired
	LightPollutionCollectorService lpcs;

	@Test
	void test() {
		lpcs.collect(LocalDateTime.now(ZoneId.of("UTC")));
	}

}
