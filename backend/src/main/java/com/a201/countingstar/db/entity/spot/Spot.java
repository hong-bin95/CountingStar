package com.a201.countingstar.db.entity.spot;

import javax.persistence.*;

@Entity
@Table(name="spot")
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sql에 시키기
    @Column(name="spot_id")
    private int spotId;

    private String latitude;
    private String longitude;
    @Column(name="area_code", length = 7)
    private String areaCode;

}
