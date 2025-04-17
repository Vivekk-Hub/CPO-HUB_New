// package com.evgateway.cpohubserver.model;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.time.Instant;

// import org.junit.jupiter.api.Test;

// class CPOHUBAlertModelTest {

// 	@Test
// 	public void testSetAndGetId() {
// 		CPOHUBAlertModel model = new CPOHUBAlertModel();
// 		String id = "alert123";
// 		model.setId(id);
// 		assertEquals(id, model.getId());
// 	}

// 	@Test
// 	public void testSetAndGetType() {
// 		CPOHUBAlertModel model = new CPOHUBAlertModel();
// 		String type = "WARNING";
// 		model.setType(type);
// 		assertEquals(type, model.getType());
// 	}

// 	@Test
// 	public void testSetAndGetDetails() {
// 		CPOHUBAlertModel model = new CPOHUBAlertModel();
// 		String details = "This is a test alert";
// 		model.setDetails(details);
// 		assertEquals(details, model.getDetails());
// 	}

// 	@Test
// 	public void testSetAndGetPartyId() {
// 		CPOHUBAlertModel model = new CPOHUBAlertModel();
// 		String partyId = "PARTY123";
// 		model.setParty_id(partyId);
// 		assertEquals(partyId, model.getParty_id());
// 	}

// 	@Test
// 	public void testSetAndGetCountryCode() {
// 		CPOHUBAlertModel model = new CPOHUBAlertModel();
// 		String countryCode = "NL";
// 		model.setCountry_code(countryCode);
// 		assertEquals(countryCode, model.getCountry_code());
// 	}

// 	@Test
// 	public void testSetAndGetStatus() {
// 		CPOHUBAlertModel model = new CPOHUBAlertModel();
// 		String status = "ACTIVE";
// 		model.setStatus(status);
// 		assertEquals(status, model.getStatus());
// 	}

// 	@Test
// 	public void testSetAndGetTimeStamp() {
// 		CPOHUBAlertModel model = new CPOHUBAlertModel();
// 		Instant timeStamp = Instant.now();
// 		model.setTimeStamp(timeStamp);
// 		assertEquals(timeStamp, model.getTimeStamp());
// 	}

// 	@Test
// 	public void testSetAndGetComment() {
// 		CPOHUBAlertModel model = new CPOHUBAlertModel();
// 		String comment = "This is a test comment";
// 		model.setComment(comment);
// 		assertEquals(comment, model.getComment());
// 	}

// 	@Test
// 	public void testToString() {
// 		CPOHUBAlertModel model = new CPOHUBAlertModel();
// 		model.setId("alert123");
// 		model.setType("WARNING");
// 		model.setDetails("This is a test alert");
// 		model.setParty_id("PARTY123");
// 		model.setCountry_code("NL");
// 		model.setStatus("ACTIVE");
// 		model.setTimeStamp(Instant.now());
// 		model.setComment("This is a test comment");

// 		String expectedPartial = "CPOHUBAlertModel [id=alert123, type=WARNING, details=This is a test alert";
// 		assertEquals(true, model.toString().contains(expectedPartial));
// 	}
// }
