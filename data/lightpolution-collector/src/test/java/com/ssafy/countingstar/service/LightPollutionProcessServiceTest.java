package com.ssafy.countingstar.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.countingstar.LightpollutionCollectorApplication;
import com.ssafy.countingstar.data.LightPollution;
import com.ssafy.countingstar.data.raw.SuomiNppViirsDnbData;
import com.ssafy.countingstar.service.data.util.SuomiNppViirsDnbDataDeserializer;
import com.ssafy.countingstar.service.processor.LightPollutionProcessorService;

@SpringBootTest(classes = LightpollutionCollectorApplication.class)
class LightPollutionProcessServiceTest {
	
	@Autowired
	LightPollutionProcessorService lpps;
	
	@Test
	void test() {
		
		FileInputStream fis;
		try {
			File file = new File("src/test/resource/testdata.nc");
			fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			byte[] data = bis.readAllBytes();
			SuomiNppViirsDnbData suomi = SuomiNppViirsDnbDataDeserializer.deserialize(data);
			
			for(LightPollution lp : lpps.process(List.of(suomi))) {
				assertTrue(lp.getRadiance() > 0);
			}

			
			bis.close();
			fis.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
