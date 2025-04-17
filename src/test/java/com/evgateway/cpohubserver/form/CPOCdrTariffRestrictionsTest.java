package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class CPOCdrTariffRestrictionsTest {

	@Test
	void testGettersAndSetters() {
		// Create an instance of CPOCdrTariffRestrictions
		CPOCdrTariffRestrictions restrictions = new CPOCdrTariffRestrictions();

		// Set values for the fields
		restrictions.setStart_time("08:00");
		restrictions.setEnd_time("18:00");
		restrictions.setStart_date("2024-01-01");
		restrictions.setEnd_date("2024-12-31");
		restrictions.setMin_kwh("1.5");
		restrictions.setMax_kwh("10.0");
		restrictions.setMin_power("3.0");
		restrictions.setMax_power("50.0");
		restrictions.setMin_duration("30");
		restrictions.setMax_duration("240");
		restrictions.setDay_of_week(Arrays.asList("Monday", "Tuesday", "Wednesday"));
		restrictions.setReservation("Yes");

		// Test each field with corresponding getters
		assertEquals("08:00", restrictions.getStart_time(), "Start time should match the value set.");
		assertEquals("18:00", restrictions.getEnd_time(), "End time should match the value set.");
		assertEquals("2024-01-01", restrictions.getStart_date(), "Start date should match the value set.");
		assertEquals("2024-12-31", restrictions.getEnd_date(), "End date should match the value set.");
		assertEquals("1.5", restrictions.getMin_kwh(), "Min kWh should match the value set.");
		assertEquals("10.0", restrictions.getMax_kwh(), "Max kWh should match the value set.");
		assertEquals("3.0", restrictions.getMin_power(), "Min power should match the value set.");
		assertEquals("50.0", restrictions.getMax_power(), "Max power should match the value set.");
		assertEquals("30", restrictions.getMin_duration(), "Min duration should match the value set.");
		assertEquals("240", restrictions.getMax_duration(), "Max duration should match the value set.");
		assertEquals(Arrays.asList("Monday", "Tuesday", "Wednesday"), restrictions.getDay_of_week(),
				"Day of week should match the value set.");
		assertEquals("Yes", restrictions.getReservation(), "Reservation should match the value set.");
	}

	@Test
	void testNullValues() {
		// Create an instance of CPOCdrTariffRestrictions
		CPOCdrTariffRestrictions restrictions = new CPOCdrTariffRestrictions();

		// Test default values (should be null or empty depending on the type)
		assertNull(restrictions.getStart_time(), "Start time should be null by default.");
		assertNull(restrictions.getEnd_time(), "End time should be null by default.");
		assertNull(restrictions.getStart_date(), "Start date should be null by default.");
		assertNull(restrictions.getEnd_date(), "End date should be null by default.");
		assertNull(restrictions.getMin_kwh(), "Min kWh should be null by default.");
		assertNull(restrictions.getMax_kwh(), "Max kWh should be null by default.");
		assertNull(restrictions.getMin_power(), "Min power should be null by default.");
		assertNull(restrictions.getMax_power(), "Max power should be null by default.");
		assertNull(restrictions.getMin_duration(), "Min duration should be null by default.");
		assertNull(restrictions.getMax_duration(), "Max duration should be null by default.");
		assertNull(restrictions.getDay_of_week(), "Day of week should be null by default.");
		assertNull(restrictions.getReservation(), "Reservation should be null by default.");
	}

//	@Test
//	void testToString() {
//		// Create an instance of CPOCdrTariffRestrictions
//		CPOCdrTariffRestrictions restrictions = new CPOCdrTariffRestrictions();
//
//		// Set values for the fields
//		restrictions.setStart_time("08:00");
//		restrictions.setEnd_time("18:00");
//		restrictions.setStart_date("2024-01-01");
//		restrictions.setEnd_date("2024-12-31");
//		restrictions.setMin_kwh("1.5");
//		restrictions.setMax_kwh("10.0");
//		restrictions.setMin_power("3.0");
//		restrictions.setMax_power("50.0");
//		restrictions.setMin_duration("30");
//		restrictions.setMax_duration("240");
//		restrictions.setDay_of_week(Arrays.asList("Monday", "Tuesday", "Wednesday"));
//		restrictions.setReservation("Yes");
//
//		// Test the toString method
//		String expectedString = "CPOCdrTariffRestrictions [start_time=08:00, end_time=18:00, start_date=2024-01-01, end_date=2024-12-31, min_kwh=1.5, max_kwh=10.0, min_power=3.0, max_power=50.0, min_duration=30, max_duration=240, day_of_week=[Monday, Tuesday, Wednesday], reservation=Yes]";
//		assertEquals(expectedString, restrictions.toString(), "toString should return the expected string.");
//	}
}
