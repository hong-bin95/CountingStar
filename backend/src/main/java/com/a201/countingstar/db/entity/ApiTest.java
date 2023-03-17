package com.a201.countingstar.db.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
public class ApiTest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int at_id;
    private String at_title;
}
