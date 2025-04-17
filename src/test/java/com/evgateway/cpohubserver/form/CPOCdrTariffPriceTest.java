package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CPOCdrTariffPriceTest {

	@Test
	void testGettersAndSetters() {
		// Create an instance of CPOCdrTariffPrice
		CPOCdrTariffPrice tariffPrice = new CPOCdrTariffPrice();

		// Set values for excl_vat and incl_vat
		tariffPrice.setExcl_vat(100.5);
		tariffPrice.setIncl_vat(120.6);

		// Test excl_vat
		assertEquals(100.5, tariffPrice.getExcl_vat(), "Excl VAT should match the value set.");

		// Test incl_vat
		assertEquals(120.6, tariffPrice.getIncl_vat(), "Incl VAT should match the value set.");
	}

	@Test
	void testToString() {
		// Create an instance of CPOCdrTariffPrice
		CPOCdrTariffPrice tariffPrice = new CPOCdrTariffPrice();

		// Set values for excl_vat and incl_vat
		tariffPrice.setExcl_vat(100.5);
		tariffPrice.setIncl_vat(120.6);

		// Test the toString method
		String expectedString = "CPOCdrTariffPrice [excl_vat=100.5, incl_vat=120.6]";
		assertEquals(expectedString, tariffPrice.toString(), "toString should return the expected string.");
	}

	@Test
	void testNullValues() {
		// Create an instance of CPOCdrTariffPrice
		CPOCdrTariffPrice tariffPrice = new CPOCdrTariffPrice();

		// Test default values (should be 0.0 for primitive double)
		assertEquals(0.0, tariffPrice.getExcl_vat(), "Excl VAT should be 0.0 by default.");
		assertEquals(0.0, tariffPrice.getIncl_vat(), "Incl VAT should be 0.0 by default.");
	}
}
