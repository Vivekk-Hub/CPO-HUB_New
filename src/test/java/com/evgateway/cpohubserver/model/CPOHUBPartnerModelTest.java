//package com.evgateway.cpohubserver.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.time.Instant;
//
//import org.junit.jupiter.api.Test;
//
//import com.evgateway.cpohubserver.cnum.Status;
//
//public class CPOHUBPartnerModelTest {
//
//	@Test
//	public void testSetAndGetId() {
//		CPOHUBPartnerModel partnerModel = new CPOHUBPartnerModel();
//		String id = "partner123";
//		partnerModel.setId(id);
//		assertEquals(id, partnerModel.getId());
//	}
//
//	@Test
//	public void testSetAndGetPartyId() {
//		CPOHUBPartnerModel partnerModel = new CPOHUBPartnerModel();
//		String partyId = "party001";
//		partnerModel.setParty_id(partyId);
//		assertEquals(partyId, partnerModel.getParty_id());
//	}
//
//	@Test
//	public void testSetAndGetCountryCode() {
//		CPOHUBPartnerModel partnerModel = new CPOHUBPartnerModel();
//		String countryCode = "US";
//		partnerModel.setCountry_code(countryCode);
//		assertEquals(countryCode, partnerModel.getCountry_code());
//	}
//
//	@Test
//	public void testSetAndGetFirstContactDate() {
//		CPOHUBPartnerModel partnerModel = new CPOHUBPartnerModel();
//		Instant firstContactDate = Instant.now();
//		partnerModel.setFirst_contact_date(firstContactDate);
//		assertEquals(firstContactDate, partnerModel.getFirst_contact_date());
//	}
//
//	@Test
//	public void testSetAndGetTotalDataExchange() {
//		CPOHUBPartnerModel partnerModel = new CPOHUBPartnerModel();
//		Long totalDataExchange = 1000L;
//		partnerModel.setTotal_data_exchange(totalDataExchange);
//		assertEquals(totalDataExchange, partnerModel.getTotal_data_exchange());
//	}
//
//	@Test
//	public void testSetAndGetStatus() {
//		CPOHUBPartnerModel partnerModel = new CPOHUBPartnerModel();
//		Status status = Status.AVAILABLE; // Assuming Status is an enum
//		partnerModel.setStatus(status);
//		assertEquals(status, partnerModel.getStatus());
//	}
//
//	@Test
//	public void testSetAndGetLastActivity() {
//		CPOHUBPartnerModel partnerModel = new CPOHUBPartnerModel();
//		Instant lastActivity = Instant.now().minusSeconds(3600);
//		partnerModel.setLast_activity(lastActivity);
//		assertEquals(lastActivity, partnerModel.getLast_activity());
//	}
//
//	@Test
//	public void testSetAndGetTotalDownTime() {
//		CPOHUBPartnerModel partnerModel = new CPOHUBPartnerModel();
//		Long totalDownTime = 120L;
//		partnerModel.setTotal_down_time(totalDownTime);
//		assertEquals(totalDownTime, partnerModel.getTotal_down_time());
//	}
//
//	@Test
//	public void testSetAndGetVersion() {
//		CPOHUBPartnerModel partnerModel = new CPOHUBPartnerModel();
//		String version = "1.0.0";
//		partnerModel.setVersion(version);
//		assertEquals(version, partnerModel.getVersion());
//	}
//
//	@Test
//	public void testSetAndGetRole() {
//		CPOHUBPartnerModel partnerModel = new CPOHUBPartnerModel();
//		String role = "Admin";
//		partnerModel.setRole(role);
//		assertEquals(role, partnerModel.getRole());
//	}
//
//	@Test
//	public void testToString() {
//		// Setting up test data
//		String id = "12345";
//		String partyId = "party_123";
//		String countryCode = "US";
//		Instant firstContactDate = Instant.now();
//		long totalDataExchange = 1000;
//		String status = "AVAILABLE";
//		Instant lastActivity = Instant.now().minusSeconds(3600);
//		long totalDownTime = 150;
//		String version = "1.0";
//		String role = "CPOADMIN";
//
//		CPOHUBPartnerModel partnerModel =new CPOHUBPartnerModel();
//		partnerModel.setId(id);
//		partnerModel.setParty_id(partyId);
//		partnerModel.setCountry_code(countryCode);
//		partnerModel.setFirst_contact_date(firstContactDate);
//		partnerModel.setTotal_data_exchange(totalDataExchange);
//		partnerModel.setStatus(Status.AVAILABLE);
//		partnerModel.setLast_activity(lastActivity);
//		partnerModel.setTotal_down_time(totalDownTime);
//		partnerModel.setVersion(version);
//		partnerModel.setRole(role);
//
//		// Expected string
//		String expectedString = "CPOHUBPartnerModel [id=" + id + ", party_id=" + partyId + ", country_code="
//				+ countryCode + ", first_contact_date=" + firstContactDate + ", total_data_exchange="
//				+ totalDataExchange + ", status=" + status + ", last_activity=" + lastActivity + ", total_down_time="
//				+ totalDownTime + ", version=" + version + ", role=" + role + "]";
//
//		// Test toString() method
//		String result = partnerModel.toString();
//
//		assertNotNull(result);
//		assertEquals(expectedString, result);
//	}
//}
