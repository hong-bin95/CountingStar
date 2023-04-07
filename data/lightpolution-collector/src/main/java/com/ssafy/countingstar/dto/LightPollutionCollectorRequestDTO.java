package com.ssafy.countingstar.dto;

import java.time.LocalDateTime;

public class LightPollutionCollectorRequestDTO {
	
	private LocalDateTime date;

	public LightPollutionCollectorRequestDTO(LocalDateTime date) {
		super();
		this.date = date;
	}

	public LightPollutionCollectorRequestDTO() {
		super();
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
