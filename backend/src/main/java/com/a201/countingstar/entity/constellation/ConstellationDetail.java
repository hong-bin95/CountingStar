package com.a201.countingstar.entity.constellation;

import com.a201.countingstar.entity.code.CodeMaster;
import com.a201.countingstar.entity.star.Star;

import javax.persistence.*;

@Entity
@Table(name="constellation_detail")
public class ConstellationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sql에 시키기
    @Column(name="constellation_detail_id")
    private int constellationDetailId;

    @ManyToOne
    @JoinColumn(name = "constellation_master_id")
    private Constellation master;

    @ManyToOne
    @JoinColumn(name = "star_master_id")
    private Star star_master;

}
