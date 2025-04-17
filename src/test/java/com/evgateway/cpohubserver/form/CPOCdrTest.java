package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class CPOCdrTest {

    @Test
    void testCPOCdrInstantiation() {
        // Create an instance of CPOCdr
        CPOCdr cdr = new CPOCdr();

        // Assert that the instance is not null
        assertNotNull(cdr, "CPOCdr instance should be successfully created.");
    }

    @Test
    void testSettersAndGetters() {
        // Create an instance of CPOCdr
        CPOCdr cdr = new CPOCdr();

        // Create test data
        Date startDate = new Date();
        Date endDate = new Date();
        List<CPOCdrTariff> tariffs = new ArrayList<>();
        List<CPOCdrChargingPeriod> chargingPeriods = new ArrayList<>();
        CPOCdrToken token = new CPOCdrToken();
        CPOCdrLocation location = new CPOCdrLocation();
        CPOCdrSignedData signedData = new CPOCdrSignedData();
        CPOCdrPrice totalCost = new CPOCdrPrice();
        CPOCdrPrice fixedCost = new CPOCdrPrice();
        CPOCdrPrice energyCost = new CPOCdrPrice();
        CPOCdrPrice timeCost = new CPOCdrPrice();
        CPOCdrPrice parkingCost = new CPOCdrPrice();
        CPOCdrPrice reservationCost = new CPOCdrPrice();

        // Set values
        cdr.setCountry_code("US");
        cdr.setParty_id("PARTY123");
        cdr.setId("CDR001");
        cdr.setStart_date_time(startDate);
        cdr.setEnd_date_time(endDate);
        cdr.setSession_id("SESSION123");
        cdr.setCdr_token(token);
        cdr.setAuth_method("RFID");
        cdr.setAuth_id("AUTH123");
        cdr.setAuthorization_reference("AUTHREF123");
        cdr.setCdr_location(location);
        cdr.setMeter_id("METER001");
        cdr.setCurrency("USD");
        cdr.setTariffs(tariffs);
        cdr.setCharging_periods(chargingPeriods);
        cdr.setSigned_data(signedData);
        cdr.setTotal_cost(totalCost);
        cdr.setTotal_fixed_cost(fixedCost);
        cdr.setTotal_energy(150.5);
        cdr.setTotal_energy_cost(energyCost);
        cdr.setTotal_time(3600);
        cdr.setTotal_time_cost(timeCost);
        cdr.setTotal_parking_time(600);
        cdr.setTotal_parking_cost(parkingCost);
        cdr.setTotal_reservation_cost(reservationCost);
        cdr.setRemark("Test Remark");
        cdr.setInvoice_reference_id("INV001");
        cdr.setCredit(true);
        cdr.setCredit_reference_id("CREDIT001");
        cdr.setCdr_location_details("Detailed Location");
        cdr.setLast_updated(new Date());

        // Assert values
        assertEquals("US", cdr.getCountry_code());
        assertEquals("PARTY123", cdr.getParty_id());
        assertEquals("CDR001", cdr.getId());
        assertEquals(startDate, cdr.getStart_date_time());
        assertEquals(endDate, cdr.getEnd_date_time());
        assertEquals("SESSION123", cdr.getSession_id());
        assertEquals(token, cdr.getCdr_token());
        assertEquals("RFID", cdr.getAuth_method());
        assertEquals("AUTH123", cdr.getAuth_id());
        assertEquals("AUTHREF123", cdr.getAuthorization_reference());
        assertEquals(location, cdr.getCdr_location());
        assertEquals("METER001", cdr.getMeter_id());
        assertEquals("USD", cdr.getCurrency());
        assertEquals(tariffs, cdr.getTariffs());
        assertEquals(chargingPeriods, cdr.getCharging_periods());
        assertEquals(signedData, cdr.getSigned_data());
        assertEquals(totalCost, cdr.getTotal_cost());
        assertEquals(fixedCost, cdr.getTotal_fixed_cost());
        assertEquals(150.5, cdr.getTotal_energy());
        assertEquals(energyCost, cdr.getTotal_energy_cost());
        assertEquals(3600, cdr.getTotal_time());
        assertEquals(timeCost, cdr.getTotal_time_cost());
        assertEquals(600, cdr.getTotal_parking_time());
        assertEquals(parkingCost, cdr.getTotal_parking_cost());
        assertEquals(reservationCost, cdr.getTotal_reservation_cost());
        assertEquals("Test Remark", cdr.getRemark());
        assertEquals("INV001", cdr.getInvoice_reference_id());
        assertTrue(cdr.isCredit());
        assertEquals("CREDIT001", cdr.getCredit_reference_id());
        assertEquals("Detailed Location", cdr.getCdr_location_details());
        assertNotNull(cdr.getLast_updated());
    }
}
