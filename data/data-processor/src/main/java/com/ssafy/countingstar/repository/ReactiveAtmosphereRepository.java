package com.ssafy.countingstar.repository;

import java.time.LocalDate;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.data.Atmosphere;
import com.ssafy.countingstar.data.CollectedDataKey;

import reactor.core.publisher.Flux;

@Repository
public interface ReactiveAtmosphereRepository extends ReactiveCassandraRepository<Atmosphere, CollectedDataKey>{

    @Query("SELECT * FROM atmosphere WHERE date = :date AND hour = :hour")
    Flux<Atmosphere> findByDateAndHour(@Param("date") LocalDate date, @Param("hour") int hour);

}