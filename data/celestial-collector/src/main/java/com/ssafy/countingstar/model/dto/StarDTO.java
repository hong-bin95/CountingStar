package com.ssafy.countingstar.model.dto;

public class StarDTO {
	Integer star_id;
	String name;
	int code_detail_id;
	Integer constellation_id;
	public StarDTO() {}
	public StarDTO(Integer star_id, String name, int code_detail_id, Integer constellation_id) {
		super();
		this.star_id = star_id;
		this.name = name;
		this.code_detail_id = code_detail_id;
		this.constellation_id = constellation_id;
	}
	public Integer getStar_id() {
		return star_id;
	}
	public void setStar_id(Integer star_id) {
		this.star_id = star_id;
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
