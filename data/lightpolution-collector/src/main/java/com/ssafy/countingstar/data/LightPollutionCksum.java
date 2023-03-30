package com.ssafy.countingstar.data;

import java.time.LocalDateTime;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("lightpollutioncksum")
public class LightPollutionCksum {
	
    @PrimaryKey
    private long cksum;
    
    private LocalDateTime date;
    
    public LightPollutionCksum() {
		super();
	}
    
	public LightPollutionCksum(long cksum, LocalDateTime date) {
		super();
		this.cksum = cksum;
		this.date = date;
	}
	
	public long getCksum() {
		return cksum;
	}
	
	public void setCksum(long cksum) {
		this.cksum = cksum;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
    
}