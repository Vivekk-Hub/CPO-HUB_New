package com.evgateway.cpohubserver.common;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OCPIRolesResponseTest {

	private OCPIRolesResponse rolesResponse;

	@BeforeEach
	void setUp() {
		// Initialize the OCPIRolesResponse object before each test
		rolesResponse = new OCPIRolesResponse();
	}

	@Test
	void testGettersAndSetters() {
		rolesResponse.setRole("CPO");
		rolesResponse.setParty_id("party123");
		rolesResponse.setCountry_code("US");
		rolesResponse.setFlag(true);
		rolesResponse.setRfidrequestflag(false);
		rolesResponse.setUsaLocationFlag(true);
		rolesResponse.setCanadaLocationFlag(false);
		rolesResponse.setOrg_id("org123");

		BusinessDetails businessDetails = new BusinessDetails();
		businessDetails.setName("Business Name");
		rolesResponse.setBusiness_details(businessDetails);

		assertEquals("CPO", rolesResponse.getRole());
		assertEquals("party123", rolesResponse.getParty_id());
		assertEquals("US", rolesResponse.getCountry_code());
		assertTrue(rolesResponse.isFlag());
		assertFalse(rolesResponse.isRfidrequestflag());
		assertTrue(rolesResponse.isUsaLocationFlag());
		assertFalse(rolesResponse.isCanadaLocationFlag());
		assertEquals("org123", rolesResponse.getOrg_id());
		assertEquals("Business Name", rolesResponse.getBusiness_details().getName());
	}

	@Test
	void testToString() {
		rolesResponse.setRole("CPO");
		rolesResponse.setParty_id("party123");
		rolesResponse.setCountry_code("US");
		BusinessDetails businessDetails = new BusinessDetails();
		businessDetails.setName("Business Name");
		rolesResponse.setBusiness_details(businessDetails);

		String expected = "OCPIRolesResponse [role=CPO, party_id=party123, country_code=US, business_details=BusinessDetails [name=Business Name, website=null, logo=null]]";
		assertEquals(expected, rolesResponse.toString());
	}

	@Test
	void testJsonSerialization() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		// Create and set up the OCPIRolesResponse object
		rolesResponse.setRole("CPO");
		rolesResponse.setParty_id("party123");
		rolesResponse.setCountry_code("US");
		rolesResponse.setFlag(true);
		rolesResponse.setRfidrequestflag(false);
		rolesResponse.setUsaLocationFlag(true);
		rolesResponse.setCanadaLocationFlag(false);
		rolesResponse.setOrg_id("org123");

		BusinessDetails businessDetails = new BusinessDetails();
		businessDetails.setName("Business Name");
		rolesResponse.setBusiness_details(businessDetails);

		// Serialize the OCPIRolesResponse object to JSON
		String json = objectMapper.writeValueAsString(rolesResponse);

		// Assert that the JSON contains the expected fields
		assertTrue(json.contains("\"role\":\"CPO\""));
		assertTrue(json.contains("\"party_id\":\"party123\""));
		assertTrue(json.contains("\"country_code\":\"US\""));
		assertTrue(json.contains("\"flag\":true"));
		assertTrue(json.contains("\"rfidrequestflag\":false"));
		assertTrue(json.contains("\"usaLocationFlag\":true"));
		assertTrue(json.contains("\"canadaLocationFlag\":false"));
		assertTrue(json.contains("\"org_id\":\"org123\""));
		assertTrue(json.contains("\"business_details\":{\"name\":\"Business Name\"}"));
	}

	@Test
	void testJsonDeserialization() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		// Example JSON for the OCPIRolesResponse object
		String json = "{\"role\":\"CPO\",\"party_id\":\"party123\",\"country_code\":\"US\",\"flag\":true,\"rfidrequestflag\":false,\"usaLocationFlag\":true,\"canadaLocationFlag\":false,\"org_id\":\"org123\",\"business_details\":{\"name\":\"Business Name\"}}";

		// Deserialize JSON into OCPIRolesResponse object
		rolesResponse = objectMapper.readValue(json, OCPIRolesResponse.class);

		// Assert that the deserialized values are correct
		assertEquals("CPO", rolesResponse.getRole());
		assertEquals("party123", rolesResponse.getParty_id());
		assertEquals("US", rolesResponse.getCountry_code());
		assertTrue(rolesResponse.isFlag());
		assertFalse(rolesResponse.isRfidrequestflag());
		assertTrue(rolesResponse.isUsaLocationFlag());
		assertFalse(rolesResponse.isCanadaLocationFlag());
		assertEquals("org123", rolesResponse.getOrg_id());
		assertEquals("Business Name", rolesResponse.getBusiness_details().getName());
	}
}
