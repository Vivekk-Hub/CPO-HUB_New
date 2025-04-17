package com.evgateway.cpohubserver.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.evgateway.cpohubserver.form.CPORoles;


@Document(collection = "ocpi_cpohub_credentials")
public class CPOHUBCredentialModel {

	@Id
	private String id;

	private String token;

	private String accessToken;

	private String url;

	private String party_id;

	private String country_code;

	private boolean location_flag;

	private boolean tariff_flag;

	private boolean encodetoken;

	private boolean location;

	private boolean tariff;

	private boolean session;

	private boolean cdr;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public boolean isLocation_flag() {
		return location_flag;
	}

	public void setLocation_flag(boolean location_flag) {
		this.location_flag = location_flag;
	}

	public boolean isTariff_flag() {
		return tariff_flag;
	}

	public void setTariff_flag(boolean tariff_flag) {
		this.tariff_flag = tariff_flag;
	}

	public boolean isEncodetoken() {
		return encodetoken;
	}

	public void setEncodetoken(boolean encodetoken) {
		this.encodetoken = encodetoken;
	}

	public boolean isLocation() {
		return location;
	}

	public void setLocation(boolean location) {
		this.location = location;
	}

	public boolean isTariff() {
		return tariff;
	}

	public void setTariff(boolean tariff) {
		this.tariff = tariff;
	}

	public boolean isSession() {
		return session;
	}

	public void setSession(boolean session) {
		this.session = session;
	}

	public boolean isCdr() {
		return cdr;
	}

	public void setCdr(boolean cdr) {
		this.cdr = cdr;
	}

	@Override
	public String toString() {
		return "CPOHUBCredentialModel [id=" + id + ", token=" + token + ", accessToken=" + accessToken + ", url=" + url
				+ ", party_id=" + party_id + ", country_code=" + country_code + ", location_flag=" + location_flag
				+ ", tariff_flag=" + tariff_flag + ", encodetoken=" + encodetoken + ", location=" + location
				+ ", tariff=" + tariff + ", session=" + session + ", cdr=" + cdr + ", roles=" + roles + "]";
	}

}
