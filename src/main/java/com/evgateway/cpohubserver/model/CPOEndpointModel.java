package com.evgateway.cpohubserver.model;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ocpi_cpo_endpoints")
public class CPOEndpointModel {

	private String identifier;

	private String url;

	private String role;

	private String party_id;

	private String country_code;

	private String version;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Endpoint [identifier=" + identifier + ", url=" + url + ", role=" + role + ", party_id=" + party_id
				+ ", country_code=" + country_code + ", version=" + version + "]";
	}

}
