package com.ssafy.countingstar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.data.Atmosphere;
import com.ssafy.countingstar.repository.AtmosphereRepository;

@Service
public class AtmosphereServiceImpl implements AtmosphereService {

	@Autowired
	AtmosphereRepository atmosphereRepository;
	
	@Override
	public void saveAll(Iterable<Atmosphere> asmospheres) {
		atmosphereRepository.insert(asmospheres);
	}

}
