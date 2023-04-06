package com.ssafy.countingstar.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.countingstar.DataProcessorApplication;
import com.ssafy.countingstar.data.ObsoleteLightPollution;

@SpringBootTest(classes = DataProcessorApplication.class)
public class LightPollutionServiceTest {
	@Autowired
	ObsoleteLightPollutionService lightPollutionService;

	@Test
	void testGetAllLightPollution() {
		List<ObsoleteLightPollution> list = lightPollutionService.getAllLightPollution(LocalDate.of(2023, 4, 2)).collectList().block();
		assertNotNull(list);
		assertTrue(list.size() >0);
		System.out.println(list.get(0).getRadiance());
	}
}
