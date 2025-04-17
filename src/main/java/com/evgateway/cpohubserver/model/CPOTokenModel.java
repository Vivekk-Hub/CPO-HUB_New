package com.evgateway.cpohubserver.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.evgateway.cpohubserver.form.CPOEnergyContract;

@Document(collection = "ocpi_cpo_token")
public class CPOTokenModel {

	@Id
	private String id;

	private String party_id;

	private String country_code;

	private String uid;

	private String auth_token;

	private String type;

	private String emsp_code;

	private String auth_id;

	private String contract_id;

	private String visual_number;

	private String group_id;

	private String issuer;

	private boolean valid;

	private String whitelist;

	private String language;

	private String default_profile_type;

	private CPOEnergyContract energy_contract;

	private Instant last_updated;

	private String version;

	public String getAuth_token() {
		return auth_token;
	}

	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}

	public String getEmsp_code() {
		return emsp_code;
	}

	public void setEmsp_code(String emsp_code) {
		this.emsp_code = emsp_code;
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

	public String getVisual_number() {
		return visual_number;
	}

	public void setVisual_number(String visual_number) {
		this.visual_number = visual_number;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getWhitelist() {
		return whitelist;
	}

	public void setWhitelist(String whitelist) {
		this.whitelist = whitelist;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Instant getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Instant last_updated) {
		this.last_updated = last_updated;
	}

	public String getParty_id() {
		return party_id;
	}

	public void setParty_id(String party_id) {
		this.party_id = party_id;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getDefault_profile_type() {
		return default_profile_type;
	}

	public void setDefault_profile_type(String default_profile_type) {
		this.default_profile_type = default_profile_type;
	}

	public CPOEnergyContract getEnergy_contract() {
		return energy_contract;
	}

	public void setEnergy_contract(CPOEnergyContract energy_contract) {
		this.energy_contract = energy_contract;
	}

	public String getAuth_id() {
		return auth_id;
	}

	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "CPOTokenModel [id=" + id + ", party_id=" + party_id + ", country_code=" + country_code + ", uid=" + uid
				+ ", auth_token=" + auth_token + ", type=" + type + ", emsp_code=" + emsp_code + ", auth_id=" + auth_id
				+ ", contract_id=" + contract_id + ", visual_number=" + visual_number + ", group_id=" + group_id
				+ ", issuer=" + issuer + ", valid=" + valid + ", whitelist=" + whitelist + ", language=" + language
				+ ", default_profile_type=" + default_profile_type + ", energy_contract=" + energy_contract
				+ ", last_updated=" + last_updated + ", version=" + version + "]";
	}
}
