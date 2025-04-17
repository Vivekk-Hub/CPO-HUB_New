package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CPOBusinessDetailsTest {

	private static CPOImage logo;

	@BeforeAll
	static void setUp() {
		logo = new CPOImage();
//        logo.setUrl("https://www.evgateway.com/logo.png");
//        logo.setThumbnail("https://www.evgateway.com/logo_thumbnail.png");
	}

	@Test
	void testGettersAndSetters() {
		CPOBusinessDetails businessDetails = new CPOBusinessDetails();

		businessDetails.setName("EVGateway");
		assertEquals("EVGateway", businessDetails.getName(), "Name should match the value set.");

		businessDetails.setWebsite("https://www.evgateway.com");
		assertEquals("https://www.evgateway.com", businessDetails.getWebsite(), "Website should match the value set.");

		// Test logo (CPOImage)
		businessDetails.setLogo(logo);
		assertEquals(logo, businessDetails.getLogo(), "Logo should match the CPOImage object set.");
//        assertEquals("https://www.evgateway.com/logo.png", businessDetails.getLogo().getUrl(), "Logo URL should match.");
//        assertEquals("https://www.evgateway.com/logo_thumbnail.png", businessDetails.getLogo().getThumbnail(), "Thumbnail URL should match.");
	}

	@Test
	void testNullValues() {
		// Create an instance of CPOBusinessDetails
		CPOBusinessDetails businessDetails = new CPOBusinessDetails();

		// Test default null values
		assertNull(businessDetails.getName(), "Name should be null by default.");
		assertNull(businessDetails.getWebsite(), "Website should be null by default.");
		assertNull(businessDetails.getLogo(), "Logo should be null by default.");
	}
}
