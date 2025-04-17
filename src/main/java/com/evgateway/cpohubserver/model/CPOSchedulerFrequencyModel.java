package com.evgateway.cpohubserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ocpi_cpohub_cpo_scheduler_frequency")
public class CPOSchedulerFrequencyModel {

	@Id
	private String id;

	private String partyId;

	private String countryCode;

	private String partnerId;

	// CPO/EMSP
	private String role;

	// baseUrl
	private String endpoint;

	private String identifier;

	// must 15min/24hr
	private long frequency;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public long getFrequency() {
		return frequency;
	}

	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	@Override
	public String toString() {
		return "CPOSchedulerFrequencyModel [id=" + id + ", partyId=" + partyId + ", countryCode=" + countryCode
				+ ", partnerId=" + partnerId + ", role=" + role + ", endpoint=" + endpoint + ", identifier="
				+ identifier + ", frequency=" + frequency + "]";
	}

}
