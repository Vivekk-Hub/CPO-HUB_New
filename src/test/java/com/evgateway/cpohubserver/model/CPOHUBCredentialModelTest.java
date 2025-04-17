// package com.evgateway.cpohubserver.model;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.ArrayList;
// import java.util.List;

// import org.junit.jupiter.api.Test;

// import com.evgateway.cpohubserver.form.CPORoles;

// class CPOHUBCredentialModelTest {

// 	@Test
// 	public void testSetAndGetId() {
// 		CPOHUBCredentialModel model = new CPOHUBCredentialModel();
// 		String id = "cred123";
// 		model.setId(id);
// 		assertEquals(id, model.getId());
// 	}

// 	@Test
// 	public void testSetAndGetToken() {
// 		CPOHUBCredentialModel model = new CPOHUBCredentialModel();
// 		String token = "token123";
// 		model.setToken(token);
// 		assertEquals(token, model.getToken());
// 	}

// 	@Test
// 	public void testSetAndGetAccessToken() {
// 		CPOHUBCredentialModel model = new CPOHUBCredentialModel();
// 		String accessToken = "accessToken456";
// 		model.setAccessToken(accessToken);
// 		assertEquals(accessToken, model.getAccessToken());
// 	}

// 	@Test
// 	public void testSetAndGetUrl() {
// 		CPOHUBCredentialModel model = new CPOHUBCredentialModel();
// 		String url = "https://example.com";
// 		model.setUrl(url);
// 		assertEquals(url, model.getUrl());
// 	}

// 	@Test
// 	public void testSetAndGetPartyId() {
// 		CPOHUBCredentialModel model = new CPOHUBCredentialModel();
// 		String partyId = "PARTY001";
// 		model.setParty_id(partyId);
// 		assertEquals(partyId, model.getParty_id());
// 	}

// 	@Test
// 	public void testSetAndGetCountryCode() {
// 		CPOHUBCredentialModel model = new CPOHUBCredentialModel();
// 		String countryCode = "US";
// 		model.setCountry_code(countryCode);
// 		assertEquals(countryCode, model.getCountry_code());
// 	}

// 	@Test
// 	public void testSetAndGetRoles() {
// 		CPOHUBCredentialModel model = new CPOHUBCredentialModel();
// 		List<CPORoles> roles = new ArrayList<>();
// 		CPORoles role = new CPORoles(); // Assuming CPORoles is a simple object
// 		roles.add(role);
// 		model.setRoles(roles);
// 		assertEquals(roles, model.getRoles());
// 	}

// 	@Test
// 	public void testSetAndGetLocationFlag() {
// 		CPOHUBCredentialModel model = new CPOHUBCredentialModel();
// 		model.setLocation_flag(true);
// 		assertTrue(model.isLocation_flag());
// 	}

// 	@Test
// 	public void testSetAndGetTariffFlag() {
// 		CPOHUBCredentialModel model = new CPOHUBCredentialModel();
// 		model.setTariff_flag(true);
// 		assertTrue(model.isTariff_flag());
// 	}

// 	@Test
// 	public void testSetAndGetEncodetoken() {
// 		CPOHUBCredentialModel model = new CPOHUBCredentialModel();
// 		model.setEncodetoken(true);
// 		assertTrue(model.isEncodetoken());
// 	}

// 	@Test
// 	public void testSetAndGetLocation() {
// 		CPOHUBCredentialModel model = new CPOHUBCredentialModel();
// 		model.setLocation(true);
// 		assertTrue(model.isLocation());
// 	}

// 	@Test
// 	public void testSetAndGetTariff() {
// 		CPOHUBCredentialModel model = new CPOHUBCredentialModel();
// 		model.setTariff(true);
// 		assertTrue(model.isTariff());
// 	}

// 	@Test
// 	public void testSetAndGetSession() {
// 		CPOHUBCredentialModel model = new CPOHUBCredentialModel();
// 		model.setSession(true);
// 		assertTrue(model.isSession());
// 	}

// 	@Test
// 	public void testSetAndGetCdr() {
// 		CPOHUBCredentialModel model = new CPOHUBCredentialModel();
// 		model.setCdr(true);
// 		assertTrue(model.isCdr());
// 	}

// 	@Test
// 	public void testToString() {
// 		String id = "12345";
// 		String token = "token123";
// 		String accessToken = "accessToken123";
// 		String url = "http://example.com";
// 		String partyId = "party_123";
// 		String countryCode = "US";
// 		boolean locationFlag = true;
// 		boolean tariffFlag = false;
// 		boolean encodeToken = false;
// 		boolean location = false;
// 		boolean tariff = false;
// 		boolean session = false;
// 		boolean cdr = false;
// //		List<CPORoles> roles = "ADMIN";

// 		CPOHUBCredentialModel credentialModel = new CPOHUBCredentialModel();
// 		credentialModel.setId(id);
// 		credentialModel.setToken(token);
// 		credentialModel.setAccessToken(accessToken);
// 		credentialModel.setUrl(url);
// 		credentialModel.setParty_id(partyId);
// 		credentialModel.setCountry_code(countryCode);
// 		credentialModel.setLocation_flag(locationFlag);
// 		credentialModel.setTariff_flag(tariffFlag);
// 		credentialModel.setEncodetoken(false);
// 		credentialModel.setLocation(false);
// 		credentialModel.setTariff(false);
// 		credentialModel.setSession(false);
// 		credentialModel.setCdr(false);

// 		List<CPORoles> roless = new ArrayList<CPORoles>();
// 		CPORoles CPORoles = new CPORoles();
// 		CPORoles.setCountry_code("US");
// 		roless.add(CPORoles);
// 		credentialModel.setRoles(roless);

// 		String expectedString = "CPOHUBCredentialModel [id=" + id + ", token=" + token + ", accessToken=" + accessToken
// 				+ ", url=" + url + ", party_id=" + partyId + ", country_code=" + countryCode + ", location_flag="
// 				+ locationFlag + ", tariff_flag=" + tariffFlag + ", encodetoken=" + encodeToken + ", location="
// 				+ location + ", tariff=" + tariff + ", session=" + session + ", cdr=" + cdr + ", roles=" + roless + "]";

// 		String result = credentialModel.toString();
// 		assertNotNull(result);
// 		assertEquals(expectedString, result);
// 	}

// }
