package com.ssafy.countingstar.service.process;

import java.util.List;
import java.util.Map;

import com.ssafy.countingstar.data.Atmosphere;
import com.ssafy.countingstar.data.Observatory;
import com.ssafy.countingstar.data.raw.ASOSData;

public interface AtmosphereProcessorService {
	
	public List<Atmosphere> process(Map<Integer, List<ASOSData>> rawData, List<Observatory> observatories);

}
