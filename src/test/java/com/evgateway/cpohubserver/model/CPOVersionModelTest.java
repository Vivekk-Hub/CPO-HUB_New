package com.evgateway.cpohubserver.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CPOVersionModelTest {

	private CPOVersionModel versionModel;

	@BeforeEach
	public void setUp() {
		versionModel = new CPOVersionModel();
	}

	@Test
	public void testSetAndGetVersion() {
		String version = "1.0";
		versionModel.setVersion(version);
		assertEquals(version, versionModel.getVersion());
	}

	@Test
	public void testSetAndGetUrl() {
		String url = "https://api.example.com";
		versionModel.setUrl(url);
		assertEquals(url, versionModel.getUrl());
	}

	@Test
	public void testSetAndGetPartyId() {
		String partyId = "party123";
		versionModel.setParty_id(partyId);
		assertEquals(partyId, versionModel.getParty_id());
	}

	@Test
	public void testSetAndGetCountryCode() {
		String countryCode = "US";
		versionModel.setCountry_code(countryCode);
		assertEquals(countryCode, versionModel.getCountry_code());
	}

	@Test
	public void testToString() {
		String version = "1.0";
		String url = "https://api.example.com";
		String partyId = "party123";
		String countryCode = "US";

		versionModel.setVersion(version);
		versionModel.setUrl(url);
		versionModel.setParty_id(partyId);
		versionModel.setCountry_code(countryCode);

		String expectedString = "Version [version=1.0, url=https://api.example.com, party_id=party123, country_code=US]";
		String result = versionModel.toString();

		assertNotNull(result);
		assertEquals(expectedString, result);
	}
}
