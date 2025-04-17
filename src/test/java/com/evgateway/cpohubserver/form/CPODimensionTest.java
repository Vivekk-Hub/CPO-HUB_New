package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CPODimensionTest {

    @Test
    void testGettersAndSetters() {
        // Create an instance of CPODimension
        CPODimension dimension = new CPODimension();

        // Set values for the fields
        dimension.setType("Volume");
        dimension.setVolume(150.5);

        // Test each field with corresponding getters
        assertEquals("Volume", dimension.getType(), "Type should match the value set.");
        assertEquals(150.5, dimension.getVolume(), "Volume should match the value set.");
    }

    @Test
    void testNullValues() {
        // Create an instance of CPODimension
        CPODimension dimension = new CPODimension();

        // Test default values (should be null for objects and 0 for primitives)
        assertNull(dimension.getType(), "Type should be null by default.");
        assertEquals(0.0, dimension.getVolume(), "Volume should be 0 by default.");
    }

    @Test
    void testToString() {
        // Create an instance of CPODimension
        CPODimension dimension = new CPODimension();

        // Set values for the fields
        dimension.setType("Volume");
        dimension.setVolume(150.5);

        // Test the toString method
        String expectedString = "CdrDimension [type=Volume, volume=150.5]";
        assertEquals(expectedString, dimension.toString(), "toString should return the expected string.");
    }
}
