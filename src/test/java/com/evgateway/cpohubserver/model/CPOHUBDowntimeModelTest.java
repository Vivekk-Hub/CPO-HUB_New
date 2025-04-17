// package com.evgateway.cpohubserver.model;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNull;

// import java.time.Instant;

// import org.junit.jupiter.api.Test;

// class CPOHUBDowntimeModelTest {

// 	@Test
// 	void testSettersAndGetters() {
// 		// Create an instance of CPOHUBDowntimeModel
// 		CPOHUBDowntimeModel downtimeModel = new CPOHUBDowntimeModel();

// 		// Test initial values
// 		assertNull(downtimeModel.getId());
// 		assertNull(downtimeModel.getPartnerId());
// 		assertNull(downtimeModel.getStartTime());
// 		assertNull(downtimeModel.getEndTime());
// 		assertEquals(0, downtimeModel.getduration());

// 		// Test setting and getting 'id'
// 		String id = "downtime123";
// 		downtimeModel.setId(id);
// 		assertEquals(id, downtimeModel.getId());

// 		// Test setting and getting 'partnerId'
// 		String partnerId = "partner001";
// 		downtimeModel.setPartnerId(partnerId);
// 		assertEquals(partnerId, downtimeModel.getPartnerId());

// 		// Test setting and getting 'startTime'
// 		Instant startTime = Instant.now();
// 		downtimeModel.setStartTime(startTime);
// 		assertEquals(startTime, downtimeModel.getStartTime());

// 		// Test setting and getting 'endTime'
// 		Instant endTime = startTime.plusSeconds(3600); // 1 hour later
// 		downtimeModel.setEndTime(endTime);
// 		assertEquals(endTime, downtimeModel.getEndTime());

// 		// Test setting and getting 'duration'
// 		long duration = 3600; // 1 hour in seconds
// 		downtimeModel.setduration(duration);
// 		assertEquals(duration, downtimeModel.getduration());
// 	}

// 	@Test
// 	void testCalculateDuration() {
// 		// Test calculation of duration based on start and end times
// 		CPOHUBDowntimeModel downtimeModel = new CPOHUBDowntimeModel();

// 		Instant startTime = Instant.parse("2024-12-26T10:00:00Z");
// 		Instant endTime = Instant.parse("2024-12-26T11:00:00Z"); // 1 hour later

// 		downtimeModel.setStartTime(startTime);
// 		downtimeModel.setEndTime(endTime);

// 		// Manually calculate duration in seconds
// 		long calculatedDuration = endTime.getEpochSecond() - startTime.getEpochSecond();

// 		downtimeModel.setduration(calculatedDuration);

// 		assertEquals(calculatedDuration, downtimeModel.getduration());
// 	}
// }
