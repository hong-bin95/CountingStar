package com.ssafy.countingstar.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.data.Atmosphere;
import com.ssafy.countingstar.data.CollectedDataKey;

@Repository
public interface AtmosphereRepository extends CassandraRepository<Atmosphere, CollectedDataKey> {

}
