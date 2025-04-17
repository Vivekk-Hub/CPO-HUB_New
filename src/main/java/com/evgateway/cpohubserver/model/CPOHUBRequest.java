package com.evgateway.cpohubserver.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ocpi_cpohub_request")
public class CPOHUBRequest {

	@Id
	private String uid;

	private String emsp_country_code;

	private String emsp_party_id;

	private String cpo_country_code;

	private String cpo_party_id;

	private String emsp_code;

	private String cpo_code;

	private String token_uuid;

	private String cpo_evse_uid;

	private String cpo_location_id;

	private String status;

	private String reason;

	private String response_url;

	private String response_uuid;

	private String request_type;

	private String session_id;

	private String version;

	private Instant last_updated;

	public CPOHUBRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CPOHUBRequest(String uid, String emsp_country_code, String emsp_party_id, String cpo_country_code,
			String cpo_party_id, String emsp_code, String cpo_code, String token_uuid, String cpo_evse_uid,
			String cpo_location_id, String status, String reason, String response_url, String response_uuid,
			String request_type, String session_id, String version, Instant last_updated) {
		super();
		this.uid = uid;
		this.emsp_country_code = emsp_country_code;
		this.emsp_party_id = emsp_party_id;
		this.cpo_country_code = cpo_country_code;
		this.cpo_party_id = cpo_party_id;
		this.emsp_code = emsp_code;
		this.cpo_code = cpo_code;
		this.token_uuid = token_uuid;
		this.cpo_evse_uid = cpo_evse_uid;
		this.cpo_location_id = cpo_location_id;
		this.status = status;
		this.reason = reason;
		this.response_url = response_url;
		this.response_uuid = response_uuid;
		this.request_type = request_type;
		this.session_id = session_id;
		this.version = version;
		this.last_updated = last_updated;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getEmsp_country_code() {
		return emsp_country_code;
	}

	public void setEmsp_country_code(String emsp_country_code) {
		this.emsp_country_code = emsp_country_code;
	}

	public String getEmsp_party_id() {
		return emsp_party_id;
	}

	public void setEmsp_party_id(String emsp_party_id) {
		this.emsp_party_id = emsp_party_id;
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

	public String getEmsp_code() {
		return emsp_code;
	}

	public void setEmsp_code(String emsp_code) {
		this.emsp_code = emsp_code;
	}

	public String getCpo_code() {
		return cpo_code;
	}

	public void setCpo_code(String cpo_code) {
		this.cpo_code = cpo_code;
	}

	public String getToken_uuid() {
		return token_uuid;
	}

	public void setToken_uuid(String token_uuid) {
		this.token_uuid = token_uuid;
	}

	public String getCpo_evse_uid() {
		return cpo_evse_uid;
	}

	public void setCpo_evse_uid(String cpo_evse_uid) {
		this.cpo_evse_uid = cpo_evse_uid;
	}

	public String getCpo_location_id() {
		return cpo_location_id;
	}

	public void setCpo_location_id(String cpo_location_id) {
		this.cpo_location_id = cpo_location_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getResponse_url() {
		return response_url;
	}

	public void setResponse_url(String response_url) {
		this.response_url = response_url;
	}

	public String getResponse_uuid() {
		return response_uuid;
	}

	public void setResponse_uuid(String response_uuid) {
		this.response_uuid = response_uuid;
	}

	public String getRequest_type() {
		return request_type;
	}

	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Instant getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Instant last_updated) {
		this.last_updated = last_updated;
	}

	@Override
	public String toString() {
		return "CPOHUBRequest [uid=" + uid + ", emsp_country_code=" + emsp_country_code + ", emsp_party_id="
				+ emsp_party_id + ", cpo_country_code=" + cpo_country_code + ", cpo_party_id=" + cpo_party_id
				+ ", emsp_code=" + emsp_code + ", cpo_code=" + cpo_code + ", token_uuid=" + token_uuid
				+ ", cpo_evse_uid=" + cpo_evse_uid + ", cpo_location_id=" + cpo_location_id + ", status=" + status
				+ ", reason=" + reason + ", response_url=" + response_url + ", response_uuid=" + response_uuid
				+ ", request_type=" + request_type + ", session_id=" + session_id + ", version=" + version
				+ ", last_updated=" + last_updated + ", getUid()=" + getUid() + ", getEmsp_country_code()="
				+ getEmsp_country_code() + ", getEmsp_party_id()=" + getEmsp_party_id() + ", getCpo_country_code()="
				+ getCpo_country_code() + ", getCpo_party_id()=" + getCpo_party_id() + ", getEmsp_code()="
				+ getEmsp_code() + ", getCpo_code()=" + getCpo_code() + ", getToken_uuid()=" + getToken_uuid()
				+ ", getCpo_evse_uid()=" + getCpo_evse_uid() + ", getCpo_location_id()=" + getCpo_location_id()
				+ ", getStatus()=" + getStatus() + ", getReason()=" + getReason() + ", getResponse_url()="
				+ getResponse_url() + ", getResponse_uuid()=" + getResponse_uuid() + ", getRequest_type()="
				+ getRequest_type() + ", getSession_id()=" + getSession_id() + ", getVersion()=" + getVersion()
				+ ", getLast_updated()=" + getLast_updated() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
