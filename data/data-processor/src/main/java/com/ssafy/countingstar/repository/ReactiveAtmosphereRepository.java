package com.ssafy.countingstar.repository;

import java.time.LocalDate;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.data.Atmosphere;
import com.ssafy.countingstar.data.CollectedDataKey;

import reactor.core.publisher.Flux;

@Repository
public interface ReactiveAtmosphereRepository extends ReactiveCassandraRepository<Atmosphere, CollectedDataKey>{

	Flux<Atmosphere> findByDate(LocalDate date);
	
	Flux<Atmosphere> findByDateAndHour(LocalDate date, int hour);
	
}
