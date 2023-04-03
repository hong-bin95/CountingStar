package com.ssafy.countingstar.service;

import java.time.LocalDate;
import java.util.List;

import com.ssafy.countingstar.data.Atmosphere;

public interface AtmosphereService {
	
	public List<Atmosphere> getAllAtmosphere(LocalDate date, int hour);

}
