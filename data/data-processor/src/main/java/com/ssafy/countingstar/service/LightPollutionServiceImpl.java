package com.ssafy.countingstar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.data.LightPollution;
import com.ssafy.countingstar.data.LightPollutionKey;
import com.ssafy.countingstar.repository.ReactiveLightPollutionRepository;

import reactor.core.publisher.Flux;

@Service
public class LightPollutionServiceImpl implements LightPollutionService{
	
	@Autowired
	ReactiveLightPollutionRepository reactiveLightPollutionRepository;

	@Override
	public Flux<LightPollution> getAllLightPollution(float lat, float lng) {		
		return reactiveLightPollutionRepository.findBySlot(LightPollutionKey.calculateSlot(lat, lng));
	}

}
