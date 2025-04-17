package com.evgateway.cpohubserver.response;

import java.util.ArrayList;
import java.util.List;

import com.evgateway.cpohubserver.form.CPORoles;

public class CPOCredential {

	private String token;

	private String accessToken;

	private String url;

	private List<CPORoles> roles = new ArrayList<CPORoles>();

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

	public String getAccessToken() {
		return accessToken;
	}

	public List<CPORoles> getRoles() {
		return roles;
	}

	public void setRoles(List<CPORoles> roles) {
		this.roles = roles;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "CPOCredential [token=" + token + ", accessToken=" + accessToken + ", url=" + url + ", roles=" + roles
				+ "]";
	}

}
