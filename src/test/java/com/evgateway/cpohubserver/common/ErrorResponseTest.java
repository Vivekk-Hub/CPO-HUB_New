package com.evgateway.cpohubserver.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

class ErrorResponseTest {

	private ErrorResponse errorResponse;

	@BeforeEach
	void setUp() {
		// Initialize ErrorResponse with default constructor
		errorResponse = new ErrorResponse();
	}

	@Test
	void testDefaultConstructor() {
		assertNotNull(errorResponse.getTimestamp());
		assertEquals(0, errorResponse.getStatus_code());
		assertNull(errorResponse.getStatus_message());
	}

	@Test
	void testConstructorWithHttpStatusAndMessage() {
		errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, "Not Found");

		assertEquals(HttpStatus.NOT_FOUND.value(), errorResponse.getStatus_code());
		assertEquals("Not Found", errorResponse.getStatus_message());
	}

	@Test
	void testConstructorWithTimestampHttpStatusAndMessage() {
		Date timestamp = new Date(1633024861000L); // Example timestamp: 2021-10-01T00:47:41.000Z
		errorResponse = new ErrorResponse(timestamp, HttpStatus.BAD_REQUEST, "Bad Request");

		assertEquals(timestamp, errorResponse.getTimestamp());
		assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatus_code());
		assertEquals("Bad Request", errorResponse.getStatus_message());
	}

	@Test
	void testGettersAndSetters() {
		errorResponse.setStatus_code(500);
		errorResponse.setStatus_message("Internal Server Error");
		Date timestamp = new Date();
		errorResponse.setTimestamp(timestamp);

		assertEquals(500, errorResponse.getStatus_code());
		assertEquals("Internal Server Error", errorResponse.getStatus_message());
		assertEquals(timestamp, errorResponse.getTimestamp());
	}

	@Test
	void testJsonSerialization() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		// Create an ErrorResponse object
		errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN, "Forbidden");

		// Serialize to JSON
		String json = objectMapper.writeValueAsString(errorResponse);

		// Assert that the JSON contains expected values
		assertTrue(json.contains("\"status_code\":403"));
		assertTrue(json.contains("\"status_message\":\"Forbidden\""));
		assertTrue(json.contains("\"timestamp\":"));
	}

	@Test
	void testJsonDeserialization() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		// Example JSON string for ErrorResponse
		String json = "{\"status_code\":400,\"status_message\":\"Bad Request\",\"timestamp\":\"2021-10-01T00:47:41.000Z\"}";

		// Deserialize JSON into ErrorResponse object
		errorResponse = objectMapper.readValue(json, ErrorResponse.class);

		// Assert the deserialized values
		assertEquals(400, errorResponse.getStatus_code());
		assertEquals("Bad Request", errorResponse.getStatus_message());
		assertNotNull(errorResponse.getTimestamp()); // timestamp should be correctly parsed
	}
}
