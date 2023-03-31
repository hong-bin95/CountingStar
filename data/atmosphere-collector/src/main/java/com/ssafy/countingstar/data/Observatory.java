package com.ssafy.countingstar.data;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Table(value = "observatory")
public class Observatory implements Serializable {

    @PrimaryKey
    @Column(value = "code")
    private int code;

    @Column(value = "lat")
    private float lat;

    @Column(value = "lng")
    private float lng;

    public Observatory() {}

    public Observatory(int code, float lat, float lng) {
        this.code = code;
        this.lat = lat;
        this.lng = lng;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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