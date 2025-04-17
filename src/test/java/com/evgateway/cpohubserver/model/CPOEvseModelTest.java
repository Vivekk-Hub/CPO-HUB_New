//package com.evgateway.cpohubserver.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.time.Instant;
//import java.util.Arrays;
//import java.util.Collections;
//
//import org.junit.jupiter.api.Test;
//
//import com.evgateway.cpohubserver.form.CPODisplayText;
//import com.evgateway.cpohubserver.form.CPOGeoLocation;
//import com.evgateway.cpohubserver.form.CPOImage;
//import com.evgateway.cpohubserver.form.CPOStatusSchedule;
//
//class CPOEvseModelTest {
//
//	@Test
//	public void testSetAndGetU_id() {
//		CPOEvseModel model = new CPOEvseModel();
//		String u_id = "testUId";
//		model.setU_id(u_id);
//		assertEquals(u_id, model.getU_id());
//	}
//
//	@Test
//	public void testSetAndGetuId() {
//		CPOEvseModel model = new CPOEvseModel();
//		String uId = "testUId";
//		model.setUid(uId);
//		assertEquals(uId, model.getUid());
//	}
//
//	@Test
//	public void testSetAndGetstatus() {
//		CPOEvseModel model = new CPOEvseModel();
//		String status = "testUId";
//		model.setStatus(status);
//		assertEquals(status, model.getStatus());
//	}
//
//	@Test
//	public void testSetAndGetevse_id() {
//		CPOEvseModel model = new CPOEvseModel();
//		String evse_id = "testUId";
//		model.setEvse_id(evse_id);
//		assertEquals(evse_id, model.getEvse_id());
//	}
//
//	@Test
//	public void testSetAndGetfloor_level() {
//		CPOEvseModel model = new CPOEvseModel();
//		String floor_level = "testUId";
//		model.setFloor_level(floor_level);
//		assertEquals(floor_level, model.getFloor_level());
//	}
//
//	@Test
//	public void testSetAndGetphysical_reference() {
//		CPOEvseModel model = new CPOEvseModel();
//		String physical_reference = "testUId";
//		model.setPhysical_reference(physical_reference);
//		assertEquals(physical_reference, model.getPhysical_reference());
//	}
//
//	@Test
//	public void testSetAndGetloc_ref_id() {
//		CPOEvseModel model = new CPOEvseModel();
//		String loc_ref_id = "testUId";
//		model.setLoc_ref_id(loc_ref_id);
//		assertEquals(loc_ref_id, model.getLoc_ref_id());
//	}
//
//	@Test
//	public void testSetAndGetloc_uid() {
//		CPOEvseModel model = new CPOEvseModel();
//		String loc_uid = "testUId";
//		model.setLoc_uid(loc_uid);
//		assertEquals(loc_uid, model.getLoc_uid());
//	}
//
//	@Test
//	public void testSetAndGetcpo_evse_id() {
//		CPOEvseModel model = new CPOEvseModel();
//		String cpo_evse_id = "testUId";
//		model.setCpo_evse_id(cpo_evse_id);
//		assertEquals(cpo_evse_id, model.getCpo_evse_id());
//	}
//
//	@Test
//	public void testSetAndGetu_id() {
//		CPOEvseModel model = new CPOEvseModel();
//		String u_id = "1168951";
//		model.setU_id(u_id);
//		assertEquals(u_id, model.getU_id());
//	}
//
//	@Test
//	public void testSetAndGetCountryCode() {
//		CPOEvseModel model = new CPOEvseModel();
//		String countryCode = "NL";
//		model.setCountrycode(countryCode);
//		assertEquals(countryCode, model.getCountrycode());
//	}
//
//	@Test
//	public void testSetAndGetPartyId() {
//		CPOEvseModel model = new CPOEvseModel();
//		String partyId = "TEST";
//		model.setPartyid(partyId);
//		assertEquals(partyId, model.getPartyid());
//	}
//
//	@Test
//	public void testSetAndGetCapabilities() {
//		CPOEvseModel model = new CPOEvseModel();
//		String[] capabilities = { "CAPABILITY_1", "CAPABILITY_2" };
//		model.setCapabilities(capabilities);
//		assertTrue(Arrays.equals(capabilities, model.getCapabilities()));
//	}
//
//	@Test
//	public void testSetAndGetParkingRestrictions() {
//		CPOEvseModel model = new CPOEvseModel();
//		String[] restrictions = { "RESTRICTION_1", "RESTRICTION_2" };
//		model.setParking_restrictions(restrictions);
//		assertTrue(Arrays.equals(restrictions, model.getParking_restrictions()));
//	}
//
//	@Test
//	public void testSetAndGetCoordinates() {
//		CPOEvseModel model = new CPOEvseModel();
//		CPOGeoLocation geoLocation = new CPOGeoLocation();
//		geoLocation.setLatitude("52.379189");
//		geoLocation.setLongitude("4.899431");
//		model.setCoordinates(geoLocation);
//		assertEquals(geoLocation, model.getCoordinates());
//	}
//
//	@Test
//	public void testSetAndGetDirections() {
//		CPOEvseModel model = new CPOEvseModel();
//		CPODisplayText direction = new CPODisplayText();
//		direction.setLanguage("EN");
//		direction.setText("Turn left");
//		model.setDirections(Collections.singletonList(direction));
//		assertEquals(Collections.singletonList(direction), model.getDirections());
//	}
//
//	@Test
//	public void testSetAndGetStatusSchedule() {
//		CPOEvseModel model = new CPOEvseModel();
//		CPOStatusSchedule schedule = new CPOStatusSchedule();
//		schedule.setStatus("AVAILABLE");
//		model.setStatus_schedule(Collections.singletonList(schedule));
//		assertEquals(Collections.singletonList(schedule), model.getStatus_schedule());
//	}
//
//	@Test
//	public void testSetAndGetImages() {
//		CPOEvseModel model = new CPOEvseModel();
//		CPOImage image = new CPOImage();
////		image.setUrl("http://example.com/image.png");
//		model.setImages(Collections.singletonList(image));
//		assertEquals(Collections.singletonList(image), model.getImages());
//	}
//
//	@Test
//	public void testSetAndGetLastUpdated() {
//		CPOEvseModel model = new CPOEvseModel();
//		Instant now = Instant.now();
//		model.setLast_updated(now);
//		assertEquals(now, model.getLast_updated());
//	}
//
//	@Test
//	public void testToString() {
//		CPOEvseModel model = new CPOEvseModel();
//		model.setU_id("testUId");
//		model.setCountrycode("NL");
//		model.setPartyid("TEST");
//		model.setUid("UID123");
//		String expected = "CPOEvseModel [u_id=testUId, countrycode=NL, partyid=TEST, uid=UID123";
//		assertTrue(model.toString().contains(expected));
//	}
//}
