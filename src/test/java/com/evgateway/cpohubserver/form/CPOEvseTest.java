// package com.evgateway.cpohubserver.form;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Date;
// import java.util.List;

// class CPOEvseTest {

// 	private CPOEvse cpoEvse;

// 	@BeforeEach
// 	void setUp() {
// 		// Initialize the CPOEvse object before each test
// 		cpoEvse = new CPOEvse();
// 		cpoEvse.setU_id("u123");
// 		cpoEvse.setCountrycode("US");
// 		cpoEvse.setPartyid("party1");
// 		cpoEvse.setUid("uid123");
// 		cpoEvse.setEvse_id("evse123");
// 		cpoEvse.setStatus("active");
// 		cpoEvse.setFloor_level("1");
// 		cpoEvse.setPhysical_reference("ref123");
// 		cpoEvse.setParking_restrictions(new String[] { "restricted" });
// 		cpoEvse.setCapabilities(new String[] { "fast_charge", "slow_charge" });
// 		cpoEvse.setLast_updated(new Date());

// 		List<CPOStatusSchedule> list = new ArrayList<CPOStatusSchedule>();
// 		CPOStatusSchedule CPOStatusSchedule = new CPOStatusSchedule();
// 		CPOStatusSchedule.setStatus("ACTIVE");
// 		CPOStatusSchedule.setPeriod_begin(null);
// 		CPOStatusSchedule.setPeriod_end(null);
// 		list.add(CPOStatusSchedule);
// 		cpoEvse.setStatus_schedule(list);

// 		cpoEvse.setCoordinates(new CPOGeoLocation()); // Replace with actual initialization
// 		List<CPODisplayText> lists = new ArrayList<CPODisplayText>();
// 		CPODisplayText CPODisplayText = new CPODisplayText();
// 		CPODisplayText.setLanguage("EN");
// 		CPODisplayText.setText("English");
// 		lists.add(CPODisplayText);
// 		cpoEvse.setDirections(lists);

// 		List<CPOImage> list2 = new ArrayList<CPOImage>();
// 		CPOImage CPOImage = new CPOImage();
// 		list2.add(CPOImage);
// 		cpoEvse.setImages(list2);

// 		cpoEvse.setConnectors(Arrays.asList(new CPOConnector())); // Replace with actual initialization
// 	}

// 	@Test
// 	void testGettersAndSetters() {
// 		assertEquals("u123", cpoEvse.getU_id());
// 		assertEquals("US", cpoEvse.getCountrycode());
// 		assertEquals("party1", cpoEvse.getPartyid());
// 		assertEquals("uid123", cpoEvse.getUid());
// 		assertEquals("evse123", cpoEvse.getEvse_id());
// 		assertEquals("active", cpoEvse.getStatus());
// 		assertEquals("1", cpoEvse.getFloor_level());
// 		assertEquals("ref123", cpoEvse.getPhysical_reference());
// 		assertEquals("ACTIVE", cpoEvse.getStatus_schedule().get(0).getStatus());
// 		assertEquals("EN", cpoEvse.getDirections().get(0).getLanguage());

// 		assertArrayEquals(new String[] { "restricted" }, cpoEvse.getParking_restrictions());
// 		assertArrayEquals(new String[] { "fast_charge", "slow_charge" }, cpoEvse.getCapabilities());
// 		assertNotNull(cpoEvse.getLast_updated());
// 	}

// 	@Test
// 	void testToString() {
// 		String expectedString = "CPOEvse [u_id=u123, countrycode=US, partyid=party1, uid=uid123, evse_id=evse123, status=active, floor_level=1, physical_reference=ref123, parking_restrictions=[restricted], capabilities=[fast_charge, slow_charge], last_updated="
// 				+ cpoEvse.getLast_updated()
// 				+ ", status_schedule=[CPOStatusSchedule@123456], connectors=[CPOConnector@654321], coordinates=CPOGeoLocation@abcdef, directions=[CPODisplayText@789012]]";
// 		String actualString = cpoEvse.toString();
// 		assertTrue(actualString.contains("u_id=u123"));
// 		assertTrue(actualString.contains("countrycode=US"));
// 		assertTrue(actualString.contains("partyid=party1"));
// 		assertTrue(actualString.contains("status=active"));
// 	}
// }
