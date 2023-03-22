package com.ssafy.countingstar.data.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.ssafy.countingstar.data.raw.SuomiNppViirsDnbData;

import org.junit.jupiter.api.BeforeAll;

class LightPolutionDeserializerTest {
	
	private final String testUrl = "testdata.nc";
	
	private byte[] byteData;
	
	@BeforeAll
	void before() {
		// read testData.nc and convert to byte[]
		
	}

	@Test
	void test() {
		SuomiNppViirsDnbData sdata = LightPolutionDeserializers.deserialize(byteData);
	}

}
