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
}