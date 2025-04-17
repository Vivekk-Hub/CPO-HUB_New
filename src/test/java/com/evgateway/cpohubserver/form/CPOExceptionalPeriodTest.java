package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.Date;

class CPOExceptionalPeriodTest {

	@Test
	public void testGetPeriod_begin() {
		CPOExceptionalPeriod exceptionalPeriod = new CPOExceptionalPeriod();
		Date beginDate = new Date();
		exceptionalPeriod.setPeriod_begin(beginDate);
		assertEquals(beginDate, exceptionalPeriod.getPeriod_begin());
	}

	@Test
	public void testSetPeriod_begin() {
		CPOExceptionalPeriod exceptionalPeriod = new CPOExceptionalPeriod();
		Date beginDate = new Date();
		exceptionalPeriod.setPeriod_begin(beginDate);
		assertEquals(beginDate, exceptionalPeriod.getPeriod_begin());
	}

	@Test
	public void testGetPeriod_end() {
		CPOExceptionalPeriod exceptionalPeriod = new CPOExceptionalPeriod();
		Date endDate = new Date();
		exceptionalPeriod.setPeriod_end(endDate);
		assertEquals(endDate, exceptionalPeriod.getPeriod_end());
	}

	@Test
	public void testSetPeriod_end() {
		CPOExceptionalPeriod exceptionalPeriod = new CPOExceptionalPeriod();
		Date endDate = new Date();
		exceptionalPeriod.setPeriod_end(endDate);
		assertEquals(endDate, exceptionalPeriod.getPeriod_end());
	}

	@Test
	public void testToString() {
		CPOExceptionalPeriod exceptionalPeriod = new CPOExceptionalPeriod();
		Date beginDate = new Date();
		Date endDate = new Date();
		exceptionalPeriod.setPeriod_begin(beginDate);
		exceptionalPeriod.setPeriod_end(endDate);

		String expectedString = "ExceptionalPeriod [period_begin=" + beginDate + ", period_end=" + endDate + "]";
		assertEquals(expectedString, exceptionalPeriod.toString());
	}
}
