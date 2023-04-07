package com.ssafy.countingstar.data;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("celestial")
public class Celestial implements Serializable {
	
	@PrimaryKey
	private int id;
	
	@Column("star_id")
	private Integer starId;
	
	@Column("name")
    private String name;
	
	@Column("hd")
    private Long hd;
	
	@Column("right_ascension")
    private double rightAscension;
	
	@Column("declination")
    private double declination;
	
	@Column("visual_magnitude")
    private double visualMagnitude;
	
	@Column("constellation_id")
    private Integer constellationId;
    
    public Celestial() {}

    public Celestial(int id, Integer starId, String name, Long hd, double rightAscension,
                      double declination, double visualMagnitude, Integer constellationId) {
    	this.id = id;
    	this.starId = starId;
        this.name = name;
        this.hd = hd;
        this.rightAscension = rightAscension;
        this.declination = declination;
        this.visualMagnitude = visualMagnitude;
        this.constellationId = constellationId;
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getStarId() {
		return starId;
	}

	public String getName() {
		return name;
	}

	public Long getHd() {
		return hd;
	}

	public double getRightAscension() {
		return rightAscension;
	}

	public double getDeclination() {
		return declination;
	}

	public double getVisualMagnitude() {
		return visualMagnitude;
	}

	public Integer getConstellationId() {
		return constellationId;
	}

	public void setStarId(Integer starId) {
		this.starId = starId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHd(Long hd) {
		this.hd = hd;
	}

	public void setRightAscension(double rightAscension) {
		this.rightAscension = rightAscension;
	}

	public void setDeclination(double declination) {
		this.declination = declination;
	}

	public void setVisualMagnitude(double visualMagnitude) {
		this.visualMagnitude = visualMagnitude;
	}

	public void setConstellationId(Integer constellationId) {
		this.constellationId = constellationId;
	}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Celestial that = (Celestial) obj;

        return starId == that.starId; 
    }

}