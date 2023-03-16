package com.ssafy.countingstar.data;

public class Celestial {
    private String name;
    private int hrNumber;
    private double rightAscension;
    private double declination;
    private double visualMagnitude;
    private String spectralType;
    // other fields

    public Celestial(String name, int hrNumber, double rightAscension,
                      double declination, double visualMagnitude,
                      String spectralType) {
        this.name = name;
        this.hrNumber = hrNumber;
        this.rightAscension = rightAscension;
        this.declination = declination;
        this.visualMagnitude = visualMagnitude;
        this.spectralType = spectralType;
    }

    // getters and setters

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Celestial that = (Celestial) obj;

        return hrNumber == that.hrNumber; 
    }

}