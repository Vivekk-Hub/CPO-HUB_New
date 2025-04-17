package com.evgateway.cpohubserver.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ocpi_cpo_session_activity")
public class CPOSessionsActivityModel {

	@Id
	private String uid;
	 
		private String emsp_country_code;
	 
		private String emsp_party_id;
	 
		private String emsp_code;
	 
		private String cpo_country_code;
	 
		private String cpo_party_id;
	 
		private String cpo_code;
	 
		private String token_uuid;
	 
		private String cpo_location_id;
	 
		private String evse_id;
	 
		private String cpo_evse_uid;
	 
		private String session_id;
	 
		private Instant session_start_date_time;
	 
		private Instant session_end_date_time;
	 
		private double total_energy;
	 
		private String status;
	 
		private Instant last_updated;
	 
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
	 
		public String getEmsp_code() {
			return emsp_code;
		}
	 
		public void setEmsp_code(String emsp_code) {
			this.emsp_code = emsp_code;
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
	 
		public String getCpo_location_id() {
			return cpo_location_id;
		}
	 
		public void setCpo_location_id(String cpo_location_id) {
			this.cpo_location_id = cpo_location_id;
		}
	 
		public String getCpo_evse_uid() {
			return cpo_evse_uid;
		}
	 
		public void setCpo_evse_uid(String cpo_evse_uid) {
			this.cpo_evse_uid = cpo_evse_uid;
		}
	 
		public String getSession_id() {
			return session_id;
		}
	 
		public void setSession_id(String session_id) {
			this.session_id = session_id;
		}
	 
		public Instant getSession_start_date_time() {
			return session_start_date_time;
		}
	 
		public void setSession_start_date_time(Instant session_start_date_time) {
			this.session_start_date_time = session_start_date_time;
		}
	 
		public Instant getSession_end_date_time() {
			return session_end_date_time;
		}
	 
		public void setSession_end_date_time(Instant session_end_date_time) {
			this.session_end_date_time = session_end_date_time;
		}
	 
		public double getTotal_energy() {
			return total_energy;
		}
	 
		public void setTotal_energy(double total_energy) {
			this.total_energy = total_energy;
		}
	 
		public String getStatus() {
			return status;
		}
	 
		public void setStatus(String status) {
			this.status = status;
		}
	 
		public Instant getLast_updated() {
			return last_updated;
		}
	 
		public void setLast_updated(Instant last_updated) {
			this.last_updated = last_updated;
		}
	 
		public String getEvse_id() {
			return evse_id;
		}
	 
		public void setEvse_id(String evse_id) {
			this.evse_id = evse_id;
		}
	 
		@Override
		public String toString() {
			return "CPOSessionsActivityModel [uid=" + uid + ", emsp_country_code=" + emsp_country_code + ", emsp_party_id="
					+ emsp_party_id + ", emsp_code=" + emsp_code + ", cpo_country_code=" + cpo_country_code
					+ ", cpo_party_id=" + cpo_party_id + ", cpo_code=" + cpo_code + ", token_uuid=" + token_uuid
					+ ", cpo_location_id=" + cpo_location_id + ", evse_id=" + evse_id + ", cpo_evse_uid=" + cpo_evse_uid
					+ ", session_id=" + session_id + ", session_start_date_time=" + session_start_date_time
					+ ", session_end_date_time=" + session_end_date_time + ", total_energy=" + total_energy + ", status="
					+ status + ", last_updated=" + last_updated + "]";
		}

}
