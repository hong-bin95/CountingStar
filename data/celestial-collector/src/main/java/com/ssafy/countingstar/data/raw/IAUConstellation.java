package com.ssafy.countingstar.data.raw;

import java.io.Serializable;

public class IAUConstellation implements Serializable{
    Integer sno;
    String constellation;
    String iauAbbreviation;
    String otherAbbreviation;
    String genitive;
    String family;
    String origin;
    String meaning;
    String brightestStar;
    
    public IAUConstellation() {}

    public IAUConstellation(Integer sno, String constellation, 
                         String iauAbbreviation,
                         String otherAbbreviation,
                         String genitive,
                         String family,
                         String origin,
                         String meaning,
                         String brightestStar) {
        this.sno = sno;
        this.constellation = constellation;
        this.iauAbbreviation = iauAbbreviation; 
        this.otherAbbreviation = otherAbbreviation; 
        this.genitive = genitive; 
        this.family = family; 
        this.origin = origin; 
        this.meaning = meaning; 
        this.brightestStar = brightestStar; 
    }

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getIauAbbreviation() {
		return iauAbbreviation;
	}

	public void setIauAbbreviation(String iauAbbreviation) {
		this.iauAbbreviation = iauAbbreviation;
	}

	public String getOtherAbbreviation() {
		return otherAbbreviation;
	}

	public void setOtherAbbreviation(String otherAbbreviation) {
		this.otherAbbreviation = otherAbbreviation;
	}

	public String getGenitive() {
		return genitive;
	}

	public void setGenitive(String genitive) {
		this.genitive = genitive;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getBrightestStar() {
		return brightestStar;
	}

	public void setBrightestStar(String brightestStar) {
		this.brightestStar = brightestStar;
	}
    
    
}