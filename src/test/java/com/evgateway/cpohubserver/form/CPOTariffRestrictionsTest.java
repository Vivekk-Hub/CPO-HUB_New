package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class CPOTariffRestrictionsTest {

    @Test
    void testGetAndSetStartTime() {
        CPOTariffRestrictions restrictions = new CPOTariffRestrictions();
        restrictions.setStart_time("08:00");

        assertEquals("08:00", restrictions.getStart_time(), "The start_time value should be correctly set and retrieved.");
    }

    @Test
    void testGetAndSetEndTime() {
        CPOTariffRestrictions restrictions = new CPOTariffRestrictions();
        restrictions.setEnd_time("18:00");

        assertEquals("18:00", restrictions.getEnd_time(), "The end_time value should be correctly set and retrieved.");
    }

    @Test
    void testGetAndSetStartDate() {
        CPOTariffRestrictions restrictions = new CPOTariffRestrictions();
        restrictions.setStart_date("2024-12-01");

        assertEquals("2024-12-01", restrictions.getStart_date(), "The start_date value should be correctly set and retrieved.");
    }

    @Test
    void testGetAndSetEndDate() {
        CPOTariffRestrictions restrictions = new CPOTariffRestrictions();
        restrictions.setEnd_date("2024-12-31");

        assertEquals("2024-12-31", restrictions.getEnd_date(), "The end_date value should be correctly set and retrieved.");
    }

    @Test
    void testGetAndSetMinKwh() {
        CPOTariffRestrictions restrictions = new CPOTariffRestrictions();
        restrictions.setMin_kwh("10");

        assertEquals("10", restrictions.getMin_kwh(), "The min_kwh value should be correctly set and retrieved.");
    }

    @Test
    void testGetAndSetMaxKwh() {
        CPOTariffRestrictions restrictions = new CPOTariffRestrictions();
        restrictions.setMax_kwh("50");

        assertEquals("50", restrictions.getMax_kwh(), "The max_kwh value should be correctly set and retrieved.");
    }

    @Test
    void testGetAndSetMinPower() {
        CPOTariffRestrictions restrictions = new CPOTariffRestrictions();
        restrictions.setMin_power("11");

        assertEquals("11", restrictions.getMin_power(), "The min_power value should be correctly set and retrieved.");
    }

    @Test
    void testGetAndSetMaxPower() {
        CPOTariffRestrictions restrictions = new CPOTariffRestrictions();
        restrictions.setMax_power("22");

        assertEquals("22", restrictions.getMax_power(), "The max_power value should be correctly set and retrieved.");
    }

    @Test
    void testGetAndSetMinDuration() {
        CPOTariffRestrictions restrictions = new CPOTariffRestrictions();
        restrictions.setMin_duration("30");

        assertEquals("30", restrictions.getMin_duration(), "The min_duration value should be correctly set and retrieved.");
    }

    @Test
    void testGetAndSetMaxDuration() {
        CPOTariffRestrictions restrictions = new CPOTariffRestrictions();
        restrictions.setMax_duration("120");

        assertEquals("120", restrictions.getMax_duration(), "The max_duration value should be correctly set and retrieved.");
    }

    @Test
    void testGetAndSetDayOfWeek() {
        CPOTariffRestrictions restrictions = new CPOTariffRestrictions();
        restrictions.setDay_of_week(Arrays.asList("Monday", "Wednesday"));

        assertEquals(Arrays.asList("Monday", "Wednesday"), restrictions.getDay_of_week(), "The day_of_week list should be correctly set and retrieved.");
    }

    @Test
    void testGetAndSetReservation() {
        CPOTariffRestrictions restrictions = new CPOTariffRestrictions();
        restrictions.setReservation("Yes");

        assertEquals("Yes", restrictions.getReservation(), "The reservation value should be correctly set and retrieved.");
    }

    @Test
    void testDefaultValues() {
        CPOTariffRestrictions restrictions = new CPOTariffRestrictions();

        assertNull(restrictions.getStart_time(), "Default start_time should be null");
        assertNull(restrictions.getEnd_time(), "Default end_time should be null");
        assertNull(restrictions.getStart_date(), "Default start_date should be null");
        assertNull(restrictions.getEnd_date(), "Default end_date should be null");
        assertNull(restrictions.getMin_kwh(), "Default min_kwh should be null");
        assertNull(restrictions.getMax_kwh(), "Default max_kwh should be null");
        assertNull(restrictions.getMin_power(), "Default min_power should be null");
        assertNull(restrictions.getMax_power(), "Default max_power should be null");
        assertNull(restrictions.getMin_duration(), "Default min_duration should be null");
        assertNull(restrictions.getMax_duration(), "Default max_duration should be null");
        assertNull(restrictions.getDay_of_week(), "Default day_of_week should be null");
        assertNull(restrictions.getReservation(), "Default reservation should be null");
    }

   
}
