//package com.evgateway.cpohubserver.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//import java.time.Instant;
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.evgateway.cpohubserver.form.CPOChargingPeriod;
//import com.evgateway.cpohubserver.form.CPOPrice;
//
//public class CPOSessionModelTest {
//
//	private CPOSessionModel session;
//
//	@BeforeEach
//	public void setUp() {
//		session = new CPOSessionModel();
//	}
//
//	@Test
//	public void testSetAndGetId() {
//		String id = "session123";
//		session.setId(id);
//		assertEquals(id, session.getId());
//	}
//
//	@Test
//	public void testSetAndGetUid() {
//		String uid = "uid123";
//		session.setUid(uid);
//		assertEquals(uid, session.getUid());
//	}
//
//	@Test
//	public void testSetAndGetCountryCode() {
//		String countryCode = "US";
//		session.setCountry_code(countryCode);
//		assertEquals(countryCode, session.getCountry_code());
//	}
//
//	@Test
//	public void testSetAndGetPartyId() {
//		String partyId = "party123";
//		session.setParty_id(partyId);
//		assertEquals(partyId, session.getParty_id());
//	}
//
//	@Test
//	public void testSetAndGetStartDateTime() {
//		Instant startDateTime = Instant.now();
//		session.setStart_date_time(startDateTime);
//		assertEquals(startDateTime, session.getStart_date_time());
//	}
//
//	@Test
//	public void testSetAndGetEndDateTime() {
//		Instant endDateTime = Instant.now();
//		session.setEnd_date_time(endDateTime);
//		assertEquals(endDateTime, session.getEnd_date_time());
//	}
//
//	@Test
//	public void testSetAndGetKwh() {
//		double kwh = 10.5;
//		session.setKwh(kwh);
//		assertEquals(kwh, session.getKwh());
//	}
//
//	@Test
//	public void testSetAndGetAuthMethod() {
//		String authMethod = "PIN";
//		session.setAuth_method(authMethod);
//		assertEquals(authMethod, session.getAuth_method());
//	}
//
//	@Test
//	public void testSetAndGetLocationId() {
//		String locationId = "location123";
//		session.setLocation_id(locationId);
//		assertEquals(locationId, session.getLocation_id());
//	}
//
//	@Test
//	public void testSetAndGetEvseUid() {
//		String evseUid = "evse123";
//		session.setEvse_uid(evseUid);
//		assertEquals(evseUid, session.getEvse_uid());
//	}
//
//	@Test
//	public void testSetAndGetTotalCost() {
//		CPOPrice totalCost = new CPOPrice(); // Assuming you have a valid CPOPrice class
//		session.setTotal_cost(totalCost);
//		assertEquals(totalCost, session.getTotal_cost());
//	}
//
//	@Test
//	public void testSetAndGetChargingPeriods() {
//		List<CPOChargingPeriod> chargingPeriods = Arrays.asList(new CPOChargingPeriod(), new CPOChargingPeriod());
//		session.setCharging_periods(chargingPeriods);
//		assertEquals(chargingPeriods, session.getCharging_periods());
//	}
//
//	@Test
//	public void testToString() {
//		String expectedString = "CPOSessionModel [u_id=null, id=null, uid=null, country_code=null, party_id=null, "
//				+ "cpo_country_code=null, cpo_party_id=null, emsp_country_code=null, emsp_party_id=null, auth_id=null, "
//				+ "start_date_time=null, end_date_time=null, kwh=0.0, cdr_token=null, auth_method=null, "
//				+ "authorization_reference=null, location_id=null, evse_uid=null, connector_id=null, meter_id=null, "
//				+ "currency=null, charging_periods=[], total_cost=null, status=null, location=null, last_updated=null, version=null]";
//
//		String result = session.toString();
//		assertNotNull(result);
//		assertEquals(expectedString, result);
//	}
//
//	@Test
//	public void testSetAndGetLastUpdated() {
//		Instant lastUpdated = Instant.now();
//		session.setLast_updated(lastUpdated);
//		assertEquals(lastUpdated, session.getLast_updated());
//	}
//
//	@Test
//	public void testSetAndGetVersion() {
//		String version = "1.0";
//		session.setVersion(version);
//		assertEquals(version, session.getVersion());
//	}
//}
