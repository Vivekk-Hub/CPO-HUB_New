package com.evgateway.cpohubserver.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class CPOCdr {

	private String country_code;

	private String party_id;

	private String id;

	@Field("start_date_time")
	private Date start_date_time;

	@Field("end_date_time")
	private Date end_date_time;

	private String session_id;

	private CPOCdrToken cdr_token;

	private String auth_method;

	private String auth_id;

	private String authorization_reference;

	private CPOCdrLocation cdr_location;

	private String meter_id;

	private String currency;

	private List<CPOCdrTariff> tariffs = new ArrayList<CPOCdrTariff>();

	private List<CPOCdrChargingPeriod> charging_periods = new ArrayList<CPOCdrChargingPeriod>();

	private CPOCdrSignedData signed_data;

	private CPOCdrPrice total_cost;

	private CPOCdrPrice total_fixed_cost;

	private double total_energy;

	private CPOCdrPrice total_energy_cost;

	private double total_time;

	private CPOCdrPrice total_time_cost;

	private double total_parking_time;

	private CPOCdrPrice total_parking_cost;

	private CPOCdrPrice total_reservation_cost;

	private String remark;

	private String invoice_reference_id;

	private boolean credit;

	private String credit_reference_id;

	private String cdr_location_details;

	private Date last_updated;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStart_date_time() {
		return start_date_time;
	}

	public void setStart_date_time(Date start_date_time) {
		this.start_date_time = start_date_time;
	}

	public Date getEnd_date_time() {
		return end_date_time;
	}

	public void setEnd_date_time(Date end_date_time) {
		this.end_date_time = end_date_time;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public CPOCdrToken getCdr_token() {
		return cdr_token;
	}

	public void setCdr_token(CPOCdrToken cdr_token) {
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

	public CPOCdrLocation getCdr_location() {
		return cdr_location;
	}

	public void setCdr_location(CPOCdrLocation cdr_location) {
		this.cdr_location = cdr_location;
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

	public List<CPOCdrTariff> getTariffs() {
		return tariffs;
	}

	public void setTariffs(List<CPOCdrTariff> tariffs) {
		this.tariffs = tariffs;
	}

	public List<CPOCdrChargingPeriod> getCharging_periods() {
		return charging_periods;
	}

	public void setCharging_periods(List<CPOCdrChargingPeriod> charging_periods) {
		this.charging_periods = charging_periods;
	}

	public CPOCdrSignedData getSigned_data() {
		return signed_data;
	}

	public void setSigned_data(CPOCdrSignedData signed_data) {
		this.signed_data = signed_data;
	}

	public CPOCdrPrice getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(CPOCdrPrice total_cost) {
		this.total_cost = total_cost;
	}

	public CPOCdrPrice getTotal_fixed_cost() {
		return total_fixed_cost;
	}

	public void setTotal_fixed_cost(CPOCdrPrice total_fixed_cost) {
		this.total_fixed_cost = total_fixed_cost;
	}

	public double getTotal_energy() {
		return total_energy;
	}

	public void setTotal_energy(double total_energy) {
		this.total_energy = total_energy;
	}

	public CPOCdrPrice getTotal_energy_cost() {
		return total_energy_cost;
	}

	public void setTotal_energy_cost(CPOCdrPrice total_energy_cost) {
		this.total_energy_cost = total_energy_cost;
	}

	public double getTotal_time() {
		return total_time;
	}

	public void setTotal_time(double total_time) {
		this.total_time = total_time;
	}

	public CPOCdrPrice getTotal_time_cost() {
		return total_time_cost;
	}

	public void setTotal_time_cost(CPOCdrPrice total_time_cost) {
		this.total_time_cost = total_time_cost;
	}

	public double getTotal_parking_time() {
		return total_parking_time;
	}

	public void setTotal_parking_time(double total_parking_time) {
		this.total_parking_time = total_parking_time;
	}

	public CPOCdrPrice getTotal_parking_cost() {
		return total_parking_cost;
	}

	public void setTotal_parking_cost(CPOCdrPrice total_parking_cost) {
		this.total_parking_cost = total_parking_cost;
	}

	public CPOCdrPrice getTotal_reservation_cost() {
		return total_reservation_cost;
	}

	public void setTotal_reservation_cost(CPOCdrPrice total_reservation_cost) {
		this.total_reservation_cost = total_reservation_cost;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInvoice_reference_id() {
		return invoice_reference_id;
	}

	public void setInvoice_reference_id(String invoice_reference_id) {
		this.invoice_reference_id = invoice_reference_id;
	}

	public boolean isCredit() {
		return credit;
	}

	public void setCredit(boolean credit) {
		this.credit = credit;
	}

	public String getCredit_reference_id() {
		return credit_reference_id;
	}

	public void setCredit_reference_id(String credit_reference_id) {
		this.credit_reference_id = credit_reference_id;
	}

	public Date getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}

	public String getAuth_id() {
		return auth_id;
	}

	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}

	public String getCdr_location_details() {
		return cdr_location_details;
	}

	public void setCdr_location_details(String cdr_location_details) {
		this.cdr_location_details = cdr_location_details;
	}

}
