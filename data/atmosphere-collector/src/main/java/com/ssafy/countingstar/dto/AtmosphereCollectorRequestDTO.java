package com.ssafy.countingstar.dto;

import java.time.LocalDateTime;

public class AtmosphereCollectorRequestDTO {
	private LocalDateTime date;

	public AtmosphereCollectorRequestDTO(LocalDateTime date) {
		super();
		this.date = date;
	}

	public AtmosphereCollectorRequestDTO() {
		super();
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
