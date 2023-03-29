package com.ssafy.countingstar.data;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.oss.driver.api.core.uuid.Uuids;

@Table("lightpollution")
public class LightPollution {
	
	@PrimaryKeyColumn(name = "lat", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private float lat;
	
	@PrimaryKeyColumn(name = "lng", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private float lng;
	
	@PrimaryKeyColumn(name = "time", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
	private UUID time;
	
	@Column("radiance")
	private float radiance;
	
	public LightPollution() {}
	
	public LightPollution(float lat, float lng, Timestamp time, float radiance) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.time = Uuids.timeBased();
		this.radiance = radiance;
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

	public Timestamp getTime() {
		return new Timestamp(Uuids.unixTimestamp(time));
	}

	public void setTime(Timestamp time) {
		this.time = Uuids.startOf(time.getTime());
	}

	public float getRadiance() {
		return radiance;
	}

	public void setRadiance(float radiance) {
		this.radiance = radiance;
	}
}
