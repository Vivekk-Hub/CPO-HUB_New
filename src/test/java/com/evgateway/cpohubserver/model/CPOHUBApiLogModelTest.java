// package com.evgateway.cpohubserver.model;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.time.LocalDateTime;

// import org.junit.jupiter.api.Test;

// class CPOHUBApiLogModelTest {

// 	@Test
// 	public void testSetAndGetId() {
// 		CPOHUBApiLogModel model = new CPOHUBApiLogModel();
// 		String id = "log123";
// 		model.setId(id);
// 		assertEquals(id, model.getId());
// 	}

// 	@Test
// 	public void testSetAndGetRequestUri() {
// 		CPOHUBApiLogModel model = new CPOHUBApiLogModel();
// 		String requestUri = "/api/test";
// 		model.setRequestUri(requestUri);
// 		assertEquals(requestUri, model.getRequestUri());
// 	}

// 	@Test
// 	public void testSetAndGetHttpMethod() {
// 		CPOHUBApiLogModel model = new CPOHUBApiLogModel();
// 		String httpMethod = "POST";
// 		model.setHttpMethod(httpMethod);
// 		assertEquals(httpMethod, model.getHttpMethod());
// 	}

// 	@Test
// 	public void testSetAndGetRequestBody() {
// 		CPOHUBApiLogModel model = new CPOHUBApiLogModel();
// 		String requestBody = "{\"key\":\"value\"}";
// 		model.setRequestBody(requestBody);
// 		assertEquals(requestBody, model.getRequestBody());
// 	}

// 	@Test
// 	public void testSetAndGetResponseBody() {
// 		CPOHUBApiLogModel model = new CPOHUBApiLogModel();
// 		String responseBody = "{\"response\":\"success\"}";
// 		model.setResponseBody(responseBody);
// 		assertEquals(responseBody, model.getResponseBody());
// 	}

// 	@Test
// 	public void testSetAndGetStatusCode() {
// 		CPOHUBApiLogModel model = new CPOHUBApiLogModel();
// 		int statusCode = 200;
// 		model.setStatusCode(statusCode);
// 		assertEquals(statusCode, model.getStatusCode());
// 	}

// 	@Test
// 	public void testSetAndGetStatusText() {
// 		CPOHUBApiLogModel model = new CPOHUBApiLogModel();
// 		String statusText = "OK";
// 		model.setStatusText(statusText);
// 		assertEquals(statusText, model.getStatusText());
// 	}

// 	@Test
// 	public void testSetAndGetTimestamp() {
// 		CPOHUBApiLogModel model = new CPOHUBApiLogModel();
// 		LocalDateTime timestamp = LocalDateTime.now();
// 		model.setTimestamp(timestamp);
// 		assertEquals(timestamp, model.getTimestamp());
// 	}

// 	@Test
// 	public void testSetAndGetPartyId() {
// 		CPOHUBApiLogModel model = new CPOHUBApiLogModel();
// 		String partyId = "PARTY123";
// 		model.setParty_id(partyId);
// 		assertEquals(partyId, model.getParty_id());
// 	}

// 	@Test
// 	public void testSetAndGetCountryCode() {
// 		CPOHUBApiLogModel model = new CPOHUBApiLogModel();
// 		String countryCode = "NL";
// 		model.setCountry_code(countryCode);
// 		assertEquals(countryCode, model.getCountry_code());
// 	}

// 	@Test
// 	public void testSetAndGetType() {
// 		CPOHUBApiLogModel model = new CPOHUBApiLogModel();
// 		String type = "ERROR";
// 		model.setType(type);
// 		assertEquals(type, model.getType());
// 	}

// 	@Test
// 	public void testSetAndGetIpAddress() {
// 		CPOHUBApiLogModel model = new CPOHUBApiLogModel();
// 		String ipAddress = "192.168.1.1";
// 		model.setIpAddress(ipAddress);
// 		assertEquals(ipAddress, model.getIpAddess());
// 	}
// }
