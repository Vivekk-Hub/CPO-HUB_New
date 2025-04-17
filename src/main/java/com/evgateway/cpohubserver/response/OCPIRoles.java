package com.evgateway.cpohubserver.response;

import com.evgateway.cpohubserver.common.BusinessDetails;

public class OCPIRoles {

	private String role;

	private String party_id;

	private String country_code;

	private BusinessDetails business_details;

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

	public BusinessDetails getBusiness_details() {
		return business_details;
	}

	public void setBusiness_details(BusinessDetails business_details) {
		this.business_details = business_details;
	}

	@Override
	public String toString() {
		return "OCPIRoles [role=" + role + ", party_id=" + party_id + ", country_code=" + country_code
				+ ", business_details=" + business_details + "]";
	}

}
