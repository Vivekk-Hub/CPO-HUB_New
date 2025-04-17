package com.evgateway.cpohubserver.form;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CPOEvse {

	private String u_id;
	private String countrycode;
	private String partyid;
	private String uid;
	private String evse_id;
	private String status;
	private String floor_level;
	private String physical_reference;
	private String[] parking_restrictions;
	private String[] capabilities;

	private Date last_updated;

	private List<CPOStatusSchedule> status_schedule;

	private List<CPOConnector> connectors;

	private CPOGeoLocation coordinates;

	private List<CPODisplayText> directions;

	private List<CPOImage> images;

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getPartyid() {
		return partyid;
	}

	public void setPartyid(String partyid) {
		this.partyid = partyid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getEvse_id() {
		return evse_id;
	}

	public void setEvse_id(String evse_id) {
		this.evse_id = evse_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFloor_level() {
		return floor_level;
	}

	public void setFloor_level(String floor_level) {
		this.floor_level = floor_level;
	}

	public String getPhysical_reference() {
		return physical_reference;
	}

	public void setPhysical_reference(String physical_reference) {
		this.physical_reference = physical_reference;
	}

	public String[] getParking_restrictions() {
		return parking_restrictions;
	}

	public void setParking_restrictions(String[] parking_restrictions) {
		this.parking_restrictions = parking_restrictions;
	}

	public String[] getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(String[] capabilities) {
		this.capabilities = capabilities;
	}

	public Date getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}

	public List<CPOStatusSchedule> getStatus_schedule() {
		return status_schedule;
	}

	public void setStatus_schedule(List<CPOStatusSchedule> status_schedule) {
		this.status_schedule = status_schedule;
	}

	public CPOGeoLocation getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(CPOGeoLocation coordinates) {
		this.coordinates = coordinates;
	}

	public List<CPODisplayText> getDirections() {
		return directions;
	}

	public void setDirections(List<CPODisplayText> directions) {
		this.directions = directions;
	}

	public List<CPOImage> getImages() {
		return images;
	}

	public void setImages(List<CPOImage> images) {
		this.images = images;
	}

	public List<CPOConnector> getConnectors() {
		return connectors;
	}

	public void setConnectors(List<CPOConnector> connectors) {
		this.connectors = connectors;
	}

	@Override
	public String toString() {
		return "CPOEvse [u_id=" + u_id + ", countrycode=" + countrycode + ", partyid=" + partyid + ", uid=" + uid
				+ ", evse_id=" + evse_id + ", status=" + status + ", floor_level=" + floor_level
				+ ", physical_reference=" + physical_reference + ", parking_restrictions="
				+ Arrays.toString(parking_restrictions) + ", capabilities=" + Arrays.toString(capabilities)
				+ ", last_updated=" + last_updated + ", status_schedule=" + status_schedule + ", connectors="
				+ connectors + ", coordinates=" + coordinates + ", directions=" + directions + "]";
	}

}
