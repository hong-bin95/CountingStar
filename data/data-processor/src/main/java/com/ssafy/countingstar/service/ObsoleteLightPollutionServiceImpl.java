package com.ssafy.countingstar.service;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.data.ObsoleteLightPollution;
import com.ssafy.countingstar.repository.ObsoleteLightPollutionRepository;

import reactor.core.publisher.Flux;

@Service
public class ObsoleteLightPollutionServiceImpl implements ObsoleteLightPollutionService, Serializable {
	
	@Autowired
	ObsoleteLightPollutionRepository reactiveLightPollutionRepository;
	
	
	private Flux<ObsoleteLightPollution> getAllLightPollutionByDate(LocalDate date){
		return Flux.range(1, 12)
	            .flatMap(i -> reactiveLightPollutionRepository.findByDate(date, i))
	            .cache();
	}

	@Override
	public Flux<ObsoleteLightPollution> getAllLightPollution(LocalDate date) {
		
		// 전략 : 캐싱을 이용한다.
		getAllLightPollutionByDate(date);
		
		Flux<ObsoleteLightPollution> flux = getAllLightPollutionByDate(date);
		
		// 1주일 상한으로 과거 데이터를 가져오고 해당 데이터를 캐싱한다.	
		for(int i=1; i<7; i++) {
			date = date.minusDays(1);
			flux = Flux.concat(flux,getAllLightPollutionByDate(date));
		}
		return flux;
	}
		

}
