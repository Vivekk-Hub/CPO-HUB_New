package com.evgateway.cpohubserver.form;

import java.util.Date;

public class CPOStatusSchedule {

	private Date period_begin;

	private Date period_end;

	private String status;

	public Date getPeriod_begin() {
		return period_begin;
	}

	public void setPeriod_begin(Date period_begin) {
		this.period_begin = period_begin;
	}

	public Date getPeriod_end() {
		return period_end;
	}

	public void setPeriod_end(Date period_end) {
		this.period_end = period_end;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
