//package com.evgateway.cpohubserver.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.time.Instant;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class CPOSessionsActivityModelTest {
//
//	private CPOSessionsActivityModel activity;
//
//	@BeforeEach
//	public void setUp() {
//		activity = new CPOSessionsActivityModel();
//	}
//
//	@Test
//	public void testSetAndGetUid() {
//		String uid = "activity123";
//		activity.setUid(uid);
//		assertEquals(uid, activity.getUid());
//	}
//
//	@Test
//	public void testSetAndGetSessionId() {
//		String sessionId = "session123";
//		activity.setSession_id(sessionId);
//		assertEquals(sessionId, activity.getSession_id());
//	}
//
//	@Test
//	public void testSetAndGetEmspCountryCode() {
//		String emspCountryCode = "US";
//		activity.setEmsp_country_code(emspCountryCode);
//		assertEquals(emspCountryCode, activity.getEmsp_country_code());
//	}
//
//	@Test
//	public void testSetAndGetEmspPartyId() {
//		String emspPartyId = "emsp123";
//		activity.setEmsp_party_id(emspPartyId);
//		assertEquals(emspPartyId, activity.getEmsp_party_id());
//	}
//
//	@Test
//	public void testSetAndGetTokenUuid() {
//		String tokenUuid = "token123";
//		activity.setToken_uuid(tokenUuid);
//		assertEquals(tokenUuid, activity.getToken_uuid());
//	}
//
//	@Test
//	public void testSetAndGetCpoCountryCode() {
//		String cpoCountryCode = "FR";
//		activity.setCpo_country_code(cpoCountryCode);
//		assertEquals(cpoCountryCode, activity.getCpo_country_code());
//	}
//
//	@Test
//	public void testSetAndGetCpoPartyId() {
//		String cpoPartyId = "cpo123";
//		activity.setCpo_party_id(cpoPartyId);
//		assertEquals(cpoPartyId, activity.getCpo_party_id());
//	}
//
//	@Test
//	public void testSetAndGetCpoEvseUid() {
//		String cpoEvseUid = "evse123";
//		activity.setCpo_evse_uid(cpoEvseUid);
//		assertEquals(cpoEvseUid, activity.getCpo_evse_uid());
//	}
//
//	@Test
//	public void testSetAndGetCpoLocationId() {
//		String cpoLocationId = "location123";
//		activity.setCpo_location_id(cpoLocationId);
//		assertEquals(cpoLocationId, activity.getCpo_location_id());
//	}
//
//	@Test
//	public void testSetAndGetSessionStartDateTime() {
//		Instant sessionStartDateTime = Instant.now();
//		activity.setSession_start_date_time(sessionStartDateTime);
//		assertEquals(sessionStartDateTime, activity.getSession_start_date_time());
//	}
//
//	@Test
//	public void testSetAndGetSessionEndDateTime() {
//		Instant sessionEndDateTime = Instant.now();
//		activity.setSession_end_date_time(sessionEndDateTime);
//		assertEquals(sessionEndDateTime, activity.getSession_end_date_time());
//	}
//
//	@Test
//	public void testSetAndGetTotalEnergy() {
//		double totalEnergy = 15.5;
//		activity.setTotal_energy(totalEnergy);
//		assertEquals(totalEnergy, activity.getTotal_energy());
//	}
//
//	@Test
//	public void testSetAndGetStatus() {
//		String status = "Active";
//		activity.setStatus(status);
//		assertEquals(status, activity.getStatus());
//	}
//
//	@Test
//	public void testSetAndGetLastUpdated() {
//		Instant lastUpdated = Instant.now();
//		activity.setLast_updated(lastUpdated);
//		assertEquals(lastUpdated, activity.getLast_updated());
//	}
//
//	@Test
//	public void testToString() {
//		String expectedString = "CPOSessionsActivityModel [uid=null, emsp_country_code=null, emsp_party_id=null, "
//				+ "cpo_country_code=null, cpo_party_id=null, token_uuid=null, cpo_location_id=null, cpo_evse_uid=null, "
//				+ "session_id=null, session_start_date_time=null, session_end_date_time=null, total_energy=0.0, status=null, "
//				+ "last_updated=null]";
//
//		String result = activity.toString();
//		assertNotNull(result);
//		assertEquals(expectedString, result);
//	}
//}
