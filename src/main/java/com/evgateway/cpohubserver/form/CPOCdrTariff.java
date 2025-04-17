package com.evgateway.cpohubserver.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CPOCdrTariff {

	@Id
	@JsonProperty("u_id")
	private String u_id;

	private String country_code;

	private String party_id;

	private String id;

	private String currency;

	private String type;

	private String tariff_alt_url;

	private CPOCdrTariffPrice min_price;

	private CPOCdrTariffPrice max_price;

	private List<CPOCdrTariffAltText> tariff_alt_text = new ArrayList<CPOCdrTariffAltText>();

	private List<CPOCdrTariffElement> elements = new ArrayList<CPOCdrTariffElement>();

	@Field("start_date_time")
	private Date start_date_time;

	@Field("end_date_time")
	private Date end_date_time;

	private Date last_updated;

	@DBRef
	private CPOCdr cdr;

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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTariff_alt_url() {
		return tariff_alt_url;
	}

	public void setTariff_alt_url(String tariff_alt_url) {
		this.tariff_alt_url = tariff_alt_url;
	}

	public CPOCdrTariffPrice getMin_price() {
		return min_price;
	}

	public void setMin_price(CPOCdrTariffPrice min_price) {
		this.min_price = min_price;
	}

	public CPOCdrTariffPrice getMax_price() {
		return max_price;
	}

	public void setMax_price(CPOCdrTariffPrice max_price) {
		this.max_price = max_price;
	}

	public List<CPOCdrTariffElement> getElements() {
		return elements;
	}

	public void setElements(List<CPOCdrTariffElement> elements) {
		this.elements = elements;
	}

	public Date getStart_date_time() {
		return start_date_time;
	}

	public void setStart_date_time(Date start_date_time) {
		this.start_date_time = start_date_time;
	}

	public Date getEnd_date_time() {
		return end_date_time;
	}

	public void setEnd_date_time(Date end_date_time) {
		this.end_date_time = end_date_time;
	}

	public Date getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}

	public CPOCdr getCdr() {
		return cdr;
	}

	public void setCdr(CPOCdr cdr) {
		this.cdr = cdr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CPOCdrTariffAltText> getTariff_alt_text() {
		return tariff_alt_text;
	}

	public void setTariff_alt_text(List<CPOCdrTariffAltText> tariff_alt_text) {
		this.tariff_alt_text = tariff_alt_text;
	}

	@Override
	public String toString() {
		return "CPOCdrTariff [country_code=" + country_code + ", party_id=" + party_id + ", id=" + id + ", currency="
				+ currency + ", type=" + type + ", tariff_alt_url=" + tariff_alt_url + ", min_price=" + min_price
				+ ", max_price=" + max_price + ", tariff_alt_text=" + tariff_alt_text + ", elements=" + elements
				+ ", start_date_time=" + start_date_time + ", end_date_time=" + end_date_time + ", last_updated="
				+ last_updated + ", cdr=" + cdr + "]";
	}

}
