package com.evgateway.cpohubserver.model;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ocpi_cpo_connector")
public class CPOConnectorModel {

	@Id
	private String u_id;

	private String uid;

	private String id;
	private String cpo_country_code;

	private String cpo_party_id;
	private String standard;

	private String format;

	private String power_type;

	private int max_voltage;

	private int max_amperage;

	private int max_electric_power;

	private String terms_and_conditions;

	private String[] tariff_ids;

	private String evse_ref_id;

	private String evse_uid;

	private Instant last_updated;

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public String getEvse_ref_id() {
		return evse_ref_id;
	}

	public void setEvse_ref_id(String evse_ref_id) {
		this.evse_ref_id = evse_ref_id;
	}

	public String getEvse_uid() {
		return evse_uid;
	}

	public void setEvse_uid(String evse_uid) {
		this.evse_uid = evse_uid;
	}

	public Instant getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Instant last_updated) {
		this.last_updated = last_updated;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCpo_country_code() {
		return cpo_country_code;
	}

	public void setCpo_country_code(String cpo_country_code) {
		this.cpo_country_code = cpo_country_code;
	}

	public String getCpo_party_id() {
		return cpo_party_id;
	}

	public void setCpo_party_id(String cpo_party_id) {
		this.cpo_party_id = cpo_party_id;
	}

	@Override
	public String toString() {
		return "CPOConnectorModel [u_id=" + u_id + ", uid=" + uid + ", id=" + id + ", cpo_country_code="
				+ cpo_country_code + ", cpo_party_id=" + cpo_party_id + ", standard=" + standard + ", format=" + format
				+ ", power_type=" + power_type + ", max_voltage=" + max_voltage + ", max_amperage=" + max_amperage
				+ ", max_electric_power=" + max_electric_power + ", terms_and_conditions=" + terms_and_conditions
				+ ", tariff_ids=" + Arrays.toString(tariff_ids) + ", evse_ref_id=" + evse_ref_id + ", evse_uid="
				+ evse_uid + ", last_updated=" + last_updated + "]";
	}

}
