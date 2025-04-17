package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CPOPriceTest {

    @Test
    public void testGetAndSetExclVat() {
        CPOPrice price = new CPOPrice();
        price.setExcl_vat(100.50);
        assertEquals(100.50, price.getExcl_vat(), 0.001); // Tolerance added for precision
    }

    @Test
    public void testGetAndSetInclVat() {
        CPOPrice price = new CPOPrice();
        price.setIncl_vat(120.60);
        assertEquals(120.60, price.getIncl_vat(), 0.001); // Tolerance added for precision
    }

    @Test
    public void testToString() {
        CPOPrice price = new CPOPrice();
        price.setExcl_vat(100.50);
        price.setIncl_vat(120.60);
        String toStringResult = price.toString();
        assertEquals("Price [excl_vat=100.5, incl_vat=120.6]", toStringResult);
    }
}
