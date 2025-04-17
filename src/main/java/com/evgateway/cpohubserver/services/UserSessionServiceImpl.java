package com.evgateway.cpohubserver.services;

import java.time.Instant;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evgateway.cpohubserver.model.UserSession;
import com.evgateway.cpohubserver.repository.UserSessionRepository;

@Service
public class UserSessionServiceImpl implements UserSessionService {

	@Autowired
	private UserSessionRepository userSessionRepository;

	// Start a new session
	@Override
	public UserSession createSession(String userId, HttpServletRequest request, String token) {
		UserSession session = new UserSession();

		session.setSessionId(token);
		session.setUserId(userId);
		session.setIpAddress(getClientIp(request));
		session.setDeviceInfo(new UserSession.DeviceInfo());
		session.getDeviceInfo().setDeviceType(getDeviceType(request));
		session.getDeviceInfo().setBrowser(getBrowser(request));
		session.setLoginTime(Instant.now());
		session.setActive(true);

		return userSessionRepository.save(session);
	}

	// End an existing session
	@Override
	public void endSession(HttpServletRequest request) {

		String sessionId = parseJwt(request);

		UserSession session = userSessionRepository.findBySessionId(sessionId)
				.orElseThrow(() -> new RuntimeException("Session not found"));
		session.setLogoutTime(Instant.now());
		session.setActive(false);
		userSessionRepository.save(session);
	}

	// Get session history for a user
	@Override
	public List<UserSession> getSessionHistory(String userId) {
		return userSessionRepository.findByUserId(userId);
	}

	// Get all active sessions
	@Override
	public List<UserSession> getActiveSessions() {
		return userSessionRepository.findByIsActive(true);
	}

	public String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
			// Handle cases where multiple IPs are in the header
			return ip.split(",")[0];
		}
		ip = request.getHeader("Proxy-Client-IP");
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public String getDeviceType(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent").toLowerCase();

		if (userAgent.contains("mobile")) {
			return "Mobile";
		} else if (userAgent.contains("tablet")) {
			return "Tablet";
		} else {
			return "Desktop";
		}
	}

	public String getBrowser(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent").toLowerCase();

		if (userAgent.contains("chrome")) {
			return "Chrome";
		} else if (userAgent.contains("firefox")) {
			return "Firefox";
		} else if (userAgent.contains("safari") && !userAgent.contains("chrome")) {
			return "Safari";
		} else if (userAgent.contains("edge")) {
			return "Edge";
		} else if (userAgent.contains("opera") || userAgent.contains("opr")) {
			return "Opera";
		} else if (userAgent.contains("msie") || userAgent.contains("trident")) {
			return "Internet Explorer";
		} else {
			return "Unknown Browser";
		}
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}

		return null;
	}
}
