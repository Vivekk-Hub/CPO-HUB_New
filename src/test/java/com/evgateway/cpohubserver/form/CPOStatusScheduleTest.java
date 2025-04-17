package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

class CPOStatusScheduleTest {

    @Test
    public void testGetAndSetPeriodBegin() {
        CPOStatusSchedule schedule = new CPOStatusSchedule();
        Date periodBegin = new Date();
        schedule.setPeriod_begin(periodBegin);
        assertEquals(periodBegin, schedule.getPeriod_begin());
    }

    @Test
    public void testGetAndSetPeriodEnd() {
        CPOStatusSchedule schedule = new CPOStatusSchedule();
        Date periodEnd = new Date();
        schedule.setPeriod_end(periodEnd);
        assertEquals(periodEnd, schedule.getPeriod_end());
    }

    @Test
    public void testGetAndSetStatus() {
        CPOStatusSchedule schedule = new CPOStatusSchedule();
        schedule.setStatus("Available");
        assertEquals("Available", schedule.getStatus());
    }

    @Test
    public void testInitialValuesAreNull() {
        CPOStatusSchedule schedule = new CPOStatusSchedule();
        assertNull(schedule.getPeriod_begin());
        assertNull(schedule.getPeriod_end());
        assertNull(schedule.getStatus());
    }
}
