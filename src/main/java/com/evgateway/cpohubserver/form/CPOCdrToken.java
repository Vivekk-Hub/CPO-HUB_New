package com.evgateway.cpohubserver.form;

public class CPOCdrToken {

	private String uid;
	private String type;
	private String contract_id;
	private String country_code;
	private String party_id;

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

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
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

	@Override
	public String toString() {
		return "CdrToken [uid=" + uid + ", type=" + type + ", contract_id=" + contract_id + ", country_code="
				+ country_code + ", party_id=" + party_id + "]";
	}

}
