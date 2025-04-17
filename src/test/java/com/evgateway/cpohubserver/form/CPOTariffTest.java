package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class CPOTariffTest {

    @Test
    void testGetAndSetCountryCode() {
        CPOTariff tariff = new CPOTariff();
        tariff.setCountry_code("US");
        assertEquals("US", tariff.getCountry_code());
    }

    @Test
    void testGetAndSetPartyId() {
        CPOTariff tariff = new CPOTariff();
        tariff.setParty_id("ABC");
        assertEquals("ABC", tariff.getParty_id());
    }

    @Test
    void testGetAndSetCurrency() {
        CPOTariff tariff = new CPOTariff();
        tariff.setCurrency("USD");
        assertEquals("USD", tariff.getCurrency());
    }

    @Test
    void testGetAndSetType() {
        CPOTariff tariff = new CPOTariff();
        tariff.setType("Regular");
        assertEquals("Regular", tariff.getType());
    }

    @Test
    void testGetAndSetTariffAltUrl() {
        CPOTariff tariff = new CPOTariff();
        tariff.setTariff_alt_url("http://example.com");
        assertEquals("http://example.com", tariff.getTariff_alt_url());
    }

    @Test
    void testGetAndSetMinPrice() {
        CPOTariff tariff = new CPOTariff();
        CPOTariffPrice minPrice = new CPOTariffPrice();
        tariff.setMin_price(minPrice);
        assertEquals(minPrice, tariff.getMin_price());
    }

    @Test
    void testGetAndSetMaxPrice() {
        CPOTariff tariff = new CPOTariff();
        CPOTariffPrice maxPrice = new CPOTariffPrice();
        tariff.setMax_price(maxPrice);
        assertEquals(maxPrice, tariff.getMax_price());
    }

    @Test
    void testGetAndSetElements() {
        CPOTariff tariff = new CPOTariff();
        List<CPOTariffElement> elements = new ArrayList<>();
        tariff.setElements(elements);
        assertEquals(elements, tariff.getElements());
    }

    @Test
    void testGetAndSetStartDateTime() {
        CPOTariff tariff = new CPOTariff();
        Date startDateTime = new Date();
        tariff.setStart_date_time(startDateTime);
        assertEquals(startDateTime, tariff.getStart_date_time());
    }

    @Test
    void testGetAndSetEndDateTime() {
        CPOTariff tariff = new CPOTariff();
        Date endDateTime = new Date();
        tariff.setEnd_date_time(endDateTime);
        assertEquals(endDateTime, tariff.getEnd_date_time());
    }

    @Test
    void testGetAndSetLastUpdated() {
        CPOTariff tariff = new CPOTariff();
        Date lastUpdated = new Date();
        tariff.setLast_updated(lastUpdated);
        assertEquals(lastUpdated, tariff.getLast_updated());
    }

    @Test
    void testGetAndSetId() {
        CPOTariff tariff = new CPOTariff();
        tariff.setId("1234");
        assertEquals("1234", tariff.getId());
    }

    @Test
    void testGetAndSetTariffAltText() {
        CPOTariff tariff = new CPOTariff();
        List<CPOTariffAltText> altTextList = new ArrayList<>();
        tariff.setTariff_alt_text(altTextList);
        assertEquals(altTextList, tariff.getTariff_alt_text());
    }

    @Test
    void testDefaultValues() {
        CPOTariff tariff = new CPOTariff();
        assertNull(tariff.getCountry_code());
        assertNull(tariff.getParty_id());
        assertNull(tariff.getCurrency());
        assertNull(tariff.getType());
        assertNull(tariff.getTariff_alt_url());
        assertNull(tariff.getMin_price());
        assertNull(tariff.getMax_price());
        assertTrue(tariff.getElements().isEmpty());
        assertNull(tariff.getStart_date_time());
        assertNull(tariff.getEnd_date_time());
        assertNull(tariff.getLast_updated());
        assertNull(tariff.getId());
        assertTrue(tariff.getTariff_alt_text().isEmpty());
    }
}
