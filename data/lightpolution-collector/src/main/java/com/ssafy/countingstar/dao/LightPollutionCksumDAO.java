package com.ssafy.countingstar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.data.LightPollutionCksum;

@Repository
public class LightPollutionCksumDAO {
	
	@Autowired
    private CassandraOperations cassandraTemplate;
    
    public void save(LightPollutionCksum obj) {
        cassandraTemplate.insert(obj);
    }
    
    public LightPollutionCksum findById(int cksum) {
        return cassandraTemplate.selectOneById(cksum, LightPollutionCksum.class);
    }

}
