package com.evgateway.cpohubserver.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.evgateway.cpohubserver.common.OCPIRolesResponse;

@Document(collection = "ocpi_cpo_credentials")
public class CPOCredentialModel {

	@Id
	private String id;

	private String token;

	private String access_token;

	private String url;

	private Set<OCPIRolesResponse> roles = new HashSet<OCPIRolesResponse>();

	private Instant lastupdated;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<OCPIRolesResponse> getRoles() {
		return roles;
	}

	public void setRoles(Set<OCPIRolesResponse> roles) {
		this.roles = roles;
	}

	public Instant getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Instant lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	@Override
	public String toString() {
		return "CPOCredentialModel [token=" + token + ", access_token=" + access_token + ", url=" + url + ", roles="
				+ roles + ", lastupdated=" + lastupdated + "]";
	}

}
