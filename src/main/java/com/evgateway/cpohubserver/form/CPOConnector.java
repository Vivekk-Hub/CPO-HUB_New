package com.evgateway.cpohubserver.form;

import java.util.Arrays;
import java.util.Date;

public class CPOConnector {

	private String id;

	private String standard;

	private String format;

	private String power_type;

	private int max_voltage;

	private int max_amperage;

	private int max_electric_power;

	private String terms_and_conditions;

	private String[] tariff_ids;

	private Date last_updated;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getPower_type() {
		return power_type;
	}

	public void setPower_type(String power_type) {
		this.power_type = power_type;
	}

	public int getMax_voltage() {
		return max_voltage;
	}

	public void setMax_voltage(int max_voltage) {
		this.max_voltage = max_voltage;
	}

	public int getMax_amperage() {
		return max_amperage;
	}

	public void setMax_amperage(int max_amperage) {
		this.max_amperage = max_amperage;
	}

	public int getMax_electric_power() {
		return max_electric_power;
	}

	public void setMax_electric_power(int max_electric_power) {
		this.max_electric_power = max_electric_power;
	}

	public String getTerms_and_conditions() {
		return terms_and_conditions;
	}

	public void setTerms_and_conditions(String terms_and_conditions) {
		this.terms_and_conditions = terms_and_conditions;
	}

	public String[] getTariff_ids() {
		return tariff_ids;
	}

	public void setTariff_ids(String[] tariff_ids) {
		this.tariff_ids = tariff_ids;
	}

	public Date getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}

	@Override
	public String toString() {
		return "EmspConnector [id=" + id + ", standard=" + standard + ", format=" + format + ", power_type="
				+ power_type + ", max_voltage=" + max_voltage + ", max_amperage=" + max_amperage
				+ ", max_electric_power=" + max_electric_power + ", terms_and_conditions=" + terms_and_conditions
				+ ", tariff_ids=" + Arrays.toString(tariff_ids) + ", last_updated=" + last_updated + "]";
	}

}
