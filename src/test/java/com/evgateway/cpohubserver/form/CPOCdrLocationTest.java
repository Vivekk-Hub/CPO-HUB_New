package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CPOCdrLocationTest {

    @Test
    void testCPOCdrLocationInstantiation() {
        // Create an instance of CPOCdrLocation
        CPOCdrLocation location = new CPOCdrLocation();

        // Assert that the instance is not null
        assertNotNull(location, "CPOCdrLocation instance should be successfully created.");
    }

    @Test
    void testSettersAndGetters() {
        // Create an instance of CPOCdrLocation
        CPOCdrLocation location = new CPOCdrLocation();

        // Set values
        location.setName("Test Location");
        location.setId("LOC123");
        location.setAddress("123 Test St.");
        location.setCity("Test City");
        location.setPostal_code("12345");
        location.setState("Test State");
        location.setCountry("Test Country");
        CPOGeoLocation coordinates = new CPOGeoLocation();
        location.setCoordinates(coordinates);
        location.setEvse_uid("EVSE123");
        location.setEvse_id("EVSE001");
        location.setConnector_id("CON123");
        location.setConnector_standard("IEC 62196");
        location.setConnector_format("CABLE");
        location.setConnector_power_type("AC");

        // Assert values
        assertEquals("Test Location", location.getName());
        assertEquals("LOC123", location.getId());
        assertEquals("123 Test St.", location.getAddress());
        assertEquals("Test City", location.getCity());
        assertEquals("12345", location.getPostal_code());
        assertEquals("Test State", location.getState());
        assertEquals("Test Country", location.getCountry());
        assertEquals(coordinates, location.getCoordinates());
        assertEquals("EVSE123", location.getEvse_uid());
        assertEquals("EVSE001", location.getEvse_id());
        assertEquals("CON123", location.getConnector_id());
        assertEquals("IEC 62196", location.getConnector_standard());
        assertEquals("CABLE", location.getConnector_format());
        assertEquals("AC", location.getConnector_power_type());
    }
}
