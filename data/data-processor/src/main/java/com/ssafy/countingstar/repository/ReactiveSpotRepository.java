package com.ssafy.countingstar.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.dto.Spot;

@Repository
public interface ReactiveSpotRepository extends ReactiveCrudRepository<Spot, Integer>{

}
