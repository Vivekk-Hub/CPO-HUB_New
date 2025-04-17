package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CPOEnvironmentalImpactTest {

    @Test
    void testConstructorAndGettersSetters() {
        CPOEnvironmentalImpact impact = new CPOEnvironmentalImpact();

        // Test setter and getter for source
        impact.setSource("Solar");
        assertEquals("Solar", impact.getSource(), "Source should be Solar.");

        // Test setter and getter for number
        impact.setNumber(100.5);
        assertEquals(100.5, impact.getNumber(), "Number should be 100.5.");
    }

//    @Test
//    void testToString() {
//        CPOEnvironmentalImpact impact = new CPOEnvironmentalImpact();
//        impact.setSource("Wind");
//        impact.setNumber(200.75);
//
//        // Assuming the toString method produces a string representation of the object
//        String expectedString = "CPOEnvironmentalImpact [source=Wind, number=200.75]";
//        assertEquals(expectedString, impact.toString(), "toString should return the expected string representation.");
//    }
}
