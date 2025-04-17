package com.evgateway.cpohubserver.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.evgateway.cpohubserver.cnum.Status;

@Document(collection = "ocpi_cpohub_partner")
public class CPOHUBPartnerModel {

	@Id
	private String id;

	// private String partner_name;

	private String party_id;

	private String country_code;

	private Instant first_contact_date; // credetial_exchange_date

	private Long total_data_exchange;

	private Status status;

	private Instant last_activity;

	private Long total_down_time;

	private String version;

	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public String getPartner_name() {
//		return partner_name;
//	}
//
//	public void setPartner_name(String partner_name) {
//		this.partner_name = partner_name;
//	}

	public String getversion() {
		return version;
	}

	public void setversion(String version) {
		this.version = version;
	}

	public String getParty_id() {
		return party_id;
	}

	public Long getTotal_down_time() {
		return total_down_time;
	}

	public void setTotal_down_time(Long total_down_time) {
		this.total_down_time = total_down_time;
	}

	public void setParty_id(String party_id) {
		this.party_id = party_id;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public Instant getFirst_contact_date() {
		return first_contact_date;
	}

	public void setFirst_contact_date(Instant first_contact_date) {
		this.first_contact_date = first_contact_date;
	}

	public Long getTotal_data_exchange() {
		return total_data_exchange;
	}

	public void setTotal_data_exchange(Long total_data_exchange) {
		this.total_data_exchange = total_data_exchange;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Instant getlast_activity() {
		return last_activity;
	}

	public void setlast_activity(Instant last_activity) {
		this.last_activity = last_activity;
	}

	@Override
	public String toString() {
		return "CPOHUBPartnerModel [id=" + id + ", party_id=" + party_id + ", country_code=" + country_code
				+ ", first_contact_date=" + first_contact_date + ", total_data_exchange=" + total_data_exchange
				+ ", status=" + status + ", last_activity=" + last_activity + ", total_down_time=" + total_down_time
				+ ", version=" + version + ", role=" + role + "]";
	}

}
