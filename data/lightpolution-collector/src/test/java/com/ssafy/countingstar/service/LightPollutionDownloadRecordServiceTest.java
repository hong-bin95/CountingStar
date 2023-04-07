package com.ssafy.countingstar.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.countingstar.LightpollutionCollectorApplication;

@SpringBootTest(classes = LightpollutionCollectorApplication.class)
class LightPollutionDownloadRecordServiceTest {
	
	@Autowired
	LightPollutionDownloadRecordService lpdr;

	@Test
	void test() {
		assertFalse(lpdr.isDownloaded(0));
	}

}
