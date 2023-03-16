package com.a201.countingstar.entity.recommendation;

import javax.persistence.*;

@Entity
@Table(name="recommendation")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sql에 시키기
    @Column(name="recommendation_id")
    private int crecommendationId;

    private String title;
    @Lob
    private String contents;

}
