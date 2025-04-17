//package com.evgateway.cpohubserver.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//
//import com.evgateway.cpohubserver.form.CPOAdditionalGeoLocation;
//import com.evgateway.cpohubserver.form.CPOBusinessDetails;
//import com.evgateway.cpohubserver.form.CPODisplayText;
//import com.evgateway.cpohubserver.form.CPOEnergyMix;
//import com.evgateway.cpohubserver.form.CPOGeoLocation;
//import com.evgateway.cpohubserver.form.CPOHours;
//import com.evgateway.cpohubserver.form.CPOImage;
//import com.evgateway.cpohubserver.form.CPOPublishTokenType;
//
//public class CPOLocationModelTest {
//
//	@Test
//	public void testSetAndGetU_id() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String uId = "u123";
//		cpoLocationModel.setU_id(uId);
//		assertEquals(uId, cpoLocationModel.getU_id());
//	}
//
//	@Test
//	public void testSetAndGetId() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String id = "loc123";
//		cpoLocationModel.setId(id);
//		assertEquals(id, cpoLocationModel.getId());
//	}
//
//	@Test
//	public void testSetAndGetpublish_allowed_to() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		List<CPOPublishTokenType> list = new ArrayList<CPOPublishTokenType>();
//		CPOPublishTokenType CPOPublishTokenType = new CPOPublishTokenType();
//		CPOPublishTokenType.setIssuer("EVG");
//		list.add(CPOPublishTokenType);
//		cpoLocationModel.setPublish_allowed_to(list);
//		assertEquals("EVG", cpoLocationModel.getPublish_allowed_to().get(0).getIssuer());
//	}
//
//	@Test
//	public void testSetAndGetsuboperator() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		CPOBusinessDetails suboperator = new CPOBusinessDetails();
//		suboperator.setName("loc123");
//		cpoLocationModel.setSuboperator(suboperator);
//		assertEquals("loc123", cpoLocationModel.getSuboperator().getName());
//	}
//
//	@Test
//	public void testSetAndGetUid() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String uid = "uid123";
//		cpoLocationModel.setUid(uid);
//		assertEquals(uid, cpoLocationModel.getUid());
//	}
//
//	@Test
//	public void testSetAndGetType() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String type = "EVSE";
//		cpoLocationModel.setType(type);
//		assertEquals(type, cpoLocationModel.getType());
//	}
//
//	@Test
//	public void testSetAndGetCountryCode() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String countryCode = "US";
//		cpoLocationModel.setCountry_code(countryCode);
//		assertEquals(countryCode, cpoLocationModel.getCountry_code());
//	}
//
//	@Test
//	public void testSetAndGetCpoCountryCode() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String cpoCountryCode = "IN";
//		cpoLocationModel.setCpo_country_code(cpoCountryCode);
//		assertEquals(cpoCountryCode, cpoLocationModel.getCpo_country_code());
//	}
//
//	@Test
//	public void testSetAndGetCpoPartyId() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String cpoPartyId = "CPO001";
//		cpoLocationModel.setCpo_party_id(cpoPartyId);
//		assertEquals(cpoPartyId, cpoLocationModel.getCpo_party_id());
//	}
//
//	@Test
//	public void testSetAndGetPublish() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		boolean publish = true;
//		cpoLocationModel.setPublish(publish);
//		assertTrue(cpoLocationModel.isPublish());
//	}
//
//	@Test
//	public void testSetAndGetName() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String name = "EV Station 1";
//		cpoLocationModel.setName(name);
//		assertEquals(name, cpoLocationModel.getName());
//	}
//
//	@Test
//	public void testSetAndGetAddress() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String address = "123 Main St";
//		cpoLocationModel.setAddress(address);
//		assertEquals(address, cpoLocationModel.getAddress());
//	}
//
//	@Test
//	public void testSetAndGetCity() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String city = "New York";
//		cpoLocationModel.setCity(city);
//		assertEquals(city, cpoLocationModel.getCity());
//	}
//
//	@Test
//	public void testSetAndGetState() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String state = "NY";
//		cpoLocationModel.setState(state);
//		assertEquals(state, cpoLocationModel.getState());
//	}
//
//	@Test
//	public void testSetAndGetPostalCode() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String postalCode = "10001";
//		cpoLocationModel.setPostal_code(postalCode);
//		assertEquals(postalCode, cpoLocationModel.getPostal_code());
//	}
//
//	@Test
//	public void testSetAndGetCountry() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String country = "USA";
//		cpoLocationModel.setCountry(country);
//		assertEquals(country, cpoLocationModel.getCountry());
//	}
//
//	@Test
//	public void testSetAndGetCoordinates() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		CPOGeoLocation coordinates = new CPOGeoLocation();
//		cpoLocationModel.setCoordinates(coordinates);
//		assertEquals(coordinates, cpoLocationModel.getCoordinates());
//	}
//
//	@Test
//	public void testSetAndGetRelatedLocations() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		List<CPOAdditionalGeoLocation> relatedLocations = new ArrayList<>();
//		CPOAdditionalGeoLocation CPOAdditionalGeoLocation = new CPOAdditionalGeoLocation();
//		CPOAdditionalGeoLocation.setLatitude(" 40.7128");
//		CPOAdditionalGeoLocation.setLongitude("-74.0060");
//		relatedLocations.add(CPOAdditionalGeoLocation);
//		cpoLocationModel.setRelated_locations(relatedLocations);
//		assertEquals(relatedLocations, cpoLocationModel.getRelated_locations());
//	}
//
//	@Test
//	public void testSetAndGetParkingType() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String parkingType = "Outdoor";
//		cpoLocationModel.setParking_type(parkingType);
//		assertEquals(parkingType, cpoLocationModel.getParking_type());
//	}
//
//	@Test
//	public void testSetAndGetDirections() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		List<CPODisplayText> directions = new ArrayList<>();
//		CPODisplayText CPODisplayText = new CPODisplayText();
//		CPODisplayText.setLanguage("US");
//		directions.add(CPODisplayText);
//		cpoLocationModel.setDirections(directions);
//		assertEquals(directions, cpoLocationModel.getDirections());
//	}
//
//	@Test
//	public void testSetAndGetOperator() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		CPOBusinessDetails operator = new CPOBusinessDetails();
//		operator.setName("Operator Co.");
//		operator.setWebsite("Operator@gmail.com");
//		cpoLocationModel.setOperator(operator);
//		assertEquals(operator, cpoLocationModel.getOperator());
//	}
//
//	@Test
//	public void testSetAndGetFacilities() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String[] facilities = { "Charging", "Wi-Fi" };
//		cpoLocationModel.setFacilities(facilities);
//		assertEquals(facilities, cpoLocationModel.getFacilities());
//	}
//
//	@Test
//	public void testSetAndGetTimeZone() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		String timeZone = "UTC";
//		cpoLocationModel.setTime_zone(timeZone);
//		assertEquals(timeZone, cpoLocationModel.getTime_zone());
//	}
//
//	@Test
//	public void testSetAndGetOpeningTimes() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		CPOHours openingTimes = new CPOHours();
//		openingTimes.setExceptional_openings(null);
//		openingTimes.setRegular_hours(null);
//		cpoLocationModel.setOpening_times(openingTimes);
//		assertEquals(openingTimes, cpoLocationModel.getOpening_times());
//	}
//
//	@Test
//	public void testSetAndGetChargingWhenClosed() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		boolean chargingWhenClosed = true;
//		cpoLocationModel.setCharging_when_closed(chargingWhenClosed);
//		assertTrue(cpoLocationModel.isCharging_when_closed());
//	}
//
//	@Test
//	public void testSetAndGetEnergyMix() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		CPOEnergyMix energyMix = new CPOEnergyMix();
//		energyMix.setEnergy_product_name("Solar");
//		energyMix.setSupplier_name("EVG");
//		cpoLocationModel.setEnergy_mix(energyMix);
//		assertEquals(energyMix, cpoLocationModel.getEnergy_mix());
//	}
//
//	@Test
//	public void testSetAndGetOpen24x7() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		boolean open24x7 = true;
//		cpoLocationModel.setOpen24x7(open24x7);
//		assertTrue(cpoLocationModel.isOpen24x7());
//	}
//
//	@Test
//	public void testSetAndGetLastUpdated() {
//		CPOLocationModel cpoLocationModel = new CPOLocationModel();
//		Instant lastUpdated = Instant.now();
//		cpoLocationModel.setLast_updated(lastUpdated);
//		assertEquals(lastUpdated, cpoLocationModel.getLast_updated());
//	}
//
//	@Test
//	public void testToString() {
//		CPOLocationModel locationModel = new CPOLocationModel();
//		locationModel.setU_id("u12345");
//		locationModel.setId("id12345");
//		locationModel.setUid("uid12345");
//		locationModel.setType("public");
//		locationModel.setCountry_code("US");
//		locationModel.setParty_id("party_123");
//		locationModel.setCpo_country_code("US");
//		locationModel.setCpo_party_id("party_123");
//		locationModel.setPublish(true);
//		locationModel.setName("LocationName");
//		locationModel.setAddress("123 Main St");
//		locationModel.setCity("CityName");
//		locationModel.setState("StateName");
//		locationModel.setProvince("ProvinceName");
//		locationModel.setPostal_code("12345");
//		locationModel.setCountry("CountryName");
//		CPOGeoLocation coordinates = new CPOGeoLocation();
//		locationModel.setCoordinates(coordinates);
//
//		CPOBusinessDetails CPOBusinessDetails = new CPOBusinessDetails();
//		CPOBusinessDetails.setName("EVG");
//		locationModel.setOwner(CPOBusinessDetails);
//
//		List<CPOAdditionalGeoLocation> relatedLocations = new ArrayList<>();
//		CPOAdditionalGeoLocation CPOAdditionalGeoLocation = new CPOAdditionalGeoLocation();
//		CPOAdditionalGeoLocation.setLatitude(" 40.7128");
//		CPOAdditionalGeoLocation.setLongitude("-74.0060");
//		relatedLocations.add(CPOAdditionalGeoLocation);
//
//		locationModel.setRelated_locations(relatedLocations);
//		locationModel.setFacilities(new String[] { "facility1", "facility2" });
//		locationModel.setTime_zone("UTC+0");
//		CPOHours openingTimes = new CPOHours();
//		openingTimes.setExceptional_openings(null);
//		openingTimes.setRegular_hours(null);
//
//		locationModel.setOpening_times(openingTimes);
//		locationModel.setCharging_when_closed(false);
//		List<CPOImage> CPOImages = new ArrayList<CPOImage>();
//		locationModel.setImages(CPOImages);
//
//		CPOEnergyMix energyMix = new CPOEnergyMix();
//		energyMix.setEnergy_product_name("Solar");
//		energyMix.setSupplier_name("wind");
//
//		locationModel.setEnergy_mix(energyMix);
//		locationModel.setOpen24x7(true);
//		locationModel.setLast_updated(Instant.now());
//
//		String expectedString = "CPOLocationModel [u_id=u12345, id=id12345, uid=uid12345, type=public, "
//				+ "country_code=US, party_id=" + locationModel.getParty_id()
//				+ ", cpo_country_code=US, cpo_party_id=party_123, "
//				+ "publish=true, name=LocationName, address=123 Main St, city=CityName, state=StateName, province="
//				+ locationModel.getProvince() + ", postal_code=12345, country=CountryName, coordinates="
//				+ locationModel.getCoordinates() + ", " + "related_locations=" + locationModel.getRelated_locations()
//				+ ", publish_allowed_to=[], parking_type=null, directions=[], operator=null, suboperator=null, owner="
//				+ locationModel.getOwner() + ", facilities=[facility1, facility2], time_zone=UTC+0, " + "opening_times="
//				+ locationModel.getOpening_times() + ", charging_when_closed=false, images=" + locationModel.getImages()
//				+ ", " + "energy_mix=" + locationModel.getEnergy_mix() + ", open24x7=true, last_updated="
//				+ locationModel.getLast_updated() + "]";
//
////		String expectedStrings = "publish_allowed_to=[], parking_type=null, directions=[], operator=null, suboperator=null, owner=null, facilities=[facility1, facility2], time_zone=UTC+0, opening_times=Hours [twentyfourseven=false, regular_hours=null, exceptional_openings=null, exceptional_closings=null], charging_when_closed=false, images=[], energy_mix=com.evgateway.cpohubserver.form.CPOEnergyMix@2892dae4, open24x7=true, last_updated=2024-12-26T10:03:22.641071800Z]";
//		assertEquals(expectedString, locationModel.toString());
//	}
//
//}
