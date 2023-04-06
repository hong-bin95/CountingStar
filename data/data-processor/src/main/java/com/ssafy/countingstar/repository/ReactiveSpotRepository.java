package com.ssafy.countingstar.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.dto.Spot;

@Repository
public interface ReactiveSpotRepository extends R2dbcRepository<Spot, Integer>{

}
