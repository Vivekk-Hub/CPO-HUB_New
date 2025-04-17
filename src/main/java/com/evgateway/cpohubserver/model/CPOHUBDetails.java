package com.evgateway.cpohubserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ocpi_cpohub_details")
public class CPOHUBDetails {

	@Id
	private String id;
	private String countryCode;
	private String partyId;
	private String partyName;
	private String website;
	private String publicToken;
	private String domain;
	private String tokenPrefix;
	private int tokenLength;
	private String partyRole;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPublicToken() {
		return publicToken;
	}

	public void setPublicToken(String publicToken) {
		this.publicToken = publicToken;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getTokenPrefix() {
		return tokenPrefix;
	}

	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}

	public int getTokenLength() {
		return tokenLength;
	}

	public void setTokenLength(int tokenLength) {
		this.tokenLength = tokenLength;
	}

	public String getPartyRole() {
		return partyRole;
	}

	public void setPartyRole(String partyRole) {
		this.partyRole = partyRole;
	}

	@Override
	public String toString() {
		return "CPOHUBDetails [id=" + id + ", countryCode=" + countryCode + ", partyId=" + partyId + ", partyName="
				+ partyName + ", website=" + website + ", publicToken=" + publicToken + ", domain=" + domain
				+ ", tokenPrefix=" + tokenPrefix + ", tokenLength=" + tokenLength + ", partyRole=" + partyRole + "]";
	}

}
