package com.evgateway.cpohubserver.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.evgateway.cpohubserver.form.CPODisplayText;
import com.evgateway.cpohubserver.form.CPOGeoLocation;
import com.evgateway.cpohubserver.form.CPOImage;
import com.evgateway.cpohubserver.form.CPOStatusSchedule;

@Document(collection = "ocpi_cpo_evse")
public class CPOEvseModel {

	@Id
	private String u_id;

	private String countrycode;

	private String partyid;

	private String cpo_country_code;

	private String cpo_party_id;

	private String uid;

	private String evse_id;

	private String cpo_evse_id;

	private String status;

	private String floor_level;

	private String physical_reference;

	private String[] parking_restrictions;

	private String[] capabilities;

	private String loc_ref_id;

	private String loc_uid;

	private List<CPOStatusSchedule> status_schedule = new ArrayList<>();

	private CPOGeoLocation coordinates;

	private List<CPODisplayText> directions = new ArrayList<CPODisplayText>();

	private List<CPOImage> images = new ArrayList<CPOImage>();

	private Instant last_updated;

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

	public Instant getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Instant last_updated) {
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

	public String getLoc_ref_id() {
		return loc_ref_id;
	}

	public void setLoc_ref_id(String loc_ref_id) {
		this.loc_ref_id = loc_ref_id;
	}

	public List<CPOImage> getImages() {
		return images;
	}

	public void setImages(List<CPOImage> images) {
		this.images = images;
	}

	public String getLoc_uid() {
		return loc_uid;
	}

	public void setLoc_uid(String loc_uid) {
		this.loc_uid = loc_uid;
	}

	public String getCpo_evse_id() {
		return cpo_evse_id;
	}

	public void setCpo_evse_id(String cpo_evse_id) {
		this.cpo_evse_id = cpo_evse_id;
	}

	public String getCpo_country_code() {
		return cpo_country_code;
	}

	public void setCpo_country_code(String cpo_country_code) {
		this.cpo_country_code = cpo_country_code;
	}

	public String getCpo_party_id() {
		return cpo_party_id;
	}

	public void setCpo_party_id(String cpo_party_id) {
		this.cpo_party_id = cpo_party_id;
	}

	@Override
	public String toString() {
		return "CPOEvseModel [u_id=" + u_id + ", countrycode=" + countrycode + ", partyid=" + partyid
				+ ", cpo_country_code=" + cpo_country_code + ", cpo_party_id=" + cpo_party_id + ", uid=" + uid
				+ ", evse_id=" + evse_id + ", cpo_evse_id=" + cpo_evse_id + ", status=" + status + ", floor_level="
				+ floor_level + ", physical_reference=" + physical_reference + ", parking_restrictions="
				+ Arrays.toString(parking_restrictions) + ", capabilities=" + Arrays.toString(capabilities)
				+ ", loc_ref_id=" + loc_ref_id + ", loc_uid=" + loc_uid + ", status_schedule=" + status_schedule
				+ ", coordinates=" + coordinates + ", directions=" + directions + ", images=" + images
				+ ", last_updated=" + last_updated + "]";
	}

}
