package com.evgateway.cpohubserver.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CPOPartyAuthenticateTest {

	@Test
	public void testSetAndGetPartyId() {
		CPOPartyAuthenticate partyAuthenticate = new CPOPartyAuthenticate();
		String partyId = "party123";
		partyAuthenticate.setParty_id(partyId);
		assertEquals(partyId, partyAuthenticate.getParty_id());
	}

	@Test
	public void testToString() {
		CPOPartyAuthenticate partyAuthenticate = new CPOPartyAuthenticate();
		String partyId = "party123";
		partyAuthenticate.setParty_id(partyId);

		String expectedString = "CPOPartyAuthenticate [party_id=party123]";
		assertEquals(expectedString, partyAuthenticate.toString());
	}
}
