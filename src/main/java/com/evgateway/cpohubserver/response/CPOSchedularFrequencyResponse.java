package com.evgateway.cpohubserver.response;

public class CPOSchedularFrequencyResponse {

	private String id;

	private String partyId;

	private String countryCode;

	// CPO/EMSP
	private String role;

	private String identifier;

	// must 15min/24hr
	private long frequency;

	public String getPartyId() {
		return partyId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "CPOSchedularFrequencyResponse [id=" + id + ", partyId=" + partyId + ", countryCode=" + countryCode
				+ ", role=" + role + ", identifier=" + identifier + ", frequency=" + frequency + "]";
	}

}
