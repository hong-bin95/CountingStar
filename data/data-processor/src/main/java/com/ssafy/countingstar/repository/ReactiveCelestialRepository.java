package com.ssafy.countingstar.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.data.Celestial;

import reactor.core.publisher.Flux;

@Repository
public interface ReactiveCelestialRepository extends CrudRepository<Celestial, Integer>{
	
	@Query("SELECT star_id, constellation_id, declination, hd, name, right_ascension, visual_magnitude FROM celestial")
	List<Celestial> findAll();

}
