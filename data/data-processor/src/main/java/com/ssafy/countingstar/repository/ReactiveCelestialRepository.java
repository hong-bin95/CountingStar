package com.ssafy.countingstar.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.data.Celestial;

@Repository
public interface ReactiveCelestialRepository extends ReactiveCassandraRepository<Celestial, Integer>{

}
