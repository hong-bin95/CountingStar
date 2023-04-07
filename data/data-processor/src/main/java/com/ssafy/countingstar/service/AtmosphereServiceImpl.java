package com.ssafy.countingstar.service;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.data.Atmosphere;
import com.ssafy.countingstar.repository.ReactiveAtmosphereRepository;

import reactor.core.publisher.Flux;

@Service
public class AtmosphereServiceImpl implements AtmosphereService, Serializable{
	
	@Autowired
	ReactiveAtmosphereRepository reactiveAtmosphereRepository;
	
	public Flux<Atmosphere> getAllAtmosphereByDateAndHour(LocalDate date, int hour){
		return reactiveAtmosphereRepository.findByDateAndHour(date, hour).cache();
	}

	@Override
	public Flux<Atmosphere> getAllAtmosphere(LocalDate date, int hour) {
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
		
		return flux;
	}

}
