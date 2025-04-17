package com.evgateway.cpohubserver.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CPOEndpointModelTest {

	@Test
	public void testGetIdentifier() {
		CPOEndpointModel endpoint = new CPOEndpointModel();
		String identifier = "testIdentifier";
		endpoint.setIdentifier(identifier);
		assertEquals(identifier, endpoint.getIdentifier());
	}

	@Test
	public void testSetIdentifier() {
		CPOEndpointModel endpoint = new CPOEndpointModel();
		String identifier = "testIdentifier";
		endpoint.setIdentifier(identifier);
		assertEquals(identifier, endpoint.getIdentifier());
	}

	@Test
	public void testGetUrl() {
		CPOEndpointModel endpoint = new CPOEndpointModel();
		String url = "http://example.com";
		endpoint.setUrl(url);
		assertEquals(url, endpoint.getUrl());
	}

	@Test
	public void testSetUrl() {
		CPOEndpointModel endpoint = new CPOEndpointModel();
		String url = "http://example.com";
		endpoint.setUrl(url);
		assertEquals(url, endpoint.getUrl());
	}

	@Test
	public void testGetRole() {
		CPOEndpointModel endpoint = new CPOEndpointModel();
		String role = "CPO";
		endpoint.setRole(role);
		assertEquals(role, endpoint.getRole());
	}

	@Test
	public void testSetRole() {
		CPOEndpointModel endpoint = new CPOEndpointModel();
		String role = "CPO";
		endpoint.setRole(role);
		assertEquals(role, endpoint.getRole());
	}

	@Test
	public void testGetPartyId() {
		CPOEndpointModel endpoint = new CPOEndpointModel();
		String partyId = "NL-TST";
		endpoint.setParty_id(partyId);
		assertEquals(partyId, endpoint.getParty_id());
	}

	@Test
	public void testSetPartyId() {
		CPOEndpointModel endpoint = new CPOEndpointModel();
		String partyId = "NL-TST";
		endpoint.setParty_id(partyId);
		assertEquals(partyId, endpoint.getParty_id());
	}

	@Test
	public void testGetCountryCode() {
		CPOEndpointModel endpoint = new CPOEndpointModel();
		String countryCode = "NL";
		endpoint.setCountry_code(countryCode);
		assertEquals(countryCode, endpoint.getCountry_code());
	}

	@Test
	public void testSetCountryCode() {
		CPOEndpointModel endpoint = new CPOEndpointModel();
		String countryCode = "NL";
		endpoint.setCountry_code(countryCode);
		assertEquals(countryCode, endpoint.getCountry_code());
	}

	@Test
	public void testGetVersion() {
		CPOEndpointModel endpoint = new CPOEndpointModel();
		String version = "2.2.1";
		endpoint.setVersion(version);
		assertEquals(version, endpoint.getVersion());
	}

	@Test
	public void testSetVersion() {
		CPOEndpointModel endpoint = new CPOEndpointModel();
		String version = "2.2.1";
		endpoint.setVersion(version);
		assertEquals(version, endpoint.getVersion());
	}

	@Test
	public void testToString() {
		CPOEndpointModel endpoint = new CPOEndpointModel();
		endpoint.setIdentifier("testIdentifier");
		endpoint.setUrl("http://example.com");
		endpoint.setRole("CPO");
		endpoint.setParty_id("NL-TST");
		endpoint.setCountry_code("NL");
		endpoint.setVersion("2.2.1");
		String expectedString = "Endpoint [identifier=testIdentifier, url=http://example.com, role=CPO, party_id=NL-TST, country_code=NL, version=2.2.1]";
		assertEquals(expectedString, endpoint.toString());
	}
}
