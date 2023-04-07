package com.ssafy.countingstar.service.process;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.countingstar.DataProcessorApplication;
import com.ssafy.countingstar.service.SpotService;
import com.ssafy.countingstar.service.processor.DataProcessorService;

@SpringBootTest(classes = DataProcessorApplication.class)
public class DataProcessorServiceTest {
	
	@Autowired
	DataProcessorService dataProcessorService;
	
	@Autowired
	SpotService spotService;
	
	@Test
	void testGetAllLightPollution() {
		spotService.publishAllSpots(LocalDate.of(2023, 03, 31), 0);
		
		dataProcessorService.runProcess();
	}

}
