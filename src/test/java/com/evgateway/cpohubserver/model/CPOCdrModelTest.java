// package com.evgateway.cpohubserver.model;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.time.Instant;
// import java.util.Arrays;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import com.evgateway.cpohubserver.form.CPOCdrChargingPeriod;
// import com.evgateway.cpohubserver.form.CPOCdrPrice;
// import com.evgateway.cpohubserver.form.CPOCdrSignedData;
// import com.evgateway.cpohubserver.form.CPOCdrTariff;
// import com.evgateway.cpohubserver.form.CPOLocation;

// class CPOCdrModelTest {

// 	private CPOCdrModel cpoCdrModel;

// 	@BeforeEach
// 	void setUp() {
// 		// Create an instance of CPOCdrModel
// 		cpoCdrModel = new CPOCdrModel();

// 		// Initialize fields with test data
// 		cpoCdrModel.setUid("12345");
// 		cpoCdrModel.setCountry_code("US");
// 		cpoCdrModel.setParty_id("party1");
// 		cpoCdrModel.setCpo_country_code("US");
// 		cpoCdrModel.setCpo_party_id("cpo_party1");
// 		cpoCdrModel.setEmsp_country_code("US");
// 		cpoCdrModel.setEmsp_party_id("emsp_party1");
// 		cpoCdrModel.setId("abc123");
// 		cpoCdrModel.setStart_date_time(Instant.now());
// 		cpoCdrModel.setEnd_date_time(Instant.now().plusSeconds(3600));
// 		cpoCdrModel.setSession_id("session123");
// 		cpoCdrModel.setAuth_method("RFID");
// 		cpoCdrModel.setAuth_id("auth123");
// 		cpoCdrModel.setAuthorization_reference("authRef123");
// 		cpoCdrModel.setMeter_id("meter123");
// 		cpoCdrModel.setCurrency("USD");

// 		// Mock or instantiate related objects like tariffs, charging periods, etc.
// 		cpoCdrModel.setTariffs(Arrays.asList(new CPOCdrTariff(), new CPOCdrTariff()));
// 		cpoCdrModel.setCharging_periods(Arrays.asList(new CPOCdrChargingPeriod(), new CPOCdrChargingPeriod()));
// 		cpoCdrModel.setSigned_data(new CPOCdrSignedData());
// 		cpoCdrModel.setTotal_cost(new CPOCdrPrice());
// 		cpoCdrModel.setTotal_energy(10.5);
// 		cpoCdrModel.setTotal_energy_cost(new CPOCdrPrice());
// 		cpoCdrModel.setTotal_time(60);
// 		cpoCdrModel.setTotal_time_cost(new CPOCdrPrice());
// 		cpoCdrModel.setTotal_parking_time(15);
// 		cpoCdrModel.setTotal_parking_cost(new CPOCdrPrice());
// 		cpoCdrModel.setTotal_reservation_cost(new CPOCdrPrice());
// 		cpoCdrModel.setRemark("Sample remark");
// 		cpoCdrModel.setInvoice_reference_id("invoice123");
// 		cpoCdrModel.setCredit(true);
// 		cpoCdrModel.setCredit_reference_id("creditRef123");
// 		cpoCdrModel.setLocation(new CPOLocation());
// 		cpoCdrModel.setLast_updated(Instant.now());
// 		cpoCdrModel.setVersion("2.2");
// 	}

// 	@Test
// 	void testUid() {
// 		assertEquals("12345", cpoCdrModel.getUid());
// 	}

// 	@Test
// 	void testCountryCode() {
// 		assertEquals("US", cpoCdrModel.getCountry_code());
// 	}

// 	@Test
// 	void testStartDateTime() {
// 		Instant startDateTime = Instant.now();
// 		cpoCdrModel.setStart_date_time(startDateTime);
// 		assertEquals(startDateTime, cpoCdrModel.getStart_date_time());
// 	}

// 	@Test
// 	void testTotalCost() {
// 		CPOCdrPrice totalCost = new CPOCdrPrice();
// 		cpoCdrModel.setTotal_cost(totalCost);
// 		assertEquals(totalCost, cpoCdrModel.getTotal_cost());
// 	}

// 	@Test
// 	void testChargingPeriods() {
// 		assertNotNull(cpoCdrModel.getCharging_periods());
// 		assertTrue(cpoCdrModel.getCharging_periods().size() > 0);
// 	}

// 	@Test
// 	void testTariffs() {
// 		assertNotNull(cpoCdrModel.getTariffs());
// 		assertTrue(cpoCdrModel.getTariffs().size() > 0);
// 	}

// 	@Test
// 	void testLocation() {
// 		CPOLocation location = new CPOLocation();
// 		cpoCdrModel.setLocation(location);
// 		assertEquals(location, cpoCdrModel.getLocation());
// 	}

// 	@Test
// 	void testCreditFlag() {
// 		cpoCdrModel.setCredit(false);
// 		assertFalse(cpoCdrModel.isCredit());
// 	}

// 	@Test
// 	void testVersion() {
// 		assertEquals("2.2", cpoCdrModel.getVersion());
// 	}

// 	@Test
// 	void testToString() {

// 		String expectedString = "CPOCdrModel [uid=12345, country_code=US, party_id=party1, cpo_country_code=US, cpo_party_id=cpo_party1, emsp_country_code=US, emsp_party_id=emsp_party1, id=abc123, start_date_time="
// 				+ cpoCdrModel.getStart_date_time() + ", end_date_time=" + cpoCdrModel.getEnd_date_time()
// 				+ ", session_id=session123, cdr_token=null, auth_method=RFID, auth_id=auth123, authorization_reference=authRef123, cdr_location=null, meter_id=meter123, currency=USD, tariffs=[com.evgateway.cpohubserver.form.CPOCdrTariff@78ffe6dc, com.evgateway.cpohubserver.form.CPOCdrTariff@8317c52], charging_periods=[ChargingPeriod [start_date_time=null, tariff_id=null, dimensions=null, cdr=null], ChargingPeriod [start_date_time=null, tariff_id=null, dimensions=null, cdr=null]], signed_data=SignedData [encoding_method=null, encoding_method_version=0, public_key=null, signed_values=null, url=null], total_cost=Price [excl_vat=0.0, incl_vat=0.0], total_fixed_cost=null, total_energy=10.5, total_energy_cost=Price [excl_vat=0.0, incl_vat=0.0], total_time=60.0, total_time_cost=Price [excl_vat=0.0, incl_vat=0.0], total_parking_time=15.0, total_parking_cost=Price [excl_vat=0.0, incl_vat=0.0], total_reservation_cost=Price [excl_vat=0.0, incl_vat=0.0], remark=Sample remark, invoice_reference_id=invoice123, credit=true, credit_reference_id=creditRef123, location=CPOLocation [id=null, type=null, country_code=null, party_id=null, publish=false, name=null, address=null, city=null, state=null, postal_code=null, country=null, parking_type=null, facilities=null, charging_when_closed=false, time_zone=null, open24x7=false, last_updated=null, publish_allowed_to=[], coordinates=null, related_locations=[], evses=[], directions=[], operator=null, suboperator=null, owner=null, opening_times=null,energy_mix=null], last_updated="
// 				+ cpoCdrModel.getLast_updated() + ", version=2.2]";
// 		assertEquals("US", cpoCdrModel.getCpo_country_code());
// 		assertEquals("authRef123", cpoCdrModel.getAuthorization_reference());
// 	}
// }
