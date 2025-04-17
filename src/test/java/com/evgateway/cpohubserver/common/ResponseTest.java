// package com.evgateway.cpohubserver.common;

// import static org.junit.jupiter.api.Assertions.*;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import java.util.Date;

// class ResponseTest {

// 	private Response<String> response;

// 	@BeforeEach
// 	void setUp() {
// 		// Initialize the Response object before each test
// 		response = new Response<>();
// 	}

// 	@Test
// 	void testGettersAndSetters() {
// 		response.setData("Success");
// 		response.setStatus_code(200);
// 		response.setStatus_message("OK");
// 		response.setTimestamp(new Date());

// 		assertEquals("Success", response.getData());
// 		assertEquals(200, response.getStatus_code());
// 		assertEquals("OK", response.getStatus_message());
// 		assertNotNull(response.getTimestamp());
// 	}

// 	@Test
// 	void testConstructorWithAllFields() {
// 		Date timestamp = new Date();
// 		Response<String> response = new Response<>("Data", 200, "Success", timestamp);

// 		assertEquals("Data", response.getData());
// 		assertEquals(200, response.getStatus_code());
// 		assertEquals("Success", response.getStatus_message());
// 		assertEquals(timestamp, response.getTimestamp());
// 	}

// 	@Test
// 	void testConstructorWithData() {
// 		Response<String> response = new Response<>("Data");

// 		assertEquals("Data", response.getData());
// 		assertEquals(0, response.getStatus_code()); // Default value
// 		assertNull(response.getStatus_message()); // Default value
// 		assertNull(response.getTimestamp()); // Default value
// 	}

// 	@Test
// 	void testConstructorWithStatusCodeAndMessage() {
// 		Date timestamp = new Date();
// 		Response<String> response = new Response<>(404, "Not Found", timestamp);

// 		assertNull(response.getData()); // No data provided in the constructor
// 		assertEquals(404, response.getStatus_code());
// 		assertEquals("Not Found", response.getStatus_message());
// 		assertEquals(timestamp, response.getTimestamp());
// 	}

// 	@Test
// 	void testJsonSerialization() throws Exception {
// 		ObjectMapper objectMapper = new ObjectMapper();
// 		Date timestamp = new Date();
// 		response.setData("Success");
// 		response.setStatus_code(200);
// 		response.setStatus_message("OK");
// 		response.setTimestamp(timestamp);

// 		// Serialize the Response object to JSON
// 		String json = objectMapper.writeValueAsString(response);

// 		// Assert that the JSON contains the expected fields
// 		assertTrue(json.contains("\"data\":\"Success\""));
// 		assertTrue(json.contains("\"status_code\":200"));
// 		assertTrue(json.contains("\"status_message\":\"OK\""));
// 		assertTrue(json.contains("\"timestamp\":\""));
// 	}

// 	@Test
// 	void testJsonDeserialization() throws Exception {
// 		ObjectMapper objectMapper = new ObjectMapper();
// 		String json = "{\"data\":\"Success\",\"status_code\":200,\"status_message\":\"OK\",\"timestamp\":\"2024-12-24T14:45:00Z\"}";

// 		// Deserialize JSON into a Response object
// 		Response<String> response = objectMapper.readValue(json, Response.class);

// 		// Assert that the deserialized values are correct
// 		assertEquals("Success", response.getData());
// 		assertEquals(200, response.getStatus_code());
// 		assertEquals("OK", response.getStatus_message());
// 		assertNotNull(response.getTimestamp());
// 	}
// }
