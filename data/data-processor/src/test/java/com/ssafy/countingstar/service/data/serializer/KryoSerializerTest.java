package com.ssafy.countingstar.service.data.serializer;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.countingstar.DataProcessorApplication;
import com.ssafy.countingstar.dto.TimeWithSpot;
import com.ssafy.countingstar.dto.serializer.TimeWithSpotDeserializer;
import com.ssafy.countingstar.dto.serializer.TimeWithSpotSerializer;
import com.ssafy.countingstar.repository.ReactiveSpotRepository;

@SpringBootTest(classes = DataProcessorApplication.class)
public class KryoSerializerTest {
	
	@Autowired
	ReactiveSpotRepository reactiveSpotRepository;
	
	@Test
	void testGetAllLightPollution() {
		TimeWithSpotSerializer s = new TimeWithSpotSerializer();
		TimeWithSpotDeserializer d = new TimeWithSpotDeserializer();
		
		TimeWithSpot tws = new TimeWithSpot(3,1.1f,1.1f, LocalDate.of(2022, 2, 22),10);
		byte[] b = s.serialize("abc", tws);
		TimeWithSpot tws2 = d.deserialize("abc", b);
		System.out.println(tws2.getDate());
		System.out.println(tws2.getHour());
	}

}
