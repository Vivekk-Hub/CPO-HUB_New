package com.evgateway.cpohubserver.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ocpi_cpohub_downtime")
public class CPOHUBDowntimeModel {

	@Id
	private String id;
	private String partnerId;
	private Instant startTime;
	private Instant endTime;
	private long duration; // in seconds

	public long getduration() {
		return duration;
	}

	public void setduration(long duration) {
		this.duration = duration;
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public Instant getStartTime() {
		return startTime;
	}

	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}

	public Instant getEndTime() {
		return endTime;
	}

	public void setEndTime(Instant endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "CPOHUBDowntimeModel [id=" + id + ", partnerId=" + partnerId + ", startTime=" + startTime + ", endTime="
				+ endTime + ", duration=" + duration + "]";
	}

}
