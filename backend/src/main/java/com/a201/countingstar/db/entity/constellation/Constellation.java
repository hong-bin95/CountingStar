package com.a201.countingstar.db.entity.constellation;

import javax.persistence.*;

@Entity
@Table(name="constellation")
public class Constellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sql에 시키기
    @Column(name="constellation_id")
    private int constellationId;

    private String name;
    @Column(name="kor_name")
    private String korName;
    @Column(name="observe_month")
    private String observeMonth;

}
