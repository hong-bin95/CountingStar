package com.ssafy.countingstar.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.data.CollectedDataKey;
import com.ssafy.countingstar.data.LightPollution;

@Repository
public interface LightPollutionRepository extends CassandraRepository<LightPollution, CollectedDataKey>{
	
//	List<LightPollution> findAllByDateAndHour(LocalDate date, int hour);

}
