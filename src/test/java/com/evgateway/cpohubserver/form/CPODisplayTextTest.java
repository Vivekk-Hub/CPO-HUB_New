package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CPODisplayTextTest {

	@Test
	void testGettersAndSetters() {
		// Create an instance of CPODisplayText
		CPODisplayText displayText = new CPODisplayText();

		// Set values for the fields
		displayText.setLanguage("English");
		displayText.setText("Welcome to the CPO system");

		// Test each field with corresponding getters
		assertEquals("English", displayText.getLanguage(), "Language should match the value set.");
		assertEquals("Welcome to the CPO system", displayText.getText(), "Text should match the value set.");
	}

	@Test
	void testNullValues() {
		// Create an instance of CPODisplayText
		CPODisplayText displayText = new CPODisplayText();

		// Test default values (should be null for objects)
		assertNull(displayText.getLanguage(), "Language should be null by default.");
		assertNull(displayText.getText(), "Text should be null by default.");
	}

}
