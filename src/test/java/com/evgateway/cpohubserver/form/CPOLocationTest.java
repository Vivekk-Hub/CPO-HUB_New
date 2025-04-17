package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CPOLocationTest {

	@Test
	public void testGetAndSetId() {
		CPOLocation location = new CPOLocation();
		location.setId("loc123");
		assertEquals("loc123", location.getId());
	}

	@Test
	public void testGetAndSetParking_type() {
		CPOLocation location = new CPOLocation();
		location.setParking_type("Chargeable");
		assertEquals("Chargeable", location.getParking_type());
	}

	@Test
	public void Charging_when_closed() {
		CPOLocation location = new CPOLocation();
		location.setCharging_when_closed(false);
		assertEquals(false, location.isCharging_when_closed());
	}

	@Test
	public void testGetAndSetTime_zone() {
		CPOLocation location = new CPOLocation();
		location.setTime_zone("IND");
		assertEquals("IND", location.getTime_zone());
	}

	@Test
	public void testGetAndSetOpen24x7() {
		CPOLocation location = new CPOLocation();
		location.setOpen24x7(false);
		assertEquals(false, location.isOpen24x7());
	}

//	@Test
//	public void testGetAndSet() {
//		CPOLocation location = new CPOLocation();
//		location.setParking_type("Chargeable");
//		assertEquals("Chargeable", location.getParking_type());
//	}

	@Test
	public void testGetAndSetRelated_locations() {
		CPOLocation location = new CPOLocation();
		List<CPOAdditionalGeoLocation> Related_locations = new ArrayList<CPOAdditionalGeoLocation>();
		CPOAdditionalGeoLocation CPOAdditionalGeoLocation = new CPOAdditionalGeoLocation();
		CPOAdditionalGeoLocation.setLatitude("540022.25");
		Related_locations.add(CPOAdditionalGeoLocation);
		location.setRelated_locations(Related_locations);
		assertEquals("540022.25", location.getRelated_locations().get(0).getLatitude());
	}

	@Test
	public void testGetAndSetCPOEvse() {
		CPOLocation location = new CPOLocation();
		List<CPOEvse> Related_locations = new ArrayList<CPOEvse>();
		CPOEvse CPOEvse = new CPOEvse();
		CPOEvse.setU_id("54321");
		Related_locations.add(CPOEvse);
		location.setEvses(Related_locations);
		assertEquals("54321", location.getEvses().get(0).getU_id());
	}

	@Test
	public void testGetAndSetDirections() {
		CPOLocation location = new CPOLocation();

		List<CPODisplayText> lists = new ArrayList<CPODisplayText>();
		CPODisplayText CPODisplayText = new CPODisplayText();
		CPODisplayText.setLanguage("EN");
		CPODisplayText.setText("English");
		lists.add(CPODisplayText);
		location.setDirections(lists);

		assertEquals("EN", location.getDirections().get(0).getLanguage());
	}

	@Test
	public void testGetAndSetEnergy_mix() {
		CPOLocation location = new CPOLocation();
		CPOEnergyMix CPOEnergyMix = new CPOEnergyMix();
		CPOEnergyMix.setEnergy_product_name("EV");
		location.setEnergy_mix(CPOEnergyMix);
		assertEquals("EV", location.getEnergy_mix().getEnergy_product_name());
	}

	@Test
	public void testGetAndSetOperator() {
		CPOLocation location = new CPOLocation();
		CPOBusinessDetails CPOBusinessDetails = new CPOBusinessDetails();
		CPOBusinessDetails.setName("EvGateway");
		location.setOperator(CPOBusinessDetails);
		assertEquals("EvGateway", location.getOperator().getName());
	}

	@Test
	public void testGetAndSetSuboperator() {
		CPOLocation location = new CPOLocation();
		CPOBusinessDetails CPOBusinessDetails = new CPOBusinessDetails();
		CPOBusinessDetails.setName("EvGateway");
		location.setSuboperator(CPOBusinessDetails);
		assertEquals("EvGateway", location.getSuboperator().getName());
	}

	@Test
	public void testGetAndSetOwner() {
		CPOLocation location = new CPOLocation();
		CPOBusinessDetails CPOBusinessDetails = new CPOBusinessDetails();
		CPOBusinessDetails.setName("EvGateway");
		location.setOwner(CPOBusinessDetails);
		assertEquals("EvGateway", location.getOwner().getName());
	}

	@Test
	public void testGetAndSetOpening_times() {
		CPOLocation location = new CPOLocation();
		CPOHours CPOHours = new CPOHours();
		CPOHours.setTwentyfourseven(false);
		location.setOpening_times(CPOHours);
		assertEquals(false, location.getOpening_times().isTwentyfourseven());
	}

	@Test
	public void testGetAndSetType() {
		CPOLocation location = new CPOLocation();
		location.setType("public");
		assertEquals("public", location.getType());
	}

	@Test
	public void testGetAndSetCountryCode() {
		CPOLocation location = new CPOLocation();
		location.setCountry_code("US");
		assertEquals("US", location.getCountry_code());
	}

	@Test
	public void testGetAndSetPartyId() {
		CPOLocation location = new CPOLocation();
		location.setParty_id("party001");
		assertEquals("party001", location.getParty_id());
	}

	@Test
	public void testGetAndSetPublish() {
		CPOLocation location = new CPOLocation();
		location.setPublish(true);
		assertEquals(true, location.isPublish());
	}

	@Test
	public void testGetAndSetName() {
		CPOLocation location = new CPOLocation();
		location.setName("Location A");
		assertEquals("Location A", location.getName());
	}

	@Test
	public void testGetAndSetAddress() {
		CPOLocation location = new CPOLocation();
		location.setAddress("123 Main St");
		assertEquals("123 Main St", location.getAddress());
	}

	@Test
	public void testGetAndSetCity() {
		CPOLocation location = new CPOLocation();
		location.setCity("Springfield");
		assertEquals("Springfield", location.getCity());
	}

	@Test
	public void testGetAndSetState() {
		CPOLocation location = new CPOLocation();
		location.setState("IL");
		assertEquals("IL", location.getState());
	}

	@Test
	public void testGetAndSetPostalCode() {
		CPOLocation location = new CPOLocation();
		location.setPostal_code("62704");
		assertEquals("62704", location.getPostal_code());
	}

	@Test
	public void testGetAndSetCountry() {
		CPOLocation location = new CPOLocation();
		location.setCountry("USA");
		assertEquals("USA", location.getCountry());
	}

	@Test
	public void testToString() {
		CPOLocation location = new CPOLocation();
		location.setId("loc123");
		location.setType("public");
		location.setName("Location A");
		String expected = "CPOLocation [id=loc123, type=public, country_code=null, party_id=null, publish=false, name=Location A, address=null, city=null, state=null, postal_code=null, country=null, parking_type=null, facilities=null, charging_when_closed=false, time_zone=null, open24x7=false, last_updated=null, publish_allowed_to=[], coordinates=null, related_locations=[], evses=[], directions=[], operator=null, suboperator=null, owner=null, opening_times=null,energy_mix=null]";
		assertEquals(expected, location.toString());
	}
}
