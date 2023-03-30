package com.ssafy.countingstar.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.ssafy.countingstar.data.Observatory;

public interface ObservatoryRepository extends CassandraRepository<Observatory, Integer>{

}