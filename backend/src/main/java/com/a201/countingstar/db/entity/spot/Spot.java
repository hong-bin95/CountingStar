package com.a201.countingstar.db.entity.spot;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="spot")
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="spot_id")
    private int spotId;
    @Column(name="spot_name")
    private String spotName;

    private String latitude;
    private String longitude;
    @Column(name="area_code", length = 7)
    private String areaCode;

    private int x;
    private int y;

    @Column(name="location_name")
    private String locationName;

    @Column(name="location_code")
    private String locationCode;

}
