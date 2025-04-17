package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CPOCdrSignedDataTest {

	@Test
	void testGettersAndSetters() {
		// Create an instance of CPOCdrSignedData
		CPOCdrSignedData signedData = new CPOCdrSignedData();

		// Test encoding_method
		signedData.setEncoding_method("SHA-256");
		assertEquals("SHA-256", signedData.getEncoding_method(), "Encoding method should match the value set.");

		// Test encoding_method_version
		signedData.setEncoding_method_version(1);
		assertEquals(1, signedData.getEncoding_method_version(), "Encoding method version should match the value set.");

		// Test public_key
		signedData.setPublic_key("public_key_value");
		assertEquals("public_key_value", signedData.getPublic_key(), "Public key should match the value set.");

		// Test signed_values (CPOCdrSignedValue)
		CPOCdrSignedValue signedValues = new CPOCdrSignedValue();
		signedValues.setNature("NatureValue");
		signedValues.setPlain_data("PlainData");
		signedData.setSigned_values(signedValues);
		assertEquals(signedValues, signedData.getSigned_values(),
				"Signed values should match the CPOCdrSignedValue object set.");

		// Test url
		signedData.setUrl("https://www.example.com");
		assertEquals("https://www.example.com", signedData.getUrl(), "URL should match the value set.");
	}

	@Test
	void testToString() {
		// Create an instance of CPOCdrSignedData
		CPOCdrSignedData signedData = new CPOCdrSignedData();
		signedData.setEncoding_method("SHA-256");
		signedData.setEncoding_method_version(1);
		signedData.setPublic_key("public_key_value");

		// Test the toString method
		CPOCdrSignedValue signedValues = new CPOCdrSignedValue();
		signedValues.setNature("NatureValue");
		signedValues.setPlain_data("PlainData");
		signedData.setSigned_values(signedValues);
		signedData.setUrl("https://www.example.com");

		String expectedString = "SignedData [encoding_method=SHA-256, encoding_method_version=1, public_key=public_key_value, signed_values="
				+ signedValues + ", url=https://www.example.com]";
		assertEquals(expectedString, signedData.toString(),
				"toString method should return the correct string representation.");
	}

	@Test
	void testNullValues() {
		// Create an instance of CPOCdrSignedData
		CPOCdrSignedData signedData = new CPOCdrSignedData();

		// Test default values
		assertNull(signedData.getEncoding_method(), "Encoding method should be null by default.");
		assertEquals(0, signedData.getEncoding_method_version(), "Encoding method version should be 0 by default.");
		assertNull(signedData.getPublic_key(), "Public key should be null by default.");
		assertNull(signedData.getSigned_values(), "Signed values should be null by default.");
		assertNull(signedData.getUrl(), "URL should be null by default.");
	}
}
