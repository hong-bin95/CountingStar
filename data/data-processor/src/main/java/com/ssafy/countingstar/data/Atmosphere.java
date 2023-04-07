package com.ssafy.countingstar.data;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("atmosphere")
public class Atmosphere implements Serializable {
	
	@PrimaryKey
	private CollectedDataKey key;
	
    @Column("vs")
    private int vs;

    @Column("ss")
    private float ss;

    public Atmosphere() {}

    public Atmosphere(CollectedDataKey key, int vs, float ss) {
    	this.key = key;
        this.vs = vs;
        this.ss = ss;
    }

	public CollectedDataKey getKey() {
		return key;
	}

	public void setKey(CollectedDataKey key) {
		this.key = key;
	}

	public int getVs() {
		return vs;
	}

	public void setVs(int vs) {
		this.vs = vs;
	}

	public float getSs() {
		return ss;
	}

	public void setSs(float ss) {
		this.ss = ss;
	}
    
    
}