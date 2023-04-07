package com.ssafy.countingstar.repository;

import java.time.LocalDate;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.data.LightPollution;
import com.ssafy.countingstar.data.LightPollutionKey;

import reactor.core.publisher.Flux;

@Repository
public interface ReactiveLightPollutionRepository extends ReactiveCassandraRepository<LightPollution,LightPollutionKey>{
	
	@Query("SELECT * FROM lightpollution2 WHERE slot = :slot")
    Flux<LightPollution> findBySlot(@Param("slot") int slot);

	@Query("SELECT * FROM lightpollution2 WHERE slot = :slot AND date > :dateLimit")
    Flux<LightPollution> findBySlotAndDate(@Param("slot") int slot, @Param("dateLimit") LocalDate dateLimit);

	
}
