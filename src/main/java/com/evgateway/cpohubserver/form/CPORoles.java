package com.evgateway.cpohubserver.form;

public class CPORoles {

	private String party_id;

	private String country_code;

	private String role;

	private CPOBusinessDetails business_details;

	public String getParty_id() {
		return party_id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public CPOBusinessDetails getBusiness_details() {
		return business_details;
	}

	public void setBusiness_details(CPOBusinessDetails business_details) {
		this.business_details = business_details;
	}

}
