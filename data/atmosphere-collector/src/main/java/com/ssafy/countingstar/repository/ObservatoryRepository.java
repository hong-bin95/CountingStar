package com.ssafy.countingstar.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.data.Observatory;

@Repository
public interface ObservatoryRepository extends CassandraRepository<Observatory, Integer>{

}