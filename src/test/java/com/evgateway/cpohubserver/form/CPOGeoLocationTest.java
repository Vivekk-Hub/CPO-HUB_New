package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CPOGeoLocationTest {

    @Test
    public void testGetLatitude() {
        CPOGeoLocation geoLocation = new CPOGeoLocation();
        geoLocation.setLatitude("40.7128");
        assertEquals("40.7128", geoLocation.getLatitude());
    }

    @Test
    public void testSetLatitude() {
        CPOGeoLocation geoLocation = new CPOGeoLocation();
        geoLocation.setLatitude("40.7128");
        assertEquals("40.7128", geoLocation.getLatitude());
    }

    @Test
    public void testGetLongitude() {
        CPOGeoLocation geoLocation = new CPOGeoLocation();
        geoLocation.setLongitude("-74.0060");
        assertEquals("-74.0060", geoLocation.getLongitude());
    }

    @Test
    public void testSetLongitude() {
        CPOGeoLocation geoLocation = new CPOGeoLocation();
        geoLocation.setLongitude("-74.0060");
        assertEquals("-74.0060", geoLocation.getLongitude());
    }
}
