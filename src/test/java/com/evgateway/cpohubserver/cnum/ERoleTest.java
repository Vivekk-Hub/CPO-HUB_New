package com.evgateway.cpohubserver.cnum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ERoleTest {

	@Test
	void testERoleValues() {
		// Check the enum values
		ERole[] expectedRoles = { ERole.ADMIN, ERole.CPOADMIN, ERole.EMSPADMIN };
		ERole[] actualRoles = ERole.values();

		// Assert that the expected and actual roles match
		assertArrayEquals(expectedRoles, actualRoles, "The ERole values should match the defined enum constants.");
	}

	@Test
	void testERoleValueOf() {
		// Check that each role name can be correctly accessed using valueOf
		assertEquals(ERole.ADMIN, ERole.valueOf("ADMIN"));
		assertEquals(ERole.CPOADMIN, ERole.valueOf("CPOADMIN"));
		assertEquals(ERole.EMSPADMIN, ERole.valueOf("EMSPADMIN"));

		// Check that valueOf throws an exception for an invalid name
		assertThrows(IllegalArgumentException.class, () -> ERole.valueOf("INVALID"));
	}
}
