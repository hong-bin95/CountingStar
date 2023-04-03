package com.ssafy.countingstar.repository;

import java.time.LocalDate;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.data.CollectedDataKey;
import com.ssafy.countingstar.data.LightPollution;

import reactor.core.publisher.Flux;

@Repository
public interface ReactiveLightPollutionRepository extends ReactiveCassandraRepository<LightPollution,CollectedDataKey>{

	Flux<LightPollution> findByDate(LocalDate date);
	
}
