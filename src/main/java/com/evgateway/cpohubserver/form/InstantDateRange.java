package com.evgateway.cpohubserver.form;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class InstantDateRange {

	private Instant startInstant;
	private Instant endInstant;

	public InstantDateRange(Instant startInstant, Instant endInstant) {
		this.startInstant = startInstant;
		this.endInstant = endInstant;
	}

	public Instant getStartInstant() {
		return startInstant;
	}

	public void setStartInstant(Instant startInstant) {
		this.startInstant = startInstant;
	}

	public Instant getEndInstant() {
		return endInstant;
	}

	public void setEndInstant(Instant endInstant) {
		this.endInstant = endInstant;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC);
		return "Start: " + formatter.format(startInstant) + ", End: " + formatter.format(endInstant);
	}

}
