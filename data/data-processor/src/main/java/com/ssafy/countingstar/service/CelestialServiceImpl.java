package com.ssafy.countingstar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.countingstar.data.Celestial;
import com.ssafy.countingstar.repository.ReactiveCelestialRepository;

public class CelestialServiceImpl implements CelestialService{
	
	@Autowired
	ReactiveCelestialRepository celestialRepository;

	@Override
	public List<Celestial> getAllCelestial() {
		return celestialRepository.findAll().cache().collect(Collectors.toUnmodifiableList()).block();
	}

}
