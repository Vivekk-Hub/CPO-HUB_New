package com.evgateway.cpohubserver.form;

public class CPORegularHours {

	private int weekday;

	private String period_begin;

	private String period_end;

	public int getWeekday() {
		return weekday;
	}

	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}

	public String getPeriod_begin() {
		return period_begin;
	}

	public void setPeriod_begin(String period_begin) {
		this.period_begin = period_begin;
	}

	public String getPeriod_end() {
		return period_end;
	}

	public void setPeriod_end(String period_end) {
		this.period_end = period_end;
	}

	@Override
	public String toString() {
		return "RegularHours [weekday=" + weekday + ", period_begin=" + period_begin + ", period_end=" + period_end
				+ "]";
	}

}
