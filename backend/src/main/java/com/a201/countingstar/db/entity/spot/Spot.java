package com.a201.countingstar.db.entity.spot;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name="spot")
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="spot_id")
    private int spotId;

    private String latitude;
    private String longitude;
    @Column(name="area_code", length = 7)
    private String areaCode;

}
