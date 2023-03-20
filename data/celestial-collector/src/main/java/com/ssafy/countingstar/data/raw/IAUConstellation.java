package com.ssafy.countingstar.data.raw;

public class IAUConstellation {
    Integer sno;
    String constellation;
    String iauAbbreviation;
    String otherAbbreviation;
    String genitive;
    String family;
    String origin;
    String meaning;
    String brightestStar;

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