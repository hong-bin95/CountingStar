package com.ssafy.countingstar.model.dto;

public class Star {
	
	String name;
	int code_detail_id;
	Integer constellation_id;
	public Star(String name, int code_detail_id, Integer constellation_id) {
		super();
		this.name = name;
		this.code_detail_id = code_detail_id;
		this.constellation_id = constellation_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode_detail_id() {
		return code_detail_id;
	}
	public void setCode_detail_id(int code_detail_id) {
		this.code_detail_id = code_detail_id;
	}
	public Integer getConstellation_id() {
		return constellation_id;
	}
	public void setConstellation_id(Integer constellation_id) {
		this.constellation_id = constellation_id;
	}
	
	

}
