package com.ssafy.countingstar.service;

import java.util.List;

import com.ssafy.countingstar.data.Celestial;

import reactor.core.publisher.Flux;

public interface CelestialService {
	
	public List<Celestial> getAllCelestial();

}
