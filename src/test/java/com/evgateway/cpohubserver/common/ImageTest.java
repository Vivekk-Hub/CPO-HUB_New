package com.evgateway.cpohubserver.common;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImageTest {

	private Image image;

	@BeforeEach
	void setUp() {
		// Initialize the Image object before each test
		image = new Image();
	}

	@Test
	void testGettersAndSetters() {
		image.setUrl("https://example.com/image.png");
		image.setThumbnail("https://example.com/thumbnail.png");
		image.setCategory("logo");
		image.setType("image/png");
		image.setWidth(200);
		image.setHeight(100);

		assertEquals("https://example.com/image.png", image.getUrl());
		assertEquals("https://example.com/thumbnail.png", image.getThumbnail());
		assertEquals("logo", image.getCategory());
		assertEquals("image/png", image.getType());
		assertEquals(200, image.getWidth());
		assertEquals(100, image.getHeight());
	}

	@Test
	void testToString() {
		image.setUrl("https://example.com/image.png");
		image.setThumbnail("https://example.com/thumbnail.png");
		image.setCategory("logo");
		image.setType("image/png");
		image.setWidth(200);
		image.setHeight(100);

		String expected = "Image [url=https://example.com/image.png, thumbnail=https://example.com/thumbnail.png, category=logo, type=image/png, width=200, height=100]";
		assertEquals(expected, image.toString());
	}

	@Test
	void testJsonSerialization() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		// Create and set up the Image object
		image.setUrl("https://example.com/image.png");
		image.setThumbnail("https://example.com/thumbnail.png");
		image.setCategory("logo");
		image.setType("image/png");
		image.setWidth(200);
		image.setHeight(100);

		// Serialize the Image object to JSON
		String json = objectMapper.writeValueAsString(image);

		// Assert that the JSON contains the expected fields
		assertTrue(json.contains("\"url\":\"https://example.com/image.png\""));
		assertTrue(json.contains("\"thumbnail\":\"https://example.com/thumbnail.png\""));
//		assertTrue(json.contains("\"category\":\"logo\""));
		assertTrue(json.contains("\"type\":\"image/png\""));
		assertTrue(json.contains("\"width\":200"));
		assertTrue(json.contains("\"height\":100"));
	}

	@Test
	void testJsonDeserialization() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		// Example JSON for the Image object
		String json = "{\"url\":\"https://example.com/image.png\",\"thumbnail\":\"https://example.com/thumbnail.png\",\"category\":\"logo\",\"type\":\"image/png\",\"width\":200,\"height\":100}";

		// Deserialize JSON into Image object
		image = objectMapper.readValue(json, Image.class);

		// Assert that the deserialized values are correct
		assertEquals("https://example.com/image.png", image.getUrl());
		assertEquals("https://example.com/thumbnail.png", image.getThumbnail());
//		assertEquals("logo", image.getCategory());
		assertEquals("image/png", image.getType());
		assertEquals(200, image.getWidth());
		assertEquals(100, image.getHeight());
	}

	@Test
	void testJsonIgnoreCategory() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		// Create and set up the Image object
		image.setCategory("logo");

		// Serialize the Image object to JSON
		String json = objectMapper.writeValueAsString(image);

		// Assert that the 'category' field is not included in the JSON
		assertFalse(json.contains("category"));
	}
}
