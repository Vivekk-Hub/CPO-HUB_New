package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class CPOCdrTariffElementTest {

	@Test
	void testGettersAndSetters() {
		// Create an instance of CPOCdrTariffElement
		CPOCdrTariffElement tariffElement = new CPOCdrTariffElement();

		// Create and set price components
		CPOCdrPriceComponent priceComponent1 = new CPOCdrPriceComponent();
		priceComponent1.setPrice(10.5);
		priceComponent1.setVat(1.0);
		priceComponent1.setType("type1");
		priceComponent1.setStep_size(1);

		CPOCdrPriceComponent priceComponent2 = new CPOCdrPriceComponent();
		priceComponent2.setPrice(15.0);
		priceComponent2.setVat(1.2);
		priceComponent2.setType("type2");
		priceComponent2.setStep_size(2);

		tariffElement.setPrice_components(Arrays.asList(priceComponent1, priceComponent2));

		// Create and set restrictions
		CPOCdrTariffRestrictions restrictions = new CPOCdrTariffRestrictions();

		restrictions.setMax_kwh("40");
		restrictions.setMin_power("50");

		tariffElement.setRestrictions(restrictions);

		// Create and set cdr_tariff
		CPOCdrTariff cdrTariff = new CPOCdrTariff();
		cdrTariff.setId("someId");
		tariffElement.setCdr_tariff(cdrTariff);

		// Test price components
		assertEquals(2, tariffElement.getPrice_components().size(), "Price components size should be 2.");
		assertEquals(priceComponent1, tariffElement.getPrice_components().get(0),
				"First price component should match.");
		assertEquals(priceComponent2, tariffElement.getPrice_components().get(1),
				"Second price component should match.");

		// Test restrictions
		assertEquals("40", tariffElement.getRestrictions().getMax_kwh());

		// Test cdr_tariff
		assertEquals("someId", tariffElement.getCdr_tariff().getId(), "CdrTariff ID should match.");
	}

	@Test
	void testNullValues() {
		// Create an instance of CPOCdrTariffElement
		CPOCdrTariffElement tariffElement = new CPOCdrTariffElement();

		assertNull(tariffElement.getRestrictions(), "Restrictions should be null by default.");
		assertNull(tariffElement.getCdr_tariff(), "CdrTariff should be null by default.");
	}
}
