package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CPOTariffPriceTest {

    @Test
    void testGetAndSetExclVat() {
        CPOTariffPrice price = new CPOTariffPrice();
        price.setExcl_vat(100.50);
        
        assertEquals(100.50, price.getExcl_vat(), "The excl_vat value should be correctly set and retrieved.");
    }

    @Test
    void testGetAndSetInclVat() {
        CPOTariffPrice price = new CPOTariffPrice();
        price.setIncl_vat(120.60);
        
        assertEquals(120.60, price.getIncl_vat(), "The incl_vat value should be correctly set and retrieved.");
    }

    @Test
    void testToString() {
        CPOTariffPrice price = new CPOTariffPrice();
        price.setExcl_vat(100.50);
        price.setIncl_vat(120.60);
        
        String expectedString = "Price [excl_vat=100.5, incl_vat=120.6]";
        assertEquals(expectedString, price.toString(), "The toString method should return the correct string representation.");
    }

    @Test
    void testDefaultValues() {
        CPOTariffPrice price = new CPOTariffPrice();
        
        assertEquals(0.0, price.getExcl_vat(), "Default excl_vat should be 0.0");
        assertEquals(0.0, price.getIncl_vat(), "Default incl_vat should be 0.0");
    }
}
