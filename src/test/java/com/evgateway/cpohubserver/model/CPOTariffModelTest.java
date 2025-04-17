//package com.evgateway.cpohubserver.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.evgateway.cpohubserver.form.CPOTariffAltText;
//import com.evgateway.cpohubserver.form.CPOTariffElement;
//import com.evgateway.cpohubserver.form.CPOTariffPrice;
//
//public class CPOTariffModelTest {
//
//	private CPOTariffModel tariff;
//
//	@BeforeEach
//	public void setUp() {
//		tariff = new CPOTariffModel();
//	}
//
//	@Test
//	public void testSetAndGetUid() {
//		String uid = "tariff123";
//		tariff.setUid(uid);
//		assertEquals(uid, tariff.getUid());
//	}
//
//	@Test
//	public void testSetAndGetCountryCode() {
//		String countryCode = "US";
//		tariff.setCountry_code(countryCode);
//		assertEquals(countryCode, tariff.getCountry_code());
//	}
//
//	@Test
//	public void testSetAndGetPartyId() {
//		String partyId = "party123";
//		tariff.setParty_id(partyId);
//		assertEquals(partyId, tariff.getParty_id());
//	}
//
//	@Test
//	public void testSetAndGetCurrency() {
//		String currency = "USD";
//		tariff.setCurrency(currency);
//		assertEquals(currency, tariff.getCurrency());
//	}
//
//	@Test
//	public void testSetAndGetType() {
//		String type = "dynamic";
//		tariff.setType(type);
//		assertEquals(type, tariff.getType());
//	}
//
//	@Test
//	public void testSetAndGetTariffAltUrl() {
//		String url = "http://example.com/tariff";
//		tariff.setTariff_alt_url(url);
//		assertEquals(url, tariff.getTariff_alt_url());
//	}
//
//	@Test
//	public void testSetAndGetMinPrice() {
//		CPOTariffPrice minPrice = new CPOTariffPrice();
//		tariff.setMin_price(minPrice);
//		assertEquals(minPrice, tariff.getMin_price());
//	}
//
//	@Test
//	public void testSetAndGetMaxPrice() {
//		CPOTariffPrice maxPrice = new CPOTariffPrice();
//		tariff.setMax_price(maxPrice);
//		assertEquals(maxPrice, tariff.getMax_price());
//	}
//
//	@Test
//	public void testSetAndGetElements() {
//		List<CPOTariffElement> elements = new ArrayList<>();
//		tariff.setElements(elements);
//		assertEquals(elements, tariff.getElements());
//	}
//
//	@Test
//	public void testSetAndGetStartDateTime() {
//		Date startDateTime = new Date();
//		tariff.setStart_date_time(startDateTime);
//		assertEquals(startDateTime, tariff.getStart_date_time());
//	}
//
//	@Test
//	public void testSetAndGetEndDateTime() {
//		Date endDateTime = new Date();
//		tariff.setEnd_date_time(endDateTime);
//		assertEquals(endDateTime, tariff.getEnd_date_time());
//	}
//
//	@Test
//	public void testSetAndGetLastUpdated() {
//		Date lastUpdated = new Date();
//		tariff.setLast_updated(lastUpdated);
//		assertEquals(lastUpdated, tariff.getLast_updated());
//	}
//
//	@Test
//	public void testSetAndGetId() {
//		String id = "tariff001";
//		tariff.setId(id);
//		assertEquals(id, tariff.getId());
//	}
//
//	@Test
//	public void testSetAndGetTariffAltText() {
//		List<CPOTariffAltText> tariffAltText = new ArrayList<>();
//		tariff.setTariff_alt_text(tariffAltText);
//		assertEquals(tariffAltText, tariff.getTariff_alt_text());
//	}
//
//	@Test
//	public void testSetAndGetCpoCountryCode() {
//		String cpoCountryCode = "DE";
//		tariff.setCpo_country_code(cpoCountryCode);
//		assertEquals(cpoCountryCode, tariff.getCpo_country_code());
//	}
//
//	@Test
//	public void testSetAndGetCpoPartyId() {
//		String cpoPartyId = "cpo123";
//		tariff.setCpo_party_id(cpoPartyId);
//		assertEquals(cpoPartyId, tariff.getCpo_party_id());
//	}
//
//	@Test
//	public void testToString() {
//		String expectedString = "CPOTariffModel [uid=null, country_code=null, party_id=null, cpo_country_code=null, "
//				+ "cpo_party_id=null, id=null, currency=null, type=null, tariff_alt_url=null, min_price=null, "
//				+ "max_price=null, tariff_alt_text=[], elements=[], start_date_time=null, end_date_time=null, "
//				+ "last_updated=null]";
//
//		String result = tariff.toString();
//		assertNotNull(result);
//		assertEquals(expectedString, result);
//	}
//}
