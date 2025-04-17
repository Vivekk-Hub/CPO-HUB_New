package com.evgateway.cpohubserver.cnum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

	@Test
	void testStatusValues() {
		// Check the enum values
		Status[] expectedStatuses = { Status.AVAILABLE, Status.BLOCKED, Status.CHARGING, Status.INOPERATIVE,
				Status.OUTOFORDER, Status.PLANNED, Status.REMOVED, Status.RESERVED, Status.UNKNOWN, Status.ONLINE,
				Status.OFFLINE };

		Status[] actualStatuses = Status.values();

		// Assert that the expected and actual statuses match
		assertArrayEquals(expectedStatuses, actualStatuses,
				"The Status values should match the defined enum constants.");
	}

	@Test
	void testStatusValueOf() {
		// Check that each status name can be correctly accessed using valueOf
		assertEquals(Status.AVAILABLE, Status.valueOf("AVAILABLE"));
		assertEquals(Status.BLOCKED, Status.valueOf("BLOCKED"));
		assertEquals(Status.CHARGING, Status.valueOf("CHARGING"));
		assertEquals(Status.INOPERATIVE, Status.valueOf("INOPERATIVE"));
		assertEquals(Status.OUTOFORDER, Status.valueOf("OUTOFORDER"));
		assertEquals(Status.PLANNED, Status.valueOf("PLANNED"));
		assertEquals(Status.REMOVED, Status.valueOf("REMOVED"));
		assertEquals(Status.RESERVED, Status.valueOf("RESERVED"));
		assertEquals(Status.UNKNOWN, Status.valueOf("UNKNOWN"));
		assertEquals(Status.ONLINE, Status.valueOf("ONLINE"));
		assertEquals(Status.OFFLINE, Status.valueOf("OFFLINE"));

		// Check that valueOf throws an exception for an invalid name
		assertThrows(IllegalArgumentException.class, () -> Status.valueOf("INVALID"));
	}
}
