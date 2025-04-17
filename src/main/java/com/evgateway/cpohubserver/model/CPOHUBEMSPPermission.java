package com.evgateway.cpohubserver.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ocpi_cpohub_emsp_permission")
public class CPOHUBEMSPPermission {

	@Id
	private String id;

	private String emsp_party_id;

	private List<String> cpo_partyIds;

	private List<String> location_country_names;
	
	private List<String> modules;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmsp_party_id() {
		return emsp_party_id;
	}

	public void setEmsp_party_id(String emsp_party_id) {
		this.emsp_party_id = emsp_party_id;
	}

	public List<String> getCpo_partyIds() {
		return cpo_partyIds;
	}

	public void setCpo_partyIds(List<String> cpo_partyIds) {
		this.cpo_partyIds = cpo_partyIds;
	}

	public List<String> getLocation_country_names() {
		return location_country_names;
	}

	public void setLocation_country_names(List<String> location_country_names) {
		this.location_country_names = location_country_names;
	}

	public List<String> getModules() {
		return modules;
	}

	public void setModules(List<String> modules) {
		this.modules = modules;
	}

	@Override
	public String toString() {
		return "CPOHUBEMSPPermission [id=" + id + ", emsp_party_id=" + emsp_party_id + ", cpo_partyIds=" + cpo_partyIds
				+ ", location_country_names=" + location_country_names + ", modules=" + modules + "]";
	}
	
	

}

