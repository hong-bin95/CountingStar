package com.ssafy.countingstar.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.data.LightPollution;
import com.ssafy.countingstar.repository.ReactiveLightPollutionRepository;

import reactor.core.publisher.Flux;

@Service
public class LightPollutionServiceImpl implements LightPollutionService {
	
	@Autowired
	ReactiveLightPollutionRepository reactiveLightPollutionRepository;
	
	
	private Flux<LightPollution> getAllLightPollutionByDate(LocalDate date){
		return reactiveLightPollutionRepository.findByDate(date).cache();
	}

	@Override
	public List<LightPollution> getAllLightPollution(LocalDate date) {
		// 전략 : 캐싱을 이용한다.
		
		getAllLightPollutionByDate(date);
		
		Flux<LightPollution> flux = getAllLightPollutionByDate(date);
		
		// [1] 1주일 상한으로 과거 데이터를 가져오고 해당 데이터를 캐싱한다.	
		for(int i=1; i<7; i++) {
			date = date.minusDays(1);
			flux = Flux.concat(flux,getAllLightPollutionByDate(date));
		}
		
		
		// [2] 데이터를 전부 가져온다. 해당 객체는 여러번 읽어야 하므로, 동기적으로 읽는다. 모든 데이터를 메모리에 일시 적재한 뒤, 처리하도록 한다.
		return flux.collect(Collectors.toUnmodifiableList()).block();
	
	}
		

}
