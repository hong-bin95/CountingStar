package com.ssafy.countingstar.data.raw;

import java.io.Serializable;

public class IAUStar implements Serializable{
    String nameAscii;
    String nameDiacritics;
    String designation;
    String id;
    String idDiacritics;
    String con;
    String wds;
    String wdsJ;
    Double mag;
    String bnd; 
    Long hip; 
    Long hd; 
    Double raJ2000; 
	Double decJ2000; 
	String date; 
	String notes;
	
	public IAUStar() {}
	
	public IAUStar(String nameAscii, String nameDiacritics, String designation, String id, String idDiacritics,
			String con, String wds, String wdsJ, Double mag, String bnd, Long hip, Long hd, Double raJ2000,
			Double decJ2000, String date, String notes) {
		super();
		this.nameAscii = nameAscii;
		this.nameDiacritics = nameDiacritics;
		this.designation = designation;
		this.id = id;
		this.idDiacritics = idDiacritics;
		this.con = con;
		this.wds = wds;
		this.wdsJ = wdsJ;
		this.mag = mag;
		this.bnd = bnd;
		this.hip = hip;
		this.hd = hd;
		this.raJ2000 = raJ2000;
		this.decJ2000 = decJ2000;
		this.date = date;
		this.notes = notes;
	}

	public String getNameAscii() {
		return nameAscii;
	}

	public void setNameAscii(String nameAscii) {
		this.nameAscii = nameAscii;
	}

	public String getNameDiacritics() {
		return nameDiacritics;
	}

	public void setNameDiacritics(String nameDiacritics) {
		this.nameDiacritics = nameDiacritics;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdDiacritics() {
		return idDiacritics;
	}

	public void setIdDiacritics(String idDiacritics) {
		this.idDiacritics = idDiacritics;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getWds() {
		return wds;
	}

	public void setWds(String wds) {
		this.wds = wds;
	}

	public String getWdsJ() {
		return wdsJ;
	}

	public void setWdsJ(String wdsJ) {
		this.wdsJ = wdsJ;
	}

	public Double getMag() {
		return mag;
	}

	public void setMag(Double mag) {
		this.mag = mag;
	}

	public String getBnd() {
		return bnd;
	}

	public void setBnd(String bnd) {
		this.bnd = bnd;
	}

	public Long getHip() {
		return hip;
	}

	public void setHip(Long hip) {
		this.hip = hip;
	}

	public Long getHd() {
		return hd;
	}

	public void setHd(Long hd) {
		this.hd = hd;
	}

	public Double getRaJ2000() {
		return raJ2000;
	}

	public void setRaJ2000(Double raJ2000) {
		this.raJ2000 = raJ2000;
	}

	public Double getDecJ2000() {
		return decJ2000;
	}

	public void setDecJ2000(Double decJ2000) {
		this.decJ2000 = decJ2000;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	
}
