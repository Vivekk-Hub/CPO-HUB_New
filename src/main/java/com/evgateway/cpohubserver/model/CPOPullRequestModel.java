package com.evgateway.cpohubserver.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ocpi_cpohub_cpo_pull_request")
public class CPOPullRequestModel {

	@Id
	private String id;

	private String uuid;

	private String countryCode;

	private String partyId;

	private String partnerId;

	private String requestType;

	private Instant initiateTime;

	private int statusCode;

	private String status;

	private String message;

	private Instant completedTime;

	private String requestedBy;

	private String createdBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Instant getInitiateTime() {
		return initiateTime;
	}

	public void setInitiateTime(Instant initiateTime) {
		this.initiateTime = initiateTime;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Instant getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(Instant completedTime) {
		this.completedTime = completedTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "CPOPullRequestModel [id=" + id + ", uuid=" + uuid + ", countryCode=" + countryCode + ", partyId="
				+ partyId + ", partnerId=" + partnerId + ", requestType=" + requestType + ", initiateTime="
				+ initiateTime + ", statusCode=" + statusCode + ", status=" + status + ", message=" + message
				+ ", completedTime=" + completedTime + ", requestedBy=" + requestedBy + ", createdBy=" + createdBy
				+ "]";
	}
}