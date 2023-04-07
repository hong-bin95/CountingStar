package com.ssafy.countingstar.service;

import java.time.LocalDate;
import java.util.List;

import com.ssafy.countingstar.data.Atmosphere;

import reactor.core.publisher.Flux;

public interface AtmosphereService {
	
	public Flux<Atmosphere> getAllAtmosphere(LocalDate date, int hour);

}
