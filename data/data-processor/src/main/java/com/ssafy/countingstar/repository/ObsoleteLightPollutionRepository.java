package com.ssafy.countingstar.repository;

import java.time.LocalDate;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.data.CollectedDataKey;
import com.ssafy.countingstar.data.LightPollution;
import com.ssafy.countingstar.data.LightPollutionKey;
import com.ssafy.countingstar.data.ObsoleteLightPollution;

import reactor.core.publisher.Flux;

@Repository
public interface ObsoleteLightPollutionRepository extends ReactiveCassandraRepository<ObsoleteLightPollution, CollectedDataKey>{
	
	@Query("SELECT * FROM lightpollution WHERE date = :date AND hour = :hour")
    Flux<ObsoleteLightPollution> findByDate(@Param("date") LocalDate date, @Param("hour") int hour);
	
//	@Query("SELECT * FROM lightpollution WHERE date = :date")
//    Flux<ObsoleteLightPollution> findByDate2(@Param("date") LocalDate date);

}
