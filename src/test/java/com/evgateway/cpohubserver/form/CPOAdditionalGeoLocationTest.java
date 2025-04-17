package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CPOAdditionalGeoLocationTest {

	@Test
	void testGettersAndSetters() {
		// Create an instance of the class
		CPOAdditionalGeoLocation geoLocation = new CPOAdditionalGeoLocation();

		// Test latitude
		geoLocation.setLatitude("25.2048");
		assertEquals("25.2048", geoLocation.getLatitude(), "Latitude should match the value set.");

		// Test longitude
		geoLocation.setLongitude("55.2708");
		assertEquals("55.2708", geoLocation.getLongitude(), "Longitude should match the value set.");

		// Test name (CPODisplayText)
		CPODisplayText displayText = new CPODisplayText();
		displayText.setLanguage("en");
		displayText.setText("Dubai");

		geoLocation.setName(displayText);
		assertEquals(displayText, geoLocation.getName(), "Name should match the CPODisplayText object set.");
		assertEquals("en", geoLocation.getName().getLanguage(),
				"Language should match the value set in CPODisplayText.");
		assertEquals("Dubai", geoLocation.getName().getText(), "Text should match the value set in CPODisplayText.");
	}

	@Test
	void testNullValues() {
		// Create an instance of the class
		CPOAdditionalGeoLocation geoLocation = new CPOAdditionalGeoLocation();

		// Test default null values
		assertNull(geoLocation.getLatitude(), "Latitude should be null by default.");
		assertNull(geoLocation.getLongitude(), "Longitude should be null by default.");
		assertNull(geoLocation.getName(), "Name should be null by default.");
	}
}
