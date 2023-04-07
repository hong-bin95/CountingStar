package com.ssafy.countingstar.data;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("lightpollution2")
public class LightPollution implements Serializable {
	
	@PrimaryKey
	private LightPollutionKey key;
    
    @Column("radiance")
    private float radiance;

    public LightPollution() {}

    public LightPollution(LightPollutionKey key, float radiance) {
        this.key = key;
        this.radiance = radiance;
    }

	public LightPollutionKey getKey() {
		return key;
	}

	public void setKey(LightPollutionKey key) {
		this.key = key;
	}

	public float getRadiance() {
		return radiance;
	}

	public void setRadiance(float radiance) {
		this.radiance = radiance;
	}
    
}