package com.evgateway.cpohubserver.form;

import java.util.List;

public class CPOHours {

	private boolean twentyfourseven;

	private List<CPORegularHours> regular_hours;

	private List<CPOExceptionalPeriod> exceptional_openings;

	private List<CPOExceptionalPeriod> exceptional_closings;

	public boolean isTwentyfourseven() {
		return twentyfourseven;
	}

	public void setTwentyfourseven(boolean twentyfourseven) {
		this.twentyfourseven = twentyfourseven;
	}

	public List<CPORegularHours> getRegular_hours() {
		return regular_hours;
	}

	public void setRegular_hours(List<CPORegularHours> regular_hours) {
		this.regular_hours = regular_hours;
	}

	public List<CPOExceptionalPeriod> getExceptional_openings() {
		return exceptional_openings;
	}

	public void setExceptional_openings(List<CPOExceptionalPeriod> exceptional_openings) {
		this.exceptional_openings = exceptional_openings;
	}

	public List<CPOExceptionalPeriod> getExceptional_closings() {
		return exceptional_closings;
	}

	public void setExceptional_closings(List<CPOExceptionalPeriod> exceptional_closings) {
		this.exceptional_closings = exceptional_closings;
	}

	@Override
	public String toString() {
		return "Hours [twentyfourseven=" + twentyfourseven + ", regular_hours=" + regular_hours
				+ ", exceptional_openings=" + exceptional_openings + ", exceptional_closings=" + exceptional_closings
				+ "]";
	}

}
