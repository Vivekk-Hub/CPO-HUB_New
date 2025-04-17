package com.evgateway.cpohubserver.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ocpi_cpo_versions")
public class CPOVersionModel {

	private String version;

	private String url;

	private String party_id;

	private String country_code;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	@Override
	public String toString() {
		return "Version [version=" + version + ", url=" + url + ", party_id=" + party_id + ", country_code="
				+ country_code + "]";
	}

}
