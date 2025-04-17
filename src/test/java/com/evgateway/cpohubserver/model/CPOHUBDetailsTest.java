package com.evgateway.cpohubserver.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CPOHUBDetailsTest {

	@Test
	public void testSetAndGetId() {
		CPOHUBDetails CPOHUBDetails = new CPOHUBDetails();
		String id = "cred123";
		CPOHUBDetails.setId(id);
		assertEquals(id, CPOHUBDetails.getId());
	}

	@Test
	public void testSetAndGetCountryCode() {
		CPOHUBDetails CPOHUBDetails = new CPOHUBDetails();
		String countryCode = "US";
		CPOHUBDetails.setCountryCode(countryCode);
		assertEquals(countryCode, CPOHUBDetails.getCountryCode());
	}

	@Test
	public void testSetAndGetpartyId() {
		CPOHUBDetails CPOHUBDetails = new CPOHUBDetails();
		String partyId = "123654";
		CPOHUBDetails.setPartyId(partyId);
		assertEquals(partyId, CPOHUBDetails.getPartyId());
	}

	@Test
	public void testSetAndGetpartyName() {
		CPOHUBDetails CPOHUBDetails = new CPOHUBDetails();
		String partyName = "EVG";
		CPOHUBDetails.setPartyName(partyName);
		assertEquals(partyName, CPOHUBDetails.getPartyName());
	}

	@Test
	public void testSetAndGetwebsite() {
		CPOHUBDetails CPOHUBDetails = new CPOHUBDetails();
		String website = "EVG";
		CPOHUBDetails.setWebsite(website);
		assertEquals(website, CPOHUBDetails.getWebsite());
	}

	@Test
	public void testSetAndGetpublicToken() {
		CPOHUBDetails CPOHUBDetails = new CPOHUBDetails();
		String publicToken = "1336558899665";
		CPOHUBDetails.setPublicToken(publicToken);
		assertEquals(publicToken, CPOHUBDetails.getPublicToken());
	}

	@Test
	public void testSetAndGetdomain() {
		CPOHUBDetails CPOHUBDetails = new CPOHUBDetails();
		String domain = "EVG";
		CPOHUBDetails.setDomain(domain);
		assertEquals(domain, CPOHUBDetails.getDomain());
	}

	@Test
	public void testSetAndGettokenPrefix() {
		CPOHUBDetails CPOHUBDetails = new CPOHUBDetails();
		String tokenPrefix = "EVG";
		CPOHUBDetails.setTokenPrefix(tokenPrefix);
		assertEquals(tokenPrefix, CPOHUBDetails.getTokenPrefix());
	}

	@Test
	public void testSetAndGettokenLength() {
		CPOHUBDetails CPOHUBDetails = new CPOHUBDetails();
		int tokenLength = 5;
		CPOHUBDetails.setTokenLength(tokenLength);
		assertEquals(tokenLength, CPOHUBDetails.getTokenLength());
	}

	@Test
	public void testSetAndGetpartyRole() {
		CPOHUBDetails CPOHUBDetails = new CPOHUBDetails();
		String partyRole = "EVG";
		CPOHUBDetails.setPartyRole(partyRole);
		assertEquals(partyRole, CPOHUBDetails.getPartyRole());
	}

	@Test
	void testToString() {
		// Create a test instance of the class
		CPOHUBDetails details = new CPOHUBDetails();
		details.setId("12345");
		details.setCountryCode("US");
		details.setPartyId("PARTY001");
		details.setPartyName("Test Party");
		details.setWebsite("https://example.com");
		details.setPublicToken("TOKEN123");
		details.setDomain("example.com");
		details.setTokenPrefix("PRE");
		details.setTokenLength(10);
		details.setPartyRole("CPO");

		// Expected string representation
		String expected = "CPOHUBDetails [id=12345, countryCode=US, partyId=PARTY001, partyName=Test Party, website=https://example.com, "
				+ "publicToken=TOKEN123, domain=example.com, tokenPrefix=PRE, tokenLength=10, partyRole=CPO]";

		// Assert that the toString output matches the expected string
		assertEquals(expected, details.toString());
	}
}
