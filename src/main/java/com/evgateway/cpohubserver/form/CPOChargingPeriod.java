package com.evgateway.cpohubserver.form;

import java.util.Date;
import java.util.List;

public class CPOChargingPeriod {

	private Date start_date_time;

	private String tariff_id;

	private List<CPODimension> dimensions;

	public Date getStart_date_time() {
		return start_date_time;
	}

	public void setStart_date_time(Date start_date_time) {
		this.start_date_time = start_date_time;
	}

	public List<CPODimension> getDimensions() {
		return dimensions;
	}

	public void setDimensions(List<CPODimension> dimensions) {
		this.dimensions = dimensions;
	}

	public String getTariff_id() {
		return tariff_id;
	}

	public void setTariff_id(String tariff_id) {
		this.tariff_id = tariff_id;
	}

}
