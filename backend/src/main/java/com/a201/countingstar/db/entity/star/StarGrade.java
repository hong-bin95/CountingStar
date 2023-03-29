package com.a201.countingstar.db.entity.star;

import com.a201.countingstar.db.entity.spot.Spot;

import javax.persistence.*;

@Entity
@Table(name="star_grade")
public class StarGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sql에 시키기
    @Column(name="star_grade_id")
    private int starGradeId;

    @ManyToOne
    @JoinColumn(name = "star_id")
    private Star star;

    @Column(name="basic_date_year", length = 4)
    private String basicDateYear;

    @Column(name="basic_date_month", length = 2)
    private String basicDateMonth;

    @Column(name="basic_date_day", length = 2)
    private String basicDateDay;

    @Column(name="basic_date_hour", length = 2)
    private String basicDateHour;

    @Column(name="basic_date_minute", length = 2)
    private String basicDateMinute;

    @Column(name="grade_1")
    private double grade1;
    @Column(name="grade_2")
    private int grade2;

    @Column(name="start_latitude")
    private String startLatitude;
    @Column(name="end_latitude")
    private String endLatitude;

    @Column(name="start_longitude")
    private String startLongitude;
    @Column(name="end_longitude")
    private String endLongitude;

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private Spot spot;
}
