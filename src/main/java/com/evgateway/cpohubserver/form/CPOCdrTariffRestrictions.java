package com.evgateway.cpohubserver.form;

import java.util.List;

public class CPOCdrTariffRestrictions {

	private String start_time;

	private String end_time;

	private String start_date;

	private String end_date;

	private String min_kwh;

	private String max_kwh;

	private String min_power;

	private String max_power;

	private String min_duration;

	private String max_duration;

	private List<String> day_of_week;

	private String reservation;

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getMin_kwh() {
		return min_kwh;
	}

	public void setMin_kwh(String min_kwh) {
		this.min_kwh = min_kwh;
	}

	public String getMax_kwh() {
		return max_kwh;
	}

	public void setMax_kwh(String max_kwh) {
		this.max_kwh = max_kwh;
	}

	public String getMin_power() {
		return min_power;
	}

	public void setMin_power(String min_power) {
		this.min_power = min_power;
	}

	public String getMax_power() {
		return max_power;
	}

	public void setMax_power(String max_power) {
		this.max_power = max_power;
	}

	public String getMin_duration() {
		return min_duration;
	}

	public void setMin_duration(String min_duration) {
		this.min_duration = min_duration;
	}

	public String getMax_duration() {
		return max_duration;
	}

	public void setMax_duration(String max_duration) {
		this.max_duration = max_duration;
	}

	public List<String> getDay_of_week() {
		return day_of_week;
	}

	public void setDay_of_week(List<String> day_of_week) {
		this.day_of_week = day_of_week;
	}

	public String getReservation() {
		return reservation;
	}

	public void setReservation(String reservation) {
		this.reservation = reservation;
	}

}
