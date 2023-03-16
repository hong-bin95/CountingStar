package com.a201.countingstar.entity.star;

import com.a201.countingstar.entity.code.CodeDetail;

import javax.persistence.*;

@Entity
@Table(name="star")
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sql에 시키기
    @Column(name="star_id")
    private int starId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "code_detail_id")
    // 천체 종류
    private CodeDetail code;
}
