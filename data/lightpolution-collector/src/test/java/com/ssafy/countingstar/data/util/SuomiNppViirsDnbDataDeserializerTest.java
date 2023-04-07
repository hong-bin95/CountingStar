package com.ssafy.countingstar.data.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import com.ssafy.countingstar.data.raw.SuomiNppViirsDnbData;
import com.ssafy.countingstar.service.data.util.SuomiNppViirsDnbDataDeserializer;

class SuomiNppViirsDnbDataDeserializerTest {
	
	@Test
	void test() {
		
		FileInputStream fis;
		try {
			File file = new File("src/test/resource/testdata.nc");
			fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			byte[] data = bis.readAllBytes();
			SuomiNppViirsDnbData suomi = SuomiNppViirsDnbDataDeserializer.deserialize(data);
			
			suomi.loadFromDataSet();
			
			assertNotNull(suomi);
			assertNotNull(suomi.getgRingPointLatitude());
			assertNotNull(suomi.getgRingPointLongitude());
			assertNotNull(suomi.getRadiance());
			assertNotNull(suomi.getRadiance()[0]);
			
			assertEquals(suomi.getgRingPointLatitude().length, 4);
			assertEquals(suomi.getgRingPointLongitude().length, 4);
			
			bis.close();
			fis.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
