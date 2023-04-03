package com.ssafy.countingstar.data;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class CollectedDataKey implements Serializable {
	
    @PrimaryKeyColumn(name = "date", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private LocalDate date;
    
    @PrimaryKeyColumn(name = "hour", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private int hour;
    
    @PrimaryKeyColumn(name = "lat", ordinal = 2)
    private float lat;
    
    @PrimaryKeyColumn(name = "lng", ordinal = 3)
    private float lng;
    
    public CollectedDataKey() {}

	public CollectedDataKey(LocalDate date, int hour, float lat, float lng) {
		super();
		this.date = date;
		this.hour = hour;
		this.lat = lat;
		this.lng = lng;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}
    
}
