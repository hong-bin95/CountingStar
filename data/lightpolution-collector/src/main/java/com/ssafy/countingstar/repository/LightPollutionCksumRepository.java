package com.ssafy.countingstar.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.ssafy.countingstar.data.LightPollutionCksum;

public interface LightPollutionCksumRepository extends CassandraRepository<LightPollutionCksum, Long>{

}
