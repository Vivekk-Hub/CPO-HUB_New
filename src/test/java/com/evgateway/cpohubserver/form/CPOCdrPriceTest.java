package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CPOCdrPriceTest {

	@Test
	void testGettersAndSetters() {
		// Create an instance of CPOCdrPrice
		CPOCdrPrice cdrPrice = new CPOCdrPrice();

		// Test excl_vat
		cdrPrice.setExcl_vat(100.50);
		assertEquals(100.50, cdrPrice.getExcl_vat(), 0.001, "Excl VAT should match the value set.");

		// Test incl_vat
		cdrPrice.setIncl_vat(120.60);
		assertEquals(120.60, cdrPrice.getIncl_vat(), 0.001, "Incl VAT should match the value set.");
	}

	@Test
	void testToString() {
		// Create an instance of CPOCdrPrice
		CPOCdrPrice cdrPrice = new CPOCdrPrice();
		cdrPrice.setExcl_vat(100.50);
		cdrPrice.setIncl_vat(120.60);

		// Test the toString method
		String expectedString = "Price [excl_vat=100.5, incl_vat=120.6]";
		assertEquals(expectedString, cdrPrice.toString(),
				"toString method should return the correct string representation.");
	}

	@Test
	void testNullValues() {
		// Create an instance of CPOCdrPrice
		CPOCdrPrice cdrPrice = new CPOCdrPrice();

		// Test default values
		assertEquals(0.0, cdrPrice.getExcl_vat(), 0.001, "Excl VAT should be 0.0 by default.");
		assertEquals(0.0, cdrPrice.getIncl_vat(), 0.001, "Incl VAT should be 0.0 by default.");
	}
}
