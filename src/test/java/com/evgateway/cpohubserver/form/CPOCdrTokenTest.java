package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CPOCdrTokenTest {

	@Test
	void testGettersAndSetters() {
		// Create an instance of CPOCdrToken
		CPOCdrToken token = new CPOCdrToken();

		// Set values for the fields
		token.setUid("12345");
		token.setType("Standard");
		token.setContract_id("C123");
		token.setCountry_code("US");
		token.setParty_id("P123");

		// Test each field with corresponding getters
		assertEquals("12345", token.getUid(), "UID should match the value set.");
		assertEquals("Standard", token.getType(), "Type should match the value set.");
		assertEquals("C123", token.getContract_id(), "Contract ID should match the value set.");
		assertEquals("US", token.getCountry_code(), "Country code should match the value set.");
		assertEquals("P123", token.getParty_id(), "Party ID should match the value set.");
	}

	@Test
	void testNullValues() {
		// Create an instance of CPOCdrToken
		CPOCdrToken token = new CPOCdrToken();

		// Test default values (should be null by default)
		assertNull(token.getUid(), "UID should be null by default.");
		assertNull(token.getType(), "Type should be null by default.");
		assertNull(token.getContract_id(), "Contract ID should be null by default.");
		assertNull(token.getCountry_code(), "Country code should be null by default.");
		assertNull(token.getParty_id(), "Party ID should be null by default.");
	}

	@Test
	void testToString() {
		// Create an instance of CPOCdrToken
		CPOCdrToken token = new CPOCdrToken();

		// Set values for the fields
		token.setUid("12345");
		token.setType("Standard");
		token.setContract_id("C123");
		token.setCountry_code("US");
		token.setParty_id("P123");

		// Test the toString method
		String expectedString = "CdrToken [uid=12345, type=Standard, contract_id=C123, country_code=US, party_id=P123]";
		assertEquals(expectedString, token.toString(), "toString should return the expected string.");
	}
}
