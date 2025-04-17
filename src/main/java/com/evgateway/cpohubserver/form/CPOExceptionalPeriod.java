package com.evgateway.cpohubserver.form;

import java.util.Date;

public class CPOExceptionalPeriod {

	private Date period_begin;

	private Date period_end;

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

	@Override
	public String toString() {
		return "ExceptionalPeriod [period_begin=" + period_begin + ", period_end=" + period_end + "]";
	}

}
