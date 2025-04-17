package com.evgateway.cpohubserver.model;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.evgateway.cpohubserver.form.CPOAdditionalGeoLocation;
import com.evgateway.cpohubserver.form.CPOBusinessDetails;
import com.evgateway.cpohubserver.form.CPODisplayText;
import com.evgateway.cpohubserver.form.CPOEnergyMix;
import com.evgateway.cpohubserver.form.CPOGeoLocation;
import com.evgateway.cpohubserver.form.CPOHours;
import com.evgateway.cpohubserver.form.CPOImage;
import com.evgateway.cpohubserver.form.CPOPublishTokenType;



@Document(collection = "ocpi_cpo_location")
public class CPOLocationModel {

	@Id
	private String u_id;

	private String id;

	private String uid;

	private String type;

	private String country_code;

	private String party_id;

	private String cpo_country_code;

	private String cpo_party_id;
	
	private String cpo_code;

	private boolean publish;

	private String name;

	private String address;

	private String city;

	private String state;

	private String province;

	private String postal_code;

	private String country;

	private CPOGeoLocation coordinates;

	private List<CPOAdditionalGeoLocation> related_locations = new ArrayList<CPOAdditionalGeoLocation>();

	private List<CPOPublishTokenType> publish_allowed_to = new ArrayList<CPOPublishTokenType>();

	private String parking_type;

	private List<CPODisplayText> directions = new ArrayList<CPODisplayText>();

	private CPOBusinessDetails operator;

	private CPOBusinessDetails suboperator;

	private CPOBusinessDetails owner;

	private String[] facilities;

	private String time_zone;

	private CPOHours opening_times;

	private boolean charging_when_closed;

	private List<CPOImage> images = new ArrayList<CPOImage>();

	private CPOEnergyMix energy_mix;

	private boolean open24x7;

	private Instant last_updated;

	public String getCpo_code() {
		return cpo_code;
	}

	public void setCpo_code(String cpo_code) {
		this.cpo_code = cpo_code;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getParty_id() {
		return party_id;
	}

	public void setParty_id(String party_id) {
		this.party_id = party_id;
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

	public boolean isPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
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

	public List<CPOAdditionalGeoLocation> getRelated_locations() {
		return related_locations;
	}

	public void setRelated_locations(List<CPOAdditionalGeoLocation> related_locations) {
		this.related_locations = related_locations;
	}

	public List<CPOPublishTokenType> getPublish_allowed_to() {
		return publish_allowed_to;
	}

	public void setPublish_allowed_to(List<CPOPublishTokenType> publish_allowed_to) {
		this.publish_allowed_to = publish_allowed_to;
	}

	public String getParking_type() {
		return parking_type;
	}

	public void setParking_type(String parking_type) {
		this.parking_type = parking_type;
	}

	public List<CPODisplayText> getDirections() {
		return directions;
	}

	public void setDirections(List<CPODisplayText> directions) {
		this.directions = directions;
	}

	public CPOBusinessDetails getOperator() {
		return operator;
	}

	public void setOperator(CPOBusinessDetails operator) {
		this.operator = operator;
	}

	public CPOBusinessDetails getSuboperator() {
		return suboperator;
	}

	public void setSuboperator(CPOBusinessDetails suboperator) {
		this.suboperator = suboperator;
	}

	public CPOBusinessDetails getOwner() {
		return owner;
	}

	public void setOwner(CPOBusinessDetails owner) {
		this.owner = owner;
	}

	public String[] getFacilities() {
		return facilities;
	}

	public void setFacilities(String[] facilities) {
		this.facilities = facilities;
	}

	public String getTime_zone() {
		return time_zone;
	}

	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}

	public CPOHours getOpening_times() {
		return opening_times;
	}

	public void setOpening_times(CPOHours opening_times) {
		this.opening_times = opening_times;
	}

	public boolean isCharging_when_closed() {
		return charging_when_closed;
	}

	public void setCharging_when_closed(boolean charging_when_closed) {
		this.charging_when_closed = charging_when_closed;
	}

	public List<CPOImage> getImages() {
		return images;
	}

	public void setImages(List<CPOImage> images) {
		this.images = images;
	}

	public CPOEnergyMix getEnergy_mix() {
		return energy_mix;
	}

	public void setEnergy_mix(CPOEnergyMix energy_mix) {
		this.energy_mix = energy_mix;
	}

	public boolean isOpen24x7() {
		return open24x7;
	}

	public void setOpen24x7(boolean open24x7) {
		this.open24x7 = open24x7;
	}

	public Instant getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Instant last_updated) {
		this.last_updated = last_updated;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "CPOLocationModel [u_id=" + u_id + ", id=" + id + ", uid=" + uid + ", type=" + type + ", country_code="
				+ country_code + ", party_id=" + party_id + ", cpo_country_code=" + cpo_country_code + ", cpo_party_id="
				+ cpo_party_id + ", cpo_code=" + cpo_code + ", publish=" + publish + ", name=" + name + ", address="
				+ address + ", city=" + city + ", state=" + state + ", province=" + province + ", postal_code="
				+ postal_code + ", country=" + country + ", coordinates=" + coordinates + ", related_locations="
				+ related_locations + ", publish_allowed_to=" + publish_allowed_to + ", parking_type=" + parking_type
				+ ", directions=" + directions + ", operator=" + operator + ", suboperator=" + suboperator + ", owner="
				+ owner + ", facilities=" + Arrays.toString(facilities) + ", time_zone=" + time_zone
				+ ", opening_times=" + opening_times + ", charging_when_closed=" + charging_when_closed + ", images="
				+ images + ", energy_mix=" + energy_mix + ", open24x7=" + open24x7 + ", last_updated=" + last_updated
				+ "]";
	}

}