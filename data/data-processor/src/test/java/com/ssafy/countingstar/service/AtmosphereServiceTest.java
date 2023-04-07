package com.ssafy.countingstar.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.countingstar.DataProcessorApplication;
import com.ssafy.countingstar.data.Atmosphere;

@SpringBootTest(classes = DataProcessorApplication.class)
class AtmosphereServiceTest {
	
	@Autowired
	AtmosphereService atmosphereService;

	@Test
	void testGetAllAtmosphereByDateAndHour() {
		List<Atmosphere> list = atmosphereService.getAllAtmosphere(LocalDate.of(2023, 4, 2), 1).collectList().block();
		assertNotNull(list);
		assertTrue(list.size() >0);
	}

}
