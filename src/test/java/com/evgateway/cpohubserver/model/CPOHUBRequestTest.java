// package com.evgateway.cpohubserver.model;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import java.time.Instant;

// import org.junit.jupiter.api.Test;

// public class CPOHUBRequestTest {

// 	@Test
// 	public void testSetAndGetUid() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		String uid = "request123";
// 		cpoHUBRequest.setUid(uid);
// 		assertEquals(uid, cpoHUBRequest.getUid());
// 	}

// 	@Test
// 	public void testSetAndGetEmspCountryCode() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		String emspCountryCode = "US";
// 		cpoHUBRequest.setEmsp_country_code(emspCountryCode);
// 		assertEquals(emspCountryCode, cpoHUBRequest.getEmsp_country_code());
// 	}

// 	@Test
// 	public void testSetAndGetEmspPartyId() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		String emspPartyId = "EMSP001";
// 		cpoHUBRequest.setEmsp_party_id(emspPartyId);
// 		assertEquals(emspPartyId, cpoHUBRequest.getEmsp_party_id());
// 	}

// 	@Test
// 	public void testSetAndGetTokenUuid() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		String tokenUuid = "token123-uuid";
// 		cpoHUBRequest.setToken_uuid(tokenUuid);
// 		assertEquals(tokenUuid, cpoHUBRequest.getToken_uuid());
// 	}

// 	@Test
// 	public void testSetAndGetCpoCountryCode() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		String cpoCountryCode = "IN";
// 		cpoHUBRequest.setCpo_country_code(cpoCountryCode);
// 		assertEquals(cpoCountryCode, cpoHUBRequest.getCpo_country_code());
// 	}

// 	@Test
// 	public void testSetAndGetCpoPartyId() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		String cpoPartyId = "CPO001";
// 		cpoHUBRequest.setCpo_party_id(cpoPartyId);
// 		assertEquals(cpoPartyId, cpoHUBRequest.getCpo_party_id());
// 	}

// 	@Test
// 	public void testSetAndGetCpoEvseUid() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		String cpoEvseUid = "evse001";
// 		cpoHUBRequest.setCpo_evse_uid(cpoEvseUid);
// 		assertEquals(cpoEvseUid, cpoHUBRequest.getCpo_evse_uid());
// 	}

// 	@Test
// 	public void testSetAndGetCpoLocationId() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		String cpoLocationId = "location001";
// 		cpoHUBRequest.setCpo_location_id(cpoLocationId);
// 		assertEquals(cpoLocationId, cpoHUBRequest.getCpo_location_id());
// 	}

// 	@Test
// 	public void testSetAndGetStatus() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		String status = "Active";
// 		cpoHUBRequest.setStatus(status);
// 		assertEquals(status, cpoHUBRequest.getStatus());
// 	}

// 	@Test
// 	public void testSetAndGetReason() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		String reason = "Completed";
// 		cpoHUBRequest.setReason(reason);
// 		assertEquals(reason, cpoHUBRequest.getReason());
// 	}

// 	@Test
// 	public void testSetAndGetResponseUrl() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		String responseUrl = "http://example.com/response";
// 		cpoHUBRequest.setResponse_url(responseUrl);
// 		assertEquals(responseUrl, cpoHUBRequest.getResponse_url());
// 	}

// 	@Test
// 	public void testSetAndGetResponseUuid() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		String responseUuid = "response123-uuid";
// 		cpoHUBRequest.setResponse_uuid(responseUuid);
// 		assertEquals(responseUuid, cpoHUBRequest.getResponse_uuid());
// 	}

// 	@Test
// 	public void testSetAndGetRequestType() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		String requestType = "EVSEStatus";
// 		cpoHUBRequest.setRequest_type(requestType);
// 		assertEquals(requestType, cpoHUBRequest.getRequest_type());
// 	}

// 	@Test
// 	public void testSetAndGetSessionId() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		String sessionId = "session123";
// 		cpoHUBRequest.setSession_id(sessionId);
// 		assertEquals(sessionId, cpoHUBRequest.getSession_id());
// 	}

// 	@Test
// 	public void testSetAndGetVersion() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		String version = "1.0";
// 		cpoHUBRequest.setVersion(version);
// 		assertEquals(version, cpoHUBRequest.getVersion());
// 	}

// 	@Test
// 	public void testSetAndGetLastUpdated() {
// 		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest();
// 		Instant lastUpdated = Instant.now();
// 		cpoHUBRequest.setLast_updated(lastUpdated);
// 		assertEquals(lastUpdated, cpoHUBRequest.getLast_updated());
// 	}
// //	@Test
// //    void testParameterizedConstructor() {
// //        Instant now = Instant.now();
// //        CPOHUBRequest request = new CPOHUBRequest("US", "123", "uuid-token", "US", "456", 
// //                "evse-uid", "location-id", "Pending", "Some reason", 
// //                "http://response.url", "response-uuid", "RequestType", 
// //                "session-123", "v1.0", now);
// //
// //        assertEquals("US", request.getEmsp_country_code());
// //        assertEquals("123", request.getEmsp_party_id());
// //        assertEquals("uuid-token", request.getToken_uuid());
// //        assertEquals("US", request.getCpo_country_code());
// //        assertEquals("456", request.getCpo_party_id());
// //        assertEquals("evse-uid", request.getCpo_evse_uid());
// //        assertEquals("location-id", request.getCpo_location_id());
// //        assertEquals("Pending", request.getStatus());
// //        assertEquals("Some reason", request.getReason());
// //        assertEquals("http://response.url", request.getResponse_url());
// //        assertEquals("response-uuid", request.getResponse_uuid());
// //        assertEquals("RequestType", request.getRequest_type());
// //        assertEquals("session-123", request.getSession_id());
// //        assertEquals("v1.0", request.getVersion());
// //        assertEquals(now, request.getLast_updated());
// //    }
// //	@Test
// //	void testToString() {
// //		Instant now = Instant.now();
// //		CPOHUBRequest cpoHUBRequest = new CPOHUBRequest("US", "123", "uuid-token", "US", "456", "evse-uid", "location-id", "Pending",
// //				"Some reason", "http://response.url", "response-uuid", "RequestType", "session-123", "v1.0", now);
// //
// //		String expected = "CPOHUBRequest [uid=null, emsp_country_code=US, emsp_party_id=123, "
// //				+ "cpo_country_code=US, cpo_party_id=456, token_uuid=uuid-token, "
// //				+ "cpo_evse_uid=evse-uid, cpo_location_id=location-id, status=Pending, "
// //				+ "reason=Some reason, response_url=http://response.url, "
// //				+ "response_uuid=response-uuid, request_type=RequestType, "
// //				+ "session_id=session-123, version=v1.0, last_updated=" + now + "]";
// //
// //		assertEquals(expected, cpoHUBRequest.toString());
// //	}
// }
