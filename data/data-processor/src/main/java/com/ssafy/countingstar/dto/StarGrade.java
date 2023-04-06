package com.ssafy.countingstar.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "star_grade")
public class StarGrade implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "star_grade_id")
    private Integer starGradeId;
    
    @Column(name = "spot_id")
    private int spot_id;
    
    @Column(name = "basic_date_year")
    private String basicDateYear;
    
    @Column(name = "basic_date_month")
    private String basicDateMonth;
    
    @Column(name = "basic_date_day")
    private String basicDateDay;
    
    @Column(name = "basic_date_hour")
    private String basicDateHour;
    
    @Column(name = "basic_date_minute")
    private String basicDateMinute;
    
    @Column(name = "grade_1")
    private double grade_1;
    
    @Column(name = "grade_2")
    private int grade_2;
    
    @Column(name = "start_latitude")
    private String startLatitude;
    
    @Column(name = "start_longitude")
    private String startLongitude;
    
    @Column(name = "end_latitude")
    private String endLatitude;
    
    @Column(name = "end_longitude")
    private String endLongitude;
    
    @Column(name = "star_id")
    private int star_id;

	public StarGrade() {}

	public Integer getStarGradeId() {
		return starGradeId;
	}

	public void setStarGradeId(Integer starGradeId) {
		this.starGradeId = starGradeId;
	}

	public int getSpot_id() {
		return spot_id;
	}

	public void setSpot_id(int spot_id) {
		this.spot_id = spot_id;
	}

	public String getBasicDateYear() {
		return basicDateYear;
	}

	public void setBasicDateYear(String basicDateYear) {
		this.basicDateYear = basicDateYear;
	}

	public String getBasicDateMonth() {
		return basicDateMonth;
	}

	public void setBasicDateMonth(String basicDateMonth) {
		this.basicDateMonth = basicDateMonth;
	}

	public String getBasicDateDay() {
		return basicDateDay;
	}

	public void setBasicDateDay(String basicDateDay) {
		this.basicDateDay = basicDateDay;
	}

	public String getBasicDateHour() {
		return basicDateHour;
	}

	public void setBasicDateHour(String basicDateHour) {
		this.basicDateHour = basicDateHour;
	}

	public String getBasicDateMinute() {
		return basicDateMinute;
	}

	public void setBasicDateMinute(String basicDateMinute) {
		this.basicDateMinute = basicDateMinute;
	}

	public double getGrade1() {
		return grade_1;
	}

	public void setGrade1(double grade1) {
		this.grade_1 = grade1;
	}

	public int getGrade2() {
		return grade_2;
	}

	public void setGrade2(int grade2) {
		this.grade_2 = grade2;
	}

	public String getStartLatitude() {
		return startLatitude;
	}

	public void setStartLatitude(String startLatitude) {
		this.startLatitude = startLatitude;
	}

	public String getStartLongitude() {
		return startLongitude;
	}

	public void setStartLongitude(String startLongitude) {
		this.startLongitude = startLongitude;
	}

	public String getEndLatitude() {
		return endLatitude;
	}

	public void setEndLatitude(String endLatitude) {
		this.endLatitude = endLatitude;
	}

	public String getEndLongitude() {
		return endLongitude;
	}

	public void setEndLongitude(String endLongitude) {
		this.endLongitude = endLongitude;
	}

	public int getStar_id() {
		return star_id;
	}

	public void setStar_id(int star_id) {
		this.star_id = star_id;
	}

	@Override
	public String toString() {
		return "StarGrade [starGradeId=" + starGradeId + ", spot_id=" + spot_id + ", basicDateYear=" + basicDateYear
				+ ", basicDateMonth=" + basicDateMonth + ", basicDateDay=" + basicDateDay + ", basicDateHour="
				+ basicDateHour + ", basicDateMinute=" + basicDateMinute + ", grade1=" + grade_1 + ", grade2=" + grade_2
				+ ", startLatitude=" + startLatitude + ", startLongitude=" + startLongitude + ", endLatitude="
				+ endLatitude + ", endLongitude=" + endLongitude + ", star_id=" + star_id + "]";
	}

	
    
}