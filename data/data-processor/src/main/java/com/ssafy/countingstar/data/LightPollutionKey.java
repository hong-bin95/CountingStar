package com.ssafy.countingstar.data;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class LightPollutionKey implements Serializable {

    @PrimaryKeyColumn(name = "slot", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int slot;

    @PrimaryKeyColumn(name = "lat")
    private float lat;

    @PrimaryKeyColumn(name = "lng")
    private float lng;

    @PrimaryKeyColumn(name = "date", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private LocalDate date;

    @PrimaryKeyColumn(name = "hour", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    private int hour;

    public LightPollutionKey() {
    }

    public LightPollutionKey(float lat, float lng, LocalDate date, int hour) {
        this.slot = calculateSlot(lat,lng);
        this.lat = lat;
        this.lng = lng;
        this.date = date;
        this.hour = hour;
    }
    
    public static int calculateSlot(float lat, float lng) {
    	return (int)(10*(lat + 90.0)) + 10000 * (int)(10*(lng + 180.0));
    }

    public int getSlot() {
        return slot;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

}