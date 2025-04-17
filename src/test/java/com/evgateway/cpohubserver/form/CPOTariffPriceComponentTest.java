package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CPOTariffPriceComponentTest {

	@Test
	void testGetAndSetPrice() {
		CPOTariffPriceComponent component = new CPOTariffPriceComponent();
		component.setPrice(99.99);

		assertEquals(99.99, component.getPrice(), "The price value should be correctly set and retrieved.");
	}

	@Test
	void testGetAndSetStepSize() {
		CPOTariffPriceComponent component = new CPOTariffPriceComponent();
		component.setStep_size(10);

		assertEquals(10, component.getStep_size(), "The step_size value should be correctly set and retrieved.");
	}

	@Test
	void testGetAndSetVat() {
		CPOTariffPriceComponent component = new CPOTariffPriceComponent();
		component.setVat(15.0);

		assertEquals(15.0, component.getVat(), "The vat value should be correctly set and retrieved.");
	}

	@Test
	void testGetAndSetType() {
		CPOTariffPriceComponent component = new CPOTariffPriceComponent();
		component.setType("Hourly");

		assertEquals("Hourly", component.getType(), "The type value should be correctly set and retrieved.");
	}

	@Test
	void testDefaultValues() {
		CPOTariffPriceComponent component = new CPOTariffPriceComponent();

		assertEquals(0.0, component.getPrice(), "Default price should be 0.0");
		assertEquals(0, component.getStep_size(), "Default step_size should be 0");
		assertEquals(0.0, component.getVat(), "Default vat should be 0.0");
		assertNull(component.getType(), "Default type should be null");
	}

}
