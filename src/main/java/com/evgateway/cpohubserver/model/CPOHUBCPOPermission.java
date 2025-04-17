package com.evgateway.cpohubserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ocpi_cpohub_cpo_permisssion")
public class CPOHUBCPOPermission {

	@Id
	private String id;

	private String party_id;

	private String[] modules;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParty_id() {
		return party_id;
	}

	public void setParty_id(String party_id) {
		this.party_id = party_id;
	}

	public String[] getModules() {
		return modules;
	}

	public void setModules(String[] modules) {
		this.modules = modules;
	}

	@Override
	public String toString() {
		return "CPOPermission [id=" + id + ", party_id=" + party_id + ", modules=" + modules + "]";
	}

}

