package com.ssafy.countingstar.data;

import java.time.LocalDateTime;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("lightpollutioncksum")
public class LightPollutionCksum {
	
    @PrimaryKey
    private int cksum;
    
    private LocalDateTime date;
    
    public LightPollutionCksum() {
		super();
	}
    
	public LightPollutionCksum(int cksum, LocalDateTime date) {
		super();
		this.cksum = cksum;
		this.date = date;
	}
	
	public int getCksum() {
		return cksum;
	}
	
	public void setCksum(int cksum) {
		this.cksum = cksum;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
    
}