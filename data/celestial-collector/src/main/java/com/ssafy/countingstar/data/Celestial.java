package com.ssafy.countingstar.data;

public class Celestial {
	private Long starId;
    private String name;
    private Long hd;
    private double rightAscension;
    private double declination;
    private double visualMagnitude;
    private Integer constellationId;
    
    public Celestial() {}

    public Celestial(Long starId, String name, Long hd, double rightAscension,
                      double declination, double visualMagnitude, Integer constellationId) {
    	this.starId = starId;
        this.name = name;
        this.hd = hd;
        this.rightAscension = rightAscension;
        this.declination = declination;
        this.visualMagnitude = visualMagnitude;
        this.constellationId = constellationId;
    }

	public Long getStarId() {
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

	public void setStarId(Long starId) {
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