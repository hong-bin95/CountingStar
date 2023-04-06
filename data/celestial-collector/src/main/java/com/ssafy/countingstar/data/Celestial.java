package com.ssafy.countingstar.data;

import java.io.Serializable;

public class Celestial implements Serializable {
	private Integer id;
	private Integer starId;
    private String name;
    private Long hd;
    private double rightAscension;
    private double declination;
    private double visualMagnitude;
    private Integer constellationId;
    
    public Celestial() {}

    public Celestial(Integer id, Integer starId, String name, Long hd, double rightAscension,
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

}