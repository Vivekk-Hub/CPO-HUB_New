package com.evgateway.cpohubserver.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.evgateway.cpohubserver.form.CPOChargingPeriod;
import com.evgateway.cpohubserver.form.CPOLocation;
import com.evgateway.cpohubserver.form.CPOPrice;
import com.evgateway.cpohubserver.form.CPOSesionToken;
import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "ocpi_cpo_session")
public class CPOSessionModel {

	@Id
	private String u_id;

	private String id;

	private String uid;

	private String country_code;

	private String party_id;

	private String cpo_country_code;

	private String cpo_party_id;

	private String emsp_country_code;

	private String cpo_code;
	private String emsp_code;

	private String emsp_party_id;

	private String auth_id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private Instant start_date_time;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private Instant end_date_time;

	private double kwh;

	private CPOSesionToken cdr_token;

	private String auth_method;

	private String authorization_reference;

	private String location_id;

	private String evse_uid;

	private String connector_id;

	private String meter_id;

	private String currency;

	private List<CPOChargingPeriod> charging_periods = new ArrayList<CPOChargingPeriod>();

	private CPOPrice total_cost;

	private String status;

	private CPOLocation location;

	private Instant last_updated;

	private String version;

	public String getCpo_code() {
		return cpo_code;
	}

	public void setCpo_code(String cpo_code) {
		this.cpo_code = cpo_code;
	}

	public String getEmsp_code() {
		return emsp_code;
	}

	public void setEmsp_code(String emsp_code) {
		this.emsp_code = emsp_code;
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

	public String getEmsp_country_code() {
		return emsp_country_code;
	}

	public void setEmsp_country_code(String emsp_country_code) {
		this.emsp_country_code = emsp_country_code;
	}

	public String getEmsp_party_id() {
		return emsp_party_id;
	}

	public void setEmsp_party_id(String emsp_party_id) {
		this.emsp_party_id = emsp_party_id;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getParty_id() {
		return party_id;
	}

	public void setParty_id(String party_id) {
		this.party_id = party_id;
	}

	public Instant getStart_date_time() {
		return start_date_time;
	}

	public void setStart_date_time(Instant start_date_time) {
		this.start_date_time = start_date_time;
	}

	public Instant getEnd_date_time() {
		return end_date_time;
	}

	public void setEnd_date_time(Instant end_date_time) {
		this.end_date_time = end_date_time;
	}

	public void setLast_updated(Instant last_updated) {
		this.last_updated = last_updated;
	}

	public double getKwh() {
		return kwh;
	}

	public void setKwh(double kwh) {
		this.kwh = kwh;
	}

	public CPOSesionToken getCdr_token() {
		return cdr_token;
	}

	public void setCdr_token(CPOSesionToken cdr_token) {
		this.cdr_token = cdr_token;
	}

	public String getAuth_method() {
		return auth_method;
	}

	public void setAuth_method(String auth_method) {
		this.auth_method = auth_method;
	}

	public String getAuthorization_reference() {
		return authorization_reference;
	}

	public void setAuthorization_reference(String authorization_reference) {
		this.authorization_reference = authorization_reference;
	}

	public String getLocation_id() {
		return location_id;
	}

	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}

	public String getEvse_uid() {
		return evse_uid;
	}

	public void setEvse_uid(String evse_uid) {
		this.evse_uid = evse_uid;
	}

	public String getConnector_id() {
		return connector_id;
	}

	public void setConnector_id(String connector_id) {
		this.connector_id = connector_id;
	}

	public String getMeter_id() {
		return meter_id;
	}

	public void setMeter_id(String meter_id) {
		this.meter_id = meter_id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<CPOChargingPeriod> getCharging_periods() {
		return charging_periods;
	}

	public void setCharging_periods(List<CPOChargingPeriod> charging_periods) {
		this.charging_periods = charging_periods;
	}

	public CPOPrice getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(CPOPrice total_cost) {
		this.total_cost = total_cost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Instant getLast_updated() {
		return last_updated;
	}

	public String getAuth_id() {
		return auth_id;
	}

	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}

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

	public CPOLocation getLocation() {
		return location;
	}

	public void setLocation(CPOLocation location) {
		this.location = location;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CPOSessionModel [u_id=" + u_id + ", id=" + id + ", uid=" + uid + ", country_code=" + country_code
				+ ", party_id=" + party_id + ", cpo_country_code=" + cpo_country_code + ", cpo_party_id=" + cpo_party_id
				+ ", emsp_country_code=" + emsp_country_code + ", cpo_code=" + cpo_code + ", emsp_code=" + emsp_code
				+ ", emsp_party_id=" + emsp_party_id + ", auth_id=" + auth_id + ", start_date_time=" + start_date_time
				+ ", end_date_time=" + end_date_time + ", kwh=" + kwh + ", cdr_token=" + cdr_token + ", auth_method="
				+ auth_method + ", authorization_reference=" + authorization_reference + ", location_id=" + location_id
				+ ", evse_uid=" + evse_uid + ", connector_id=" + connector_id + ", meter_id=" + meter_id + ", currency="
				+ currency + ", charging_periods=" + charging_periods + ", total_cost=" + total_cost + ", status="
				+ status + ", location=" + location + ", last_updated=" + last_updated + ", version=" + version + "]";
	}

}
