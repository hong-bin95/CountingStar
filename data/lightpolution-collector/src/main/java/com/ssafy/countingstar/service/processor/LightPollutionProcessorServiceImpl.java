package com.ssafy.countingstar.service.processor;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;

import com.ssafy.countingstar.data.LightPollution;
import com.ssafy.countingstar.data.raw.SuomiNppViirsDnbData;

public class LightPollutionProcessorServiceImpl implements LightPollutionProcessorService{
	
	
	static SparkConf conf = new SparkConf();
	
	@Value("${earthdata-token}")
	static String host;
	
	static String username;
	
	static String password;
	
	static {
		conf
		.setAppName("Light-Pollution-Processor");
	}
	
	static SparkSession sparkSession;

	@Override
	public Iterable<LightPollution> process(SuomiNppViirsDnbData rawData) {
		return null;
	}

}
