package com.ssafy.countingstar.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.countingstar.data.Atmosphere;
import com.ssafy.countingstar.repository.ReactiveAtmosphereRepository;

import reactor.core.publisher.Flux;

public class AtmosphereServiceImpl implements AtmosphereService{
	
	@Autowired
	ReactiveAtmosphereRepository reactiveAtmosphereRepository;
	
	public Flux<Atmosphere> getAllAtmosphereByDateAndHour(LocalDate date, int hour){
		return reactiveAtmosphereRepository.findByDateAndHour(date, hour).cache();
	}

	@Override
	public List<Atmosphere> getAllAtmosphere(LocalDate date, int hour) {
		Flux<Atmosphere> flux = null;
		
		for(int dateD=0; dateD<7; dateD++) {
			for(int h=hour; h>=0; h--) {
				if(flux == null) {
					flux = getAllAtmosphereByDateAndHour(date,h);
				}else {
					flux = Flux.concat(flux, getAllAtmosphereByDateAndHour(date,h));
				}
			}
			date = date.minusDays(1);
			for(int h=23; h>hour; h--) {
				flux = Flux.concat(flux, getAllAtmosphereByDateAndHour(date,h));
			}
		}
		
		return flux.collect(Collectors.toUnmodifiableList()).block();
	}

}
