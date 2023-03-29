package com.ssafy.countingstar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.data.LightPollution;

@Repository
public class LightPollutionDAO {
	
	@Autowired
    private CassandraOperations cassandraTemplate;
    
    public void save(LightPollution obj) {
        cassandraTemplate.insert(obj);
    }

}
