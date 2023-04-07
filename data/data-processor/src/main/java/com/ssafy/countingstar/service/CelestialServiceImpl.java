package com.ssafy.countingstar.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.data.Celestial;
import com.ssafy.countingstar.repository.ReactiveCelestialRepository;

@Service
public class CelestialServiceImpl implements CelestialService, Serializable{
	
	@Autowired
	ReactiveCelestialRepository celestialRepository;

	@Override
	public List<Celestial> getAllCelestial() {
		return celestialRepository.findAll();
	}

}
