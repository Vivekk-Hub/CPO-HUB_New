package com.evgateway.cpohubserver.form;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class CPOCdrPriceComponentTest {

    @Test
    void testGettersAndSetters() {
        // Create an instance of CPOCdrPriceComponent
        CPOCdrPriceComponent priceComponent = new CPOCdrPriceComponent();

        // Test type
        priceComponent.setType("Fixed");
        assertEquals("Fixed", priceComponent.getType(), "Type should match the value set.");

        // Test price
        priceComponent.setPrice(100.75);
        assertEquals(100.75, priceComponent.getPrice(), 0.001, "Price should match the value set.");

        // Test VAT
        priceComponent.setVat(15.25);
        assertEquals(15.25, priceComponent.getVat(), 0.001, "VAT should match the value set.");

        // Test step size
        priceComponent.setStep_size(10);
        assertEquals(10, priceComponent.getStep_size(), "Step size should match the value set.");

        // Test elements (CPOCdrTariffElement)
        CPOCdrTariffElement elements = new CPOCdrTariffElement();
        // Assuming CPOCdrTariffElement has a simple setName method
        elements.setCdr_tariff(null);
        priceComponent.setElements(elements);
        assertEquals(elements, priceComponent.getElements(), "Elements should match the CPOCdrTariffElement object set.");
    }

    @Test
    void testToString() {
        // Create an instance of CPOCdrPriceComponent
        CPOCdrPriceComponent priceComponent = new CPOCdrPriceComponent();
        priceComponent.setType("Fixed");
        priceComponent.setPrice(100.75);
        priceComponent.setVat(15.25);
        priceComponent.setStep_size(10);

        // Test the toString method
        CPOCdrTariffElement elements = new CPOCdrTariffElement();
        elements.setCdr_tariff(null);
        priceComponent.setElements(elements);

        String expectedString = "PriceComponent [type=Fixed, price=100.75, vat=15.25, step_size=10, elements=" + elements + "]";
        assertEquals(expectedString, priceComponent.toString(), "toString method should return the correct string representation.");
    }

    @Test
    void testNullValues() {
        // Create an instance of CPOCdrPriceComponent
        CPOCdrPriceComponent priceComponent = new CPOCdrPriceComponent();

        // Test default null values
        assertNull(priceComponent.getType(), "Type should be null by default.");
        assertEquals(0.0, priceComponent.getPrice(), 0.001, "Price should be 0.0 by default.");
        assertEquals(0.0, priceComponent.getVat(), 0.001, "VAT should be 0.0 by default.");
        assertEquals(0, priceComponent.getStep_size(), "Step size should be 0 by default.");
        assertNull(priceComponent.getElements(), "Elements should be null by default.");
    }
}
