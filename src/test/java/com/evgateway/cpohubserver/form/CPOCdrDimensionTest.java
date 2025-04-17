package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CPOCdrDimensionTest {

	@Test
	void testGettersAndSetters() {
		// Create an instance of CPOCdrDimension
		CPOCdrDimension cdrDimension = new CPOCdrDimension();

		// Test type
		cdrDimension.setType("Energy");
		assertEquals("Energy", cdrDimension.getType(), "Type should match the value set.");

		// Test volume
		cdrDimension.setVolume(123.45);
		assertEquals(123.45, cdrDimension.getVolume(), 0.001, "Volume should match the value set.");
	}

	@Test
	void testToString() {
		// Create an instance of CPOCdrDimension
		CPOCdrDimension cdrDimension = new CPOCdrDimension();
		cdrDimension.setType("Energy");
		cdrDimension.setVolume(123.45);

		// Test the toString method
		String expectedString = "CdrDimension [type=Energy, volume=123.45]";
		assertEquals(expectedString, cdrDimension.toString(),
				"toString method should return the correct string representation.");
	}

	@Test
	void testNullValues() {
		// Create an instance of CPOCdrDimension
		CPOCdrDimension cdrDimension = new CPOCdrDimension();

		// Test default null values
		assertNull(cdrDimension.getType(), "Type should be null by default.");
		assertEquals(0.0, cdrDimension.getVolume(), 0.001, "Volume should be 0.0 by default.");
	}
}
