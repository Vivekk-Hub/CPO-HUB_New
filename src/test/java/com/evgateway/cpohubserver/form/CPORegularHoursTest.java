package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CPORegularHoursTest {

    @Test
    public void testGetAndSetWeekday() {
        CPORegularHours regularHours = new CPORegularHours();
        regularHours.setWeekday(5);
        assertEquals(5, regularHours.getWeekday());
    }

    @Test
    public void testGetAndSetPeriodBegin() {
        CPORegularHours regularHours = new CPORegularHours();
        regularHours.setPeriod_begin("08:00");
        assertEquals("08:00", regularHours.getPeriod_begin());
    }

    @Test
    public void testGetAndSetPeriodEnd() {
        CPORegularHours regularHours = new CPORegularHours();
        regularHours.setPeriod_end("18:00");
        assertEquals("18:00", regularHours.getPeriod_end());
    }

    @Test
    public void testToString() {
        CPORegularHours regularHours = new CPORegularHours();
        regularHours.setWeekday(1);
        regularHours.setPeriod_begin("09:00");
        regularHours.setPeriod_end("17:00");

        String expected = "RegularHours [weekday=1, period_begin=09:00, period_end=17:00]";
        assertEquals(expected, regularHours.toString());
    }
}
