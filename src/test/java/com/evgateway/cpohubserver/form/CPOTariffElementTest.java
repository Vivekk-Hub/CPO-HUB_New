package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

class CPOTariffElementTest {

    @Test
    void testGetAndSetPriceComponents() {
        CPOTariffElement tariffElement = new CPOTariffElement();
        CPOTariffPriceComponent component1 = new CPOTariffPriceComponent();
        CPOTariffPriceComponent component2 = new CPOTariffPriceComponent();
        
        tariffElement.setPrice_components(Arrays.asList(component1, component2));
        
        assertEquals(2, tariffElement.getPrice_components().size());
        assertTrue(tariffElement.getPrice_components().contains(component1));
        assertTrue(tariffElement.getPrice_components().contains(component2));
    }

    @Test
    void testGetAndSetRestrictions() {
        CPOTariffElement tariffElement = new CPOTariffElement();
        CPOTariffRestrictions restrictions = new CPOTariffRestrictions();
        
        tariffElement.setRestrictions(restrictions);
        
        assertEquals(restrictions, tariffElement.getRestrictions());
    }

    @Test
    void testDefaultValues() {
        CPOTariffElement tariffElement = new CPOTariffElement();
        
        assertNotNull(tariffElement.getPrice_components());
        assertTrue(tariffElement.getPrice_components().isEmpty());
        assertNull(tariffElement.getRestrictions());
    }
}
