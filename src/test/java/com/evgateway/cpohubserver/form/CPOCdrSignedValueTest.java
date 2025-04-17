package com.evgateway.cpohubserver.form;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class CPOCdrSignedValueTest {

	@Test
	void testGettersAndSetters() {
		// Create an instance of CPOCdrSignedValue
		CPOCdrSignedValue signedValue = new CPOCdrSignedValue();

		// Test nature
		signedValue.setNature("SHA-256");
		assertEquals("SHA-256", signedValue.getNature(), "Nature should match the value set.");

		// Test plain_data
		signedValue.setPlain_data("somePlainData");
		assertEquals("somePlainData", signedValue.getPlain_data(), "Plain data should match the value set.");

		// Test signed_data
		signedValue.setSigned_data("signedData123");
		assertEquals("signedData123", signedValue.getSigned_data(), "Signed data should match the value set.");
	}

	@Test
	void testToString() {
		// Create an instance of CPOCdrSignedValue
		CPOCdrSignedValue signedValue = new CPOCdrSignedValue();
		signedValue.setNature("SHA-256");
		signedValue.setPlain_data("somePlainData");
		signedValue.setSigned_data("signedData123");

		// Test the toString method
		String expectedString = "SignedValue [nature=SHA-256, plain_data=somePlainData, signed_data=signedData123]";
		assertEquals(expectedString, signedValue.toString(),
				"toString method should return the correct string representation.");
	}

	@Test
	void testNullValues() {
		// Create an instance of CPOCdrSignedValue
		CPOCdrSignedValue signedValue = new CPOCdrSignedValue();

		// Test default null values
		assertNull(signedValue.getNature(), "Nature should be null by default.");
		assertNull(signedValue.getPlain_data(), "Plain data should be null by default.");
		assertNull(signedValue.getSigned_data(), "Signed data should be null by default.");
	}
}
