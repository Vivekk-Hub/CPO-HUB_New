package com.evgateway.cpohubserver.form;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CPOCdrLocation {

	@Id
	@JsonProperty("u_id")
	private String u_id;

	private String name;

	private String id;

	private String address;

	private String city;

	private String postal_code;

	private String state;

	private String country;

	private CPOGeoLocation coordinates;

	private String evse_uid;

	private String evse_id;

	private String cpo_evse_id;

	private String connector_id;

	private String connector_standard;

	private String connector_format;

	private String connector_power_type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public CPOGeoLocation getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(CPOGeoLocation coordinates) {
		this.coordinates = coordinates;
	}

	public String getEvse_uid() {
		return evse_uid;
	}

	public void setEvse_uid(String evse_uid) {
		this.evse_uid = evse_uid;
	}

	public String getEvse_id() {
		return evse_id;
	}

	public void setEvse_id(String evse_id) {
		this.evse_id = evse_id;
	}

	public String getConnector_id() {
		return connector_id;
	}

	public void setConnector_id(String connector_id) {
		this.connector_id = connector_id;
	}

	public String getConnector_standard() {
		return connector_standard;
	}

	public void setConnector_standard(String connector_standard) {
		this.connector_standard = connector_standard;
	}

	public String getConnector_format() {
		return connector_format;
	}

	public void setConnector_format(String connector_format) {
		this.connector_format = connector_format;
	}

	public String getConnector_power_type() {
		return connector_power_type;
	}

	public void setConnector_power_type(String connector_power_type) {
		this.connector_power_type = connector_power_type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getCpo_evse_id() {
		return cpo_evse_id;
	}

	public void setCpo_evse_id(String cpo_evse_id) {
		this.cpo_evse_id = cpo_evse_id;
	}

	@Override
	public String toString() {
		return "CPOCdrLocation [u_id=" + u_id + ", name=" + name + ", id=" + id + ", address=" + address + ", city="
				+ city + ", postal_code=" + postal_code + ", state=" + state + ", country=" + country + ", coordinates="
				+ coordinates + ", evse_uid=" + evse_uid + ", evse_id=" + evse_id + ", cpo_evse_id=" + cpo_evse_id
				+ ", connector_id=" + connector_id + ", connector_standard=" + connector_standard
				+ ", connector_format=" + connector_format + ", connector_power_type=" + connector_power_type + "]";
	}

}
