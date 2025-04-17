package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class CPOCdrTariffAltTextTest {

    @Test
    void testGettersAndSetters() {
        // Create an instance of CPOCdrTariffAltText
        CPOCdrTariffAltText altText = new CPOCdrTariffAltText();

        // Test language
        altText.setLanguage("en");
        assertEquals("en", altText.getLanguage(), "Language should match the value set.");

        // Test text
        altText.setText("Standard tariff");
        assertEquals("Standard tariff", altText.getText(), "Text should match the value set.");
    }

    @Test
    void testNullValues() {
        // Create an instance of CPOCdrTariffAltText
        CPOCdrTariffAltText altText = new CPOCdrTariffAltText();

        // Test default null values
        assertNull(altText.getLanguage(), "Language should be null by default.");
        assertNull(altText.getText(), "Text should be null by default.");
    }

//    @Test
//    void testToString() {
//        // Create an instance of CPOCdrTariffAltText
//        CPOCdrTariffAltText altText = new CPOCdrTariffAltText();
//        altText.setLanguage("en");
//        altText.setText("Standard tariff");
//
//        // Test the toString method
//        String expectedString = "CPOCdrTariffAltText [language=en, text=Standard tariff]";
//        assertEquals(expectedString, altText.toString());
//    }
}
