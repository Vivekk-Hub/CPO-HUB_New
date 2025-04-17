package com.evgateway.cpohubserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "ocpi_cpo_user_sessions")
public class UserSession {

	@Id
	private String id; // MongoDB ID
	private String userId; // User ID
	private String sessionId; // Session ID (UUID or JWT)
	private String ipAddress; // IP Address
	private DeviceInfo deviceInfo; // Nested object for device details
	private Instant loginTime; // Login timestamp
	private Instant logoutTime; // Logout timestamp
	private boolean isActive; // Whether the session is currently active

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public Instant getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Instant loginTime) {
		this.loginTime = loginTime;
	}

	public Instant getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Instant logoutTime) {
		this.logoutTime = logoutTime;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	// Nested DeviceInfo class
	public static class DeviceInfo {
		private String deviceType;
		private String browser;

		// Getters and Setters
		public String getDeviceType() {
			return deviceType;
		}

		public void setDeviceType(String deviceType) {
			this.deviceType = deviceType;
		}

		public String getBrowser() {
			return browser;
		}

		public void setBrowser(String browser) {
			this.browser = browser;
		}
	}

	@Override
	public String toString() {
		return "UserSession [id=" + id + ", userId=" + userId + ", sessionId=" + sessionId + ", ipAddress=" + ipAddress
				+ ", deviceInfo=" + deviceInfo + ", loginTime=" + loginTime + ", logoutTime=" + logoutTime
				+ ", isActive=" + isActive + "]";
	}

}
