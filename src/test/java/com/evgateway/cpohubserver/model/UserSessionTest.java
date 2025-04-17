// package com.evgateway.cpohubserver.model;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.time.Instant;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// public class UserSessionTest {

// 	private UserSession userSession;

// 	@BeforeEach
// 	public void setUp() {
// 		userSession = new UserSession();
// 	}

// 	@Test
// 	public void testSetAndGetId() {
// 		String id = "12345";
// 		userSession.setId(id);
// 		assertEquals(id, userSession.getId());
// 	}

// 	@Test
// 	public void testSetAndGetUserId() {
// 		String userId = "user_123";
// 		userSession.setUserId(userId);
// 		assertEquals(userId, userSession.getUserId());
// 	}

// 	@Test
// 	public void testSetAndGetSessionId() {
// 		String sessionId = "session_abc";
// 		userSession.setSessionId(sessionId);
// 		assertEquals(sessionId, userSession.getSessionId());
// 	}

// 	@Test
// 	public void testSetAndGetIpAddress() {
// 		String ipAddress = "192.168.0.1";
// 		userSession.setIpAddress(ipAddress);
// 		assertEquals(ipAddress, userSession.getIpAddress());
// 	}

// 	@Test
// 	public void testSetAndGetDeviceInfo() {
// 		UserSession.DeviceInfo deviceInfo = new UserSession.DeviceInfo();
// 		deviceInfo.setDeviceType("Mobile");
// 		deviceInfo.setBrowser("Chrome");

// 		userSession.setDeviceInfo(deviceInfo);
// 		assertEquals(deviceInfo, userSession.getDeviceInfo());
// 	}

// 	@Test
// 	public void testSetAndGetLoginTime() {
// 		Instant loginTime = Instant.now();
// 		userSession.setLoginTime(loginTime);
// 		assertEquals(loginTime, userSession.getLoginTime());
// 	}

// 	@Test
// 	public void testSetAndGetLogoutTime() {
// 		Instant logoutTime = Instant.now().plusSeconds(3600);
// 		userSession.setLogoutTime(logoutTime);
// 		assertEquals(logoutTime, userSession.getLogoutTime());
// 	}

// 	@Test
// 	public void testSetAndGetActive() {
// 		boolean isActive = true;
// 		userSession.setActive(isActive);
// 		assertTrue(userSession.isActive());
// 	}

// 	@Test
// 	public void testToString() {
// 		String id = "12345";
// 		String userId = "user_123";
// 		String sessionId = "session_abc";
// 		String ipAddress = "192.168.0.1";
// 		UserSession.DeviceInfo deviceInfo = new UserSession.DeviceInfo();
// 		deviceInfo.setDeviceType("Mobile");
// 		deviceInfo.setBrowser("Chrome");
// 		Instant loginTime = Instant.now();
// 		Instant logoutTime = Instant.now().plusSeconds(3600);
// 		boolean isActive = true;

// 		userSession.setId(id);
// 		userSession.setUserId(userId);
// 		userSession.setSessionId(sessionId);
// 		userSession.setIpAddress(ipAddress);
// 		userSession.setDeviceInfo(deviceInfo);
// 		userSession.setLoginTime(loginTime);
// 		userSession.setLogoutTime(logoutTime);
// 		userSession.setActive(isActive);

// 		String expectedString = "UserSession [id=12345, userId=user_123, sessionId=session_abc, ipAddress=192.168.0.1, deviceInfo="
// 				+ deviceInfo + ", loginTime=" + loginTime + ", logoutTime=" + logoutTime + ", isActive=true]";
// 		String result = userSession.toString();

// 		assertNotNull(result);
// 		assertTrue(result.contains("id=12345"));
// 		assertTrue(result.contains("userId=user_123"));
// 		assertTrue(result.contains("sessionId=session_abc"));
// 		assertTrue(result.contains("ipAddress=192.168.0.1"));
// 		assertTrue(result.contains("deviceInfo=" + deviceInfo));
// 		assertTrue(result.contains("loginTime=" + loginTime));
// 		assertTrue(result.contains("logoutTime=" + logoutTime));
// 		assertTrue(result.contains("isActive=true"));
// 	}

// 	// Nested DeviceInfo Test
// 	@Test
// 	public void testDeviceInfoSetAndGet() {
// 		UserSession.DeviceInfo deviceInfo = new UserSession.DeviceInfo();
// 		deviceInfo.setDeviceType("Mobile");
// 		deviceInfo.setBrowser("Chrome");

// 		assertEquals("Mobile", deviceInfo.getDeviceType());
// 		assertEquals("Chrome", deviceInfo.getBrowser());
// 	}
// }
