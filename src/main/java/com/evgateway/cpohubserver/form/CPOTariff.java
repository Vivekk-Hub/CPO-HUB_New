package com.evgateway.cpohubserver.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CPOTariff {

	private String country_code;

	private String party_id;

	private String id;

	private String currency;

	private String type;

	private String tariff_alt_url;

	private CPOTariffPrice min_price;

	private CPOTariffPrice max_price;

	private List<CPOTariffAltText> tariff_alt_text = new ArrayList<CPOTariffAltText>();

	private List<CPOTariffElement> elements = new ArrayList<CPOTariffElement>();

	private Date start_date_time;

	private Date end_date_time;

	private Date last_updated;

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

	public CPOTariffPrice getMin_price() {
		return min_price;
	}

	public void setMin_price(CPOTariffPrice min_price) {
		this.min_price = min_price;
	}

	public CPOTariffPrice getMax_price() {
		return max_price;
	}

	public void setMax_price(CPOTariffPrice max_price) {
		this.max_price = max_price;
	}

	public List<CPOTariffElement> getElements() {
		return elements;
	}

	public void setElements(List<CPOTariffElement> elements) {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CPOTariffAltText> getTariff_alt_text() {
		return tariff_alt_text;
	}

	public void setTariff_alt_text(List<CPOTariffAltText> tariff_alt_text) {
		this.tariff_alt_text = tariff_alt_text;
	}

}
