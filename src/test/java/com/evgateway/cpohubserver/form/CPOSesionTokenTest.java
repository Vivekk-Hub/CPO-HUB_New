package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CPOSesionTokenTest {

    @Test
    public void testGetAndSetUid() {
        CPOSesionToken token = new CPOSesionToken();
        token.setUid("12345");
        assertEquals("12345", token.getUid());
    }

    @Test
    public void testGetAndSetType() {
        CPOSesionToken token = new CPOSesionToken();
        token.setType("TypeA");
        assertEquals("TypeA", token.getType());
    }

    @Test
    public void testGetAndSetContractId() {
        CPOSesionToken token = new CPOSesionToken();
        token.setContract_id("Contract123");
        assertEquals("Contract123", token.getContract_id());
    }

    @Test
    public void testGetAndSetCountryCode() {
        CPOSesionToken token = new CPOSesionToken();
        token.setCountry_code("US");
        assertEquals("US", token.getCountry_code());
    }

    @Test
    public void testGetAndSetPartyId() {
        CPOSesionToken token = new CPOSesionToken();
        token.setParty_id("PartyXYZ");
        assertEquals("PartyXYZ", token.getParty_id());
    }

    @Test
    public void testToString() {
        CPOSesionToken token = new CPOSesionToken();
        token.setUid("12345");
        token.setType("TypeA");
        token.setContract_id("Contract123");
        token.setCountry_code("US");
        token.setParty_id("PartyXYZ");

        String expected = "CdrToken [uid=12345, type=TypeA, contract_id=Contract123, country_code=US, party_id=PartyXYZ]";
        assertEquals(expected, token.toString());
    }
}
