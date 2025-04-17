package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Date;

class CPOChargingPeriodTest {

    @Test
    void testGettersAndSetters() {
        // Create an instance of CPOChargingPeriod
        CPOChargingPeriod chargingPeriod = new CPOChargingPeriod();

        // Set values for the fields
        Date date = new Date();
        chargingPeriod.setStart_date_time(date);
        chargingPeriod.setTariff_id("T123");
        
        CPODimension dimension = new CPODimension();
        dimension.setType("dimension1");
        dimension.setVolume(10);
        chargingPeriod.setDimensions(Arrays.asList(dimension));
        
        
//        chargingPeriod.setDimensions(Arrays.asList(new CPODimension("dimension1", 10), new CPODimension("dimension2", 20)));

        // Test each field with corresponding getters
        assertEquals(date, chargingPeriod.getStart_date_time(), "Start date time should match the value set.");
        assertEquals("T123", chargingPeriod.getTariff_id(), "Tariff ID should match the value set.");
        assertNotNull(chargingPeriod.getDimensions(), "Dimensions list should not be null.");
        assertEquals(1, chargingPeriod.getDimensions().size(), "Dimensions list size should match the number of elements.");
        assertEquals("dimension1", chargingPeriod.getDimensions().get(0).getType(), "First dimension type should match the value set.");
        assertEquals(10, chargingPeriod.getDimensions().get(0).getVolume(), "First dimension volume should match the value set.");
    }

    @Test
    void testNullValues() {
        // Create an instance of CPOChargingPeriod
        CPOChargingPeriod chargingPeriod = new CPOChargingPeriod();

        // Test default values (should be null or empty by default)
        assertNull(chargingPeriod.getStart_date_time(), "Start date time should be null by default.");
        assertNull(chargingPeriod.getTariff_id(), "Tariff ID should be null by default.");
        assertNull(chargingPeriod.getDimensions(), "Dimensions list should be null by default.");
    }

    @Test
    void testSetAndGetDimensions() {
        // Create an instance of CPOChargingPeriod
        CPOChargingPeriod chargingPeriod = new CPOChargingPeriod();

        // Create a dimension and set it in the charging period
        CPODimension dimension = new CPODimension();
        dimension.setType("TestDimension");
        dimension.setVolume(100);
        chargingPeriod.setDimensions(Arrays.asList(dimension));

        // Test that the dimensions are set correctly
        assertNotNull(chargingPeriod.getDimensions(), "Dimensions should not be null.");
        assertEquals(1, chargingPeriod.getDimensions().size(), "Dimensions list size should be 1.");
        assertEquals("TestDimension", chargingPeriod.getDimensions().get(0).getType(), "Dimension type should match the value set.");
        assertEquals(100, chargingPeriod.getDimensions().get(0).getVolume(), "Dimension volume should match the value set.");
    
    }
}
