package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CPORolesTest {

    @Test
    public void testGetAndSetPartyId() {
        CPORoles roles = new CPORoles();
        roles.setParty_id("12345");
        assertEquals("12345", roles.getParty_id());
    }

    @Test
    public void testGetAndSetCountryCode() {
        CPORoles roles = new CPORoles();
        roles.setCountry_code("US");
        assertEquals("US", roles.getCountry_code());
    }

    @Test
    public void testGetAndSetRole() {
        CPORoles roles = new CPORoles();
        roles.setRole("CPO");
        assertEquals("CPO", roles.getRole());
    }

    @Test
    public void testGetAndSetBusinessDetails() {
        CPORoles roles = new CPORoles();
        CPOBusinessDetails businessDetails = new CPOBusinessDetails();
        businessDetails.setName("Test Business");
        roles.setBusiness_details(businessDetails);

        assertEquals("Test Business", roles.getBusiness_details().getName());
    }
}
