package com.a201.countingstar.db.entity.recommendation;

import com.a201.countingstar.db.entity.spot.Spot;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="recommendation")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sql에 시키기
    @Column(name="recommendation_id")
    private int recommendationId;

    private String title;
    @Lob
    private String contents;
    // 컨텐츠 타입
    @Column(length = 2)
    private String type;

    @ManyToOne
    @JoinColumn(name = "spot_master_id")
    private Spot spot_master;


}
