package com.evgateway.cpohubserver.form;

public class CPOAdditionalGeoLocation {

	private String latitude;

	private String longitude;

	private CPODisplayText name;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public CPODisplayText getName() {
		return name;
	}

	public void setName(CPODisplayText name) {
		this.name = name;
	}

}
