package com.ssafy.countingstar.data;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("lightpollution")
public class LightPollution {
	
	@PrimaryKey
	private CollectedDataKey key;
    
    @Column("radiance")
    private float radiance;

    public LightPollution() {}

    public LightPollution(CollectedDataKey key, float radiance) {
        this.key = key;
        this.radiance = radiance;
    }

	public CollectedDataKey getKey() {
		return key;
	}

	public void setKey(CollectedDataKey key) {
		this.key = key;
	}

	public float getRadiance() {
		return radiance;
	}

	public void setRadiance(float radiance) {
		this.radiance = radiance;
	}
    
}