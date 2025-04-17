
package com.evgateway.cpohubserver.cnum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModulesTest {

	@Test
	void testModulesValues() {
		// Check the enum values
		Modules[] expectedModules = { Modules.CDRs, Modules.Commands, Modules.Locations, Modules.Sessions,
				Modules.Tariffs, Modules.Tokens, Modules.Push, Modules.Pull, Modules.credentials };

		Modules[] actualModules = Modules.values();

		// Assert that the expected and actual modules match
		assertArrayEquals(expectedModules, actualModules,
				"The Modules values should match the defined enum constants.");
	}

	@Test
	void testModulesValueOf() {
		// Check that each module name can be correctly accessed using valueOf
		assertEquals(Modules.CDRs, Modules.valueOf("CDRs"));
		assertEquals(Modules.Commands, Modules.valueOf("Commands"));
		assertEquals(Modules.Locations, Modules.valueOf("Locations"));
		assertEquals(Modules.Sessions, Modules.valueOf("Sessions"));
		assertEquals(Modules.Tariffs, Modules.valueOf("Tariffs"));
		assertEquals(Modules.Tokens, Modules.valueOf("Tokens"));
		assertEquals(Modules.Push, Modules.valueOf("Push"));
		assertEquals(Modules.Pull, Modules.valueOf("Pull"));
		assertEquals(Modules.credentials, Modules.valueOf("credentials"));

		// Check that valueOf throws an exception for an invalid name
		assertThrows(IllegalArgumentException.class, () -> Modules.valueOf("INVALID"));
	}
}
