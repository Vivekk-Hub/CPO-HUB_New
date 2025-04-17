package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CPOTariffAltTextTest {

    @Test
    void testGetAndSetLanguage() {
        CPOTariffAltText tariffAltText = new CPOTariffAltText();
        tariffAltText.setLanguage("English");
        assertEquals("English", tariffAltText.getLanguage());
    }

    @Test
    void testGetAndSetText() {
        CPOTariffAltText tariffAltText = new CPOTariffAltText();
        tariffAltText.setText("This is a tariff description.");
        assertEquals("This is a tariff description.", tariffAltText.getText());
    }

    @Test
    void testDefaultValues() {
        CPOTariffAltText tariffAltText = new CPOTariffAltText();
        assertNull(tariffAltText.getLanguage());
        assertNull(tariffAltText.getText());
    }
}
