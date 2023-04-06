package com.ssafy.countingstar.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.countingstar.DataProcessorApplication;
import com.ssafy.countingstar.data.LightPollution;
import com.ssafy.countingstar.data.LightPollutionKey;
import com.ssafy.countingstar.data.ObsoleteLightPollution;
import com.ssafy.countingstar.repository.ObsoleteLightPollutionRepository;
import com.ssafy.countingstar.repository.ReactiveLightPollutionRepository;

@SpringBootTest(classes = DataProcessorApplication.class)
public class LightPollutionMigrationTest {
	@Autowired
	ObsoleteLightPollutionRepository lightPollutionService1;
	
	@Autowired
	ReactiveLightPollutionRepository lightPollutionService2;

	@Test
	void testGetAllLightPollution() {
		
		for(int i=30; i<=31; i++) {
			for(int j=0; j<=23; j++) {
				LocalDate date = LocalDate.of(2023, 3, i);
				
				List<ObsoleteLightPollution> list = lightPollutionService1.findByDate(date,j).collectList().block();
				
				
				assertNotNull(list);
				
				for(ObsoleteLightPollution olp : list) {
					LightPollution lp = new LightPollution();
					lp.setKey(new LightPollutionKey(
							olp.getKey().getLat(),
							olp.getKey().getLng(),
							olp.getKey().getDate(),
							olp.getKey().getHour()
						));
					
					lightPollutionService2.save(lp).block();
				}
			}
		}
		
		
		

	}
}
