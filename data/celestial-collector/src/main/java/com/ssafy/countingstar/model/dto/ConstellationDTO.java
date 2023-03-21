package com.ssafy.countingstar.model.dto;

public class ConstellationDTO {
	int constellation_id;
	String name;
	String observe_month;
	public ConstellationDTO() {}
	public ConstellationDTO(int constellation_id, String name, String observe_month) {
		super();
		this.constellation_id = constellation_id;
		this.name = name;
		this.observe_month = observe_month;
	}
	public int getConstellation_id() {
		return constellation_id;
	}
	public void setConstellation_id(int constellation_id) {
		this.constellation_id = constellation_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getObserve_month() {
		return observe_month;
	}
	public void setObserve_month(String observe_month) {
		this.observe_month = observe_month;
	}
}
