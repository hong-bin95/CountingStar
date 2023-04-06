package com.ssafy.countingstar.service;

import java.time.LocalDate;

import com.ssafy.countingstar.data.LightPollution;
import com.ssafy.countingstar.data.ObsoleteLightPollution;

import reactor.core.publisher.Flux;

public interface ObsoleteLightPollutionService {
	
	public Flux<ObsoleteLightPollution> getAllLightPollution(LocalDate date);

}
