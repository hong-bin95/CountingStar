package com.ssafy.countingstar.data.raw;

public class IAUStar {
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
	
}
