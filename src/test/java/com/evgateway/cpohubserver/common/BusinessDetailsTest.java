package com.evgateway.cpohubserver.common;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class BusinessDetailsTest {

	@Test
	void testGettersAndSetters() {
		// Create an instance of BusinessDetails
		BusinessDetails businessDetails = new BusinessDetails();

		// Set values
		businessDetails.setName("Test Business");
		businessDetails.setWebsite("https://test.com");

		// Create and set Image
		Image logo = new Image();
		logo.setUrl("https://test.com/logo.png");
		businessDetails.setLogo(logo);

		// Assert values
		assertEquals("Test Business", businessDetails.getName());
		assertEquals("https://test.com", businessDetails.getWebsite());
		assertEquals(logo, businessDetails.getLogo());
	}

	@Test
	void testToString() {
		BusinessDetails businessDetails = new BusinessDetails();
		businessDetails.setName("Test Business");
		businessDetails.setWebsite("https://test.com");

		Image logo = new Image();
		logo.setUrl("https://test.com/logo.png");
		businessDetails.setLogo(logo);

		String expected = "BusinessDetails [name=Test Business, website=https://test.com, logo=" + logo + "]";
		assertEquals(expected, businessDetails.toString());
	}

	@Test
	void testJsonSerialization() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		// Create an instance of BusinessDetails
		BusinessDetails businessDetails = new BusinessDetails();
		businessDetails.setName("Test Business");
		businessDetails.setWebsite("https://test.com");

		// Serialize to JSON
		String json = objectMapper.writeValueAsString(businessDetails);

		// Assert JSON output (logo should be excluded due to @JsonIgnore)
		assertTrue(json.contains("\"name\":\"Test Business\""));
		assertTrue(json.contains("\"website\":\"https://test.com\""));
		assertFalse(json.contains("logo")); // logo should be excluded
	}

	@Test
	void testJsonDeserialization() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		// JSON string
		String json = "{\"name\":\"Test Business\",\"website\":\"https://test.com\"}";

		// Deserialize to BusinessDetails
		BusinessDetails businessDetails = objectMapper.readValue(json, BusinessDetails.class);

		// Assert values
		assertEquals("Test Business", businessDetails.getName());
		assertEquals("https://test.com", businessDetails.getWebsite());
		assertNull(businessDetails.getLogo()); // logo is not in JSON
	}
}
