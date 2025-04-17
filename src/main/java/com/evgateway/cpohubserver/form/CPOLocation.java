package com.evgateway.cpohubserver.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;



public class CPOLocation {

	private String id;

	private String type;

	private String country_code;

	private String party_id;

	private boolean publish;

	private String name;

	private String address;

	private String city;

	private String state;

	private String postal_code;

	private String country;
	private String parking_type;
	private String[] facilities;
	private boolean charging_when_closed;
	private String time_zone;
	private boolean open24x7;

	private Date last_updated;

	private List<CPOPublishTokenType> publish_allowed_to = new ArrayList<CPOPublishTokenType>();

	private CPOGeoLocation coordinates;

	private List<CPOAdditionalGeoLocation> related_locations = new ArrayList<CPOAdditionalGeoLocation>();

	private List<CPOEvse> evses = new ArrayList<CPOEvse>();

	private List<CPODisplayText> directions = new ArrayList<CPODisplayText>();

	private CPOBusinessDetails operator;

	private CPOBusinessDetails suboperator;

	private CPOBusinessDetails owner;

	private CPOHours opening_times;

	private List<CPOImage> images = new ArrayList<CPOImage>();

	private CPOEnergyMix energy_mix;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getParking_type() {
		return parking_type;
	}

	public void setParking_type(String parking_type) {
		this.parking_type = parking_type;
	}

	public String[] getFacilities() {
		return facilities;
	}

	public void setFacilities(String[] facilities) {
		this.facilities = facilities;
	}

	public boolean isCharging_when_closed() {
		return charging_when_closed;
	}

	public void setCharging_when_closed(boolean charging_when_closed) {
		this.charging_when_closed = charging_when_closed;
	}

	public String getTime_zone() {
		return time_zone;
	}

	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}

	public boolean isOpen24x7() {
		return open24x7;
	}

	public void setOpen24x7(boolean open24x7) {
		this.open24x7 = open24x7;
	}

	public Date getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}

	public List<CPOPublishTokenType> getPublish_allowed_to() {
		return publish_allowed_to;
	}

	public void setPublish_allowed_to(List<CPOPublishTokenType> publish_allowed_to) {
		this.publish_allowed_to = publish_allowed_to;
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

	public List<CPOEvse> getEvses() {
		return evses;
	}

	public void setEvses(List<CPOEvse> evses) {
		this.evses = evses;
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

	public CPOHours getOpening_times() {
		return opening_times;
	}

	public void setOpening_times(CPOHours opening_times) {
		this.opening_times = opening_times;
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

	@Override
	public String toString() {
		return "CPOLocation [id=" + id + ", type=" + type + ", country_code=" + country_code + ", party_id=" + party_id
				+ ", publish=" + publish + ", name=" + name + ", address=" + address + ", city=" + city + ", state="
				+ state + ", postal_code=" + postal_code + ", country=" + country + ", parking_type=" + parking_type
				+ ", facilities=" + Arrays.toString(facilities) + ", charging_when_closed=" + charging_when_closed
				+ ", time_zone=" + time_zone + ", open24x7=" + open24x7 + ", last_updated=" + last_updated
				+ ", publish_allowed_to=" + publish_allowed_to + ", coordinates=" + coordinates + ", related_locations="
				+ related_locations + ", evses=" + evses + ", directions=" + directions + ", operator=" + operator
				+ ", suboperator=" + suboperator + ", owner=" + owner + ", opening_times=" + opening_times
				+ ",energy_mix=" + energy_mix + "]";
	}

}