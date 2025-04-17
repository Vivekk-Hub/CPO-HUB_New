package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CPOCdrTariffTest {

	@Test
	void testGettersAndSetters() {
		// Create an instance of CPOCdrTariff
		CPOCdrTariff tariff = new CPOCdrTariff();

		// Test country_code
		tariff.setCountry_code("US");
		assertEquals("US", tariff.getCountry_code(), "Country code should match the value set.");

		// Test party_id
		tariff.setParty_id("party123");
		assertEquals("party123", tariff.getParty_id(), "Party ID should match the value set.");

		// Test currency
		tariff.setCurrency("USD");
		assertEquals("USD", tariff.getCurrency(), "Currency should match the value set.");

		// Test type
		tariff.setType("Standard");
		assertEquals("Standard", tariff.getType(), "Type should match the value set.");

		// Test tariff_alt_url
		tariff.setTariff_alt_url("https://www.example.com/tariff");
		assertEquals("https://www.example.com/tariff", tariff.getTariff_alt_url(),
				"Tariff alt URL should match the value set.");

		// Test min_price (CPOCdrTariffPrice)
		CPOCdrTariffPrice minPrice = new CPOCdrTariffPrice();
		minPrice.setExcl_vat(0);
		minPrice.setIncl_vat(0);
		tariff.setMin_price(minPrice);
		assertEquals(minPrice, tariff.getMin_price(), "Min price should match the CPOCdrTariffPrice object set.");

		// Test max_price (CPOCdrTariffPrice)
		CPOCdrTariffPrice maxPrice = new CPOCdrTariffPrice();
		minPrice.setExcl_vat(0);
		minPrice.setIncl_vat(0);
		tariff.setMax_price(maxPrice);
		assertEquals(maxPrice, tariff.getMax_price(), "Max price should match the CPOCdrTariffPrice object set.");

		// Test elements (List<CPOCdrTariffElement>)
		List<CPOCdrTariffElement> elements = new ArrayList<>();
		elements.add(new CPOCdrTariffElement());
		tariff.setElements(elements);
		assertEquals(elements, tariff.getElements(), "Elements list should match the value set.");

		// Test start_date_time
		Date startDateTime = new Date();
		tariff.setStart_date_time(startDateTime);
		assertEquals(startDateTime, tariff.getStart_date_time(), "Start date time should match the value set.");

		// Test end_date_time
		Date endDateTime = new Date();
		tariff.setEnd_date_time(endDateTime);
		assertEquals(endDateTime, tariff.getEnd_date_time(), "End date time should match the value set.");

		// Test last_updated
		Date lastUpdated = new Date();
		tariff.setLast_updated(lastUpdated);
		assertEquals(lastUpdated, tariff.getLast_updated(), "Last updated time should match the value set.");

		// Test CPOCdr (CPOCdr)
		CPOCdr cdr = new CPOCdr();
		tariff.setCdr(cdr);
		assertEquals(cdr, tariff.getCdr(), "CPOCdr should match the value set.");

		// Test tariff_alt_text (List<CPOCdrTariffAltText>)
		List<CPOCdrTariffAltText> tariffAltText = new ArrayList<>();
		tariffAltText.add(new CPOCdrTariffAltText());
		tariff.setTariff_alt_text(tariffAltText);
		assertEquals(tariffAltText, tariff.getTariff_alt_text(), "Tariff alt text list should match the value set.");
	}

	@Test
	void testNullValues() {
		// Create an instance of CPOCdrTariff
		CPOCdrTariff tariff = new CPOCdrTariff();

		// Test default null values
		assertNull(tariff.getCountry_code(), "Country code should be null by default.");
		assertNull(tariff.getParty_id(), "Party ID should be null by default.");
		assertNull(tariff.getCurrency(), "Currency should be null by default.");
		assertNull(tariff.getType(), "Type should be null by default.");
		assertNull(tariff.getTariff_alt_url(), "Tariff alt URL should be null by default.");
		assertNull(tariff.getMin_price(), "Min price should be null by default.");
		assertNull(tariff.getMax_price(), "Max price should be null by default.");
		assertTrue(tariff.getElements().isEmpty(), "Elements list should be empty by default.");
		assertNull(tariff.getStart_date_time(), "Start date time should be null by default.");
		assertNull(tariff.getEnd_date_time(), "End date time should be null by default.");
		assertNull(tariff.getLast_updated(), "Last updated time should be null by default.");
		assertNull(tariff.getCdr(), "CPOCdr should be null by default.");
		assertTrue(tariff.getTariff_alt_text().isEmpty(), "Tariff alt text list should be empty by default.");
	}

//	@Test
//	void testToString() {
//		// Create an instance of CPOCdrTariff
//		CPOCdrTariff tariff = new CPOCdrTariff();
//		tariff.setCountry_code("US");
//		tariff.setParty_id("party123");
//		tariff.setCurrency("USD");
//		tariff.setType("Standard");
//		tariff.setTariff_alt_url("https://www.example.com/tariff");
//
//		// Test the toString method
//		CPOCdrTariffPrice minPrice = new CPOCdrTariffPrice();
//		minPrice.setExcl_vat(0);
//		minPrice.setIncl_vat(0);
//		tariff.setMin_price(minPrice);
//
//		CPOCdrTariffPrice maxPrice = new CPOCdrTariffPrice();
//		minPrice.setExcl_vat(0);
//		minPrice.setIncl_vat(0);
//		tariff.setMax_price(maxPrice);
//
//		Date startDateTime = new Date();
//		tariff.setStart_date_time(startDateTime);
//
//		Date endDateTime = new Date();
//		tariff.setEnd_date_time(endDateTime);
//
//		Date lastUpdated = new Date();
//		tariff.setLast_updated(lastUpdated);
//
//		CPOCdr cdr = new CPOCdr();
//		tariff.setCdr(cdr);
//
//		List<CPOCdrTariffAltText> tariffAltText = new ArrayList<>();
//		tariffAltText.add(new CPOCdrTariffAltText());
//		tariff.setTariff_alt_text(tariffAltText);
//		System.out.println("working");
//		String expectedString = "CPOCdrTariff [country_code=US, party_id=party123, currency=USD, type=Standard, tariff_alt_url=https://www.example.com/tariff, min_price="
//				+ minPrice + ", max_price=" + maxPrice + ", elements=" + tariff.getElements() + ", start_date_time="
//				+ startDateTime + ", end_date_time=" + endDateTime + ", last_updated=" + lastUpdated + ", cdr=" + cdr
//				+ ", tariff_alt_text=" + tariffAltText + "]";
//		System.out.println(expectedString.toString());
//		System.out.println("working"+tariff.toString());
//		assertEquals(expectedString, tariff.toString(),
//				"toString method should return the correct string representation.");
//		
//	}
}
