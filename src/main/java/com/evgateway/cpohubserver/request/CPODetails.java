package com.evgateway.cpohubserver.request;

public class CPODetails {

	private String url;

	private String token;

	private String version_number;

	private String preFix;

	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getVersion_number() {
		return version_number;
	}

	public void setVersion_number(String version_number) {
		this.version_number = version_number;
	}

	public String getPreFix() {
		return preFix;
	}

	public void setPreFix(String preFix) {
		this.preFix = preFix;
	}

	@Override
	public String toString() {
		return "CPODetails [url=" + url + ", token=" + token + ", version_number=" + version_number + ", preFix="
				+ preFix + ", role=" + role + "]";
	}

}
