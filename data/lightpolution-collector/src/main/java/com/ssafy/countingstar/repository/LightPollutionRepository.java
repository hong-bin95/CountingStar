package com.ssafy.countingstar.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.data.LightPollution;

@Repository
public interface LightPollutionRepository extends CassandraRepository<LightPollution, UUID>{
	
	

}
