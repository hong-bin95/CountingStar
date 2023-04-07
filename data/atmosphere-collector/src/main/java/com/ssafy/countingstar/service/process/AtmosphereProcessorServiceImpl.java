package com.ssafy.countingstar.service.process;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.countingstar.data.Atmosphere;
import com.ssafy.countingstar.data.CollectedDataKey;
import com.ssafy.countingstar.data.Observatory;
import com.ssafy.countingstar.data.raw.ASOSData;

@Service
public class AtmosphereProcessorServiceImpl implements AtmosphereProcessorService {

	@Override
	public List<Atmosphere> process(Map<Integer, List<ASOSData>> rawData, List<Observatory> observatories) {
		return observatories.stream()
			.map(x-> new Unit2(x.getLat(), x.getLng(), rawData.get(x.getCode())))
			.flatMap(x-> {
					List<Atmosphere> ats = new ArrayList<>();
					float lat = x.lat;
					float lng = x.lng;
					if(x.listOfASOS != null) {
						for(ASOSData asos : x.listOfASOS) {
							String[] strs = asos.getTm().split(" ");
							LocalDate date = LocalDate.parse(strs[0]);
							int hour = Integer.parseInt(strs[1].substring(0, 2));
							CollectedDataKey key = new CollectedDataKey(date, hour, lat, lng);
							ats.add(new Atmosphere(key, asos.getVs(), (float)asos.getSs()));
						}
					}
					return ats.stream();
				}
			)
			.collect(Collectors.toList());
	}	
}
