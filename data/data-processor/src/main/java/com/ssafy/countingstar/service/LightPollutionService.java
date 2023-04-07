package com.ssafy.countingstar.service;

import java.time.LocalDate;

import com.ssafy.countingstar.data.LightPollution;

import reactor.core.publisher.Flux;

public interface LightPollutionService {
	
	public Flux<LightPollution> getAllLightPollution(float lat, float lng);

}
