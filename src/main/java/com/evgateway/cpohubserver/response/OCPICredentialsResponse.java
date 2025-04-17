package com.evgateway.cpohubserver.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OCPICredentialsResponse {

	private String token;

	private String url;

	private List<OCPIRoles> roles;

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

	public List<OCPIRoles> getRoles() {
		return roles;
	}

	public void setRoles(List<OCPIRoles> roleslIst) {
		this.roles = roleslIst;
	}

	@Override
	public String toString() {
		return "OCPICredentialsResponse [token=" + token + ", url=" + url + ", roles=" + roles + "]";
	}

}
