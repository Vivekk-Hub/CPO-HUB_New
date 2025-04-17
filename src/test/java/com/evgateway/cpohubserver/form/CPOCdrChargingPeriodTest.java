package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CPOCdrChargingPeriodTest {

	@Test
	public void testCPOCdrChargingPeriodSettersAndGetters() {
		// Arrange
		CPOCdrChargingPeriod chargingPeriod = new CPOCdrChargingPeriod();

		Date startDateTime = new Date();
		String tariffId = "TARIFF001";
		List<CPOCdrDimension> dimensions = new ArrayList<>();
		CPOCdr cdr = new CPOCdr();

		// Act
		chargingPeriod.setStart_date_time(startDateTime);
		chargingPeriod.setTariff_id(tariffId);
		chargingPeriod.setDimensions(dimensions);
		chargingPeriod.setCdr(cdr);

		// Assert
		assertEquals(startDateTime, chargingPeriod.getStart_date_time());
		assertEquals(tariffId, chargingPeriod.getTariff_id());
		assertEquals(dimensions, chargingPeriod.getDimensions());
		assertEquals(cdr, chargingPeriod.getCdr());
	}

	@Test
	public void testCPOCdrChargingPeriodToString() {
		// Arrange
		CPOCdrChargingPeriod chargingPeriod = new CPOCdrChargingPeriod();

		Date startDateTime = new Date();
		String tariffId = "TARIFF001";
		List<CPOCdrDimension> dimensions = new ArrayList<>();
		CPOCdr cdr = new CPOCdr();

		chargingPeriod.setStart_date_time(startDateTime);
		chargingPeriod.setTariff_id(tariffId);
		chargingPeriod.setDimensions(dimensions);
		chargingPeriod.setCdr(cdr);

		// Act
		String result = chargingPeriod.toString();

		// Assert
		assertTrue(result.contains("start_date_time=" + startDateTime.toString()));
		assertTrue(result.contains("tariff_id=" + tariffId));
		assertTrue(result.contains("dimensions=" + dimensions.toString()));
		assertTrue(result.contains("cdr=" + cdr.toString()));
	}
}
