// package com.evgateway.cpohubserver.model;

// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import java.time.Instant;

// public class UserTest {

// 	private User user;

// 	@BeforeEach
// 	public void setUp() {
// 		user = new User();
// 	}

// 	@Test
// 	public void testSetAndGetId() {
// 		String id = "12345";
// 		user.setId(id);
// 		assertEquals(id, user.getId());
// 	}

// 	@Test
// 	public void testSetAndGetUsername() {
// 		String username = "john_doe";
// 		user.setUsername(username);
// 		assertEquals(username, user.getUsername());
// 	}

// 	@Test
// 	public void testSetAndGetFirstName() {
// 		String firstName = "John";
// 		user.setFirstName(firstName);
// 		assertEquals(firstName, user.getFirstName());
// 	}

// 	@Test
// 	public void testSetAndGetLastName() {
// 		String lastName = "Doe";
// 		user.setLastName(lastName);
// 		assertEquals(lastName, user.getLastName());
// 	}

// 	@Test
// 	public void testSetAndGetPassword() {
// 		String password = "password123";
// 		user.setPassword(password);
// 		assertEquals(password, user.getPassword());
// 	}

// 	@Test
// 	public void testSetAndGetEmail() {
// 		String email = "john.doe@example.com";
// 		user.setEmail(email);
// 		assertEquals(email, user.getEmail());
// 	}

// 	@Test
// 	public void testSetAndGetRole() {
// 		String role = "CPOADMIN";
// 		user.setRole(role);
// 		assertEquals(role, user.getRole());
// 	}

// 	@Test
// 	public void testSetAndGetActive() {
// 		Boolean active = true;
// 		user.setActive(active);
// 		assertEquals(active, user.getActive());
// 	}

// 	@Test
// 	public void testSetAndGetLastUpdated() {
// 		Instant lastUpdated = Instant.now();
// 		user.setLastUpdated(lastUpdated);
// 		assertEquals(lastUpdated, user.getLastUpdated());
// 	}

// 	@Test
// 	public void testSetAndGetPartyId() {
// 		String partyId = "CP01";
// 		user.setParty_id(partyId);
// 		assertEquals(partyId, user.getParty_id());
// 	}

// 	@Test
// 	public void testSetAndGetCountryCode() {
// 		String countryCode = "US";
// 		user.setCountry_code(countryCode);
// 		assertEquals(countryCode, user.getCountry_code());
// 	}

// 	@Test
// 	public void testToString() {
// 		String id = "12345";
// 		String username = "john_doe";
// 		String firstName = "John";
// 		String lastName = "Doe";
// 		String email = "john.doe@example.com";
// 		String role = "CPOADMIN";
// 		Boolean active = true;
// 		Instant lastUpdated = Instant.now();
// 		String partyId = "CP01";
// 		String countryCode = "US";

// 		user.setId(id);
// 		user.setUsername(username);
// 		user.setFirstName(firstName);
// 		user.setLastName(lastName);
// 		user.setEmail(email);
// 		user.setRole(role);
// 		user.setActive(active);
// 		user.setLastUpdated(lastUpdated);
// 		user.setParty_id(partyId);
// 		user.setCountry_code(countryCode);

// 		String expectedString = "User{id=12345, username=john_doe, password=password123, firstName=John, lastName=Doe, email=john.doe@example.com, role=CPOADMIN, active=true, lastUpdated="
// 				+ lastUpdated + ", party_id=CP01, country_code=US}";
// 		String result = user.toString();

// 		assertNotNull(result);
// 		assertTrue(result.contains("id=12345"));
// 		assertTrue(result.contains("username=john_doe"));
// 		assertTrue(result.contains("firstName=John"));
// 		assertTrue(result.contains("lastName=Doe"));
// 		assertTrue(result.contains("email=john.doe@example.com"));
// 		assertTrue(result.contains("role=CPOADMIN"));
// 		assertTrue(result.contains("active=true"));
// 		assertTrue(result.contains("party_id=CP01"));
// 		assertTrue(result.contains("country_code=US"));
// 	}
// }
