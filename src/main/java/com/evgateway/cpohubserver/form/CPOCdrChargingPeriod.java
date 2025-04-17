package com.evgateway.cpohubserver.form;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CPOCdrChargingPeriod {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private Date start_date_time;

	private String tariff_id;

	private List<CPOCdrDimension> dimensions;

	private CPOCdr cdr;

	public CPOCdr getCdr() {
		return cdr;
	}

	public void setCdr(CPOCdr cdr) {
		this.cdr = cdr;
	}

	public Date getStart_date_time() {
		return start_date_time;
	}

	public void setStart_date_time(Date start_date_time) {
		this.start_date_time = start_date_time;
	}

	public List<CPOCdrDimension> getDimensions() {
		return dimensions;
	}

	public void setDimensions(List<CPOCdrDimension> dimensions) {
		this.dimensions = dimensions;
	}

	public String getTariff_id() {
		return tariff_id;
	}

	public void setTariff_id(String tariff_id) {
		this.tariff_id = tariff_id;
	}

	@Override
	public String toString() {
		return "ChargingPeriod [start_date_time=" + start_date_time + ", tariff_id=" + tariff_id + ", dimensions="
				+ dimensions + ", cdr=" + cdr + "]";
	}

}
