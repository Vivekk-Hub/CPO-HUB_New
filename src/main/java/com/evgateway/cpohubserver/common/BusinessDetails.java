package com.evgateway.cpohubserver.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class BusinessDetails {

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private String name;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private String website;

	@JsonIgnore
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Image logo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}

	@Override
	public String toString() {
		return "BusinessDetails [name=" + name + ", website=" + website + ", logo=" + logo + "]";
	}

}
