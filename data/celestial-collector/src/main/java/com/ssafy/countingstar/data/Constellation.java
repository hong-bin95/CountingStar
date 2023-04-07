package com.ssafy.countingstar.data;

import java.io.Serializable;

public class Constellation implements Serializable {
	int constellationId;
	String name;
	String abbr;
	String family;
	public Constellation() {}
	public Constellation(int constellationId, String name, String abbr, String family) {
		super();
		this.constellationId = constellationId;
		this.name = name;
		this.abbr = abbr;
		this.family = family;
	}
	public int getConstellationId() {
		return constellationId;
	}
	public String getName() {
		return name;
	}
	public String getAbbr() {
		return abbr;
	}
	public String getFamily() {
		return family;
	}
	
	
}
