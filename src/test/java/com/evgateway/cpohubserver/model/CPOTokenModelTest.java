// package com.evgateway.cpohubserver.model;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import java.time.Instant;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import com.evgateway.cpohubserver.form.CPOEnergyContract;

// public class CPOTokenModelTest {

// 	private CPOTokenModel token;

// 	@BeforeEach
// 	public void setUp() {
// 		token = new CPOTokenModel();
// 	}

// 	@Test
// 	public void testSetAndGetId() {
// 		String id = "token123";
// 		token.setId(id);
// 		assertEquals(id, token.getId());
// 	}

// 	@Test
// 	public void testSetAndGetPartyId() {
// 		String partyId = "party123";
// 		token.setParty_id(partyId);
// 		assertEquals(partyId, token.getParty_id());
// 	}

// 	@Test
// 	public void testSetAndGetCountryCode() {
// 		String countryCode = "US";
// 		token.setCountry_code(countryCode);
// 		assertEquals(countryCode, token.getCountry_code());
// 	}

// 	@Test
// 	public void testSetAndGetUid() {
// 		String uid = "uid123";
// 		token.setUid(uid);
// 		assertEquals(uid, token.getUid());
// 	}

// 	@Test
// 	public void testSetAndGetType() {
// 		String type = "RFID";
// 		token.setType(type);
// 		assertEquals(type, token.getType());
// 	}

// 	@Test
// 	public void testSetAndGetAuthId() {
// 		String authId = "auth123";
// 		token.setAuth_id(authId);
// 		assertEquals(authId, token.getAuth_id());
// 	}

// 	@Test
// 	public void testSetAndGetContractId() {
// 		String contractId = "contract123";
// 		token.setContract_id(contractId);
// 		assertEquals(contractId, token.getContract_id());
// 	}

// 	@Test
// 	public void testSetAndGetVisualNumber() {
// 		String visualNumber = "123456";
// 		token.setVisual_number(visualNumber);
// 		assertEquals(visualNumber, token.getVisual_number());
// 	}

// 	@Test
// 	public void testSetAndGetGroupId() {
// 		String groupId = "group123";
// 		token.setGroup_id(groupId);
// 		assertEquals(groupId, token.getGroup_id());
// 	}

// 	@Test
// 	public void testSetAndGetIssuer() {
// 		String issuer = "issuer123";
// 		token.setIssuer(issuer);
// 		assertEquals(issuer, token.getIssuer());
// 	}

// 	@Test
// 	public void testSetAndGetValid() {
// 		boolean valid = true;
// 		token.setValid(valid);
// 		assertEquals(valid, token.isValid());
// 	}

// 	@Test
// 	public void testSetAndGetWhitelist() {
// 		String whitelist = "whitelist123";
// 		token.setWhitelist(whitelist);
// 		assertEquals(whitelist, token.getWhitelist());
// 	}

// 	@Test
// 	public void testSetAndGetLanguage() {
// 		String language = "EN";
// 		token.setLanguage(language);
// 		assertEquals(language, token.getLanguage());
// 	}

// 	@Test
// 	public void testSetAndGetDefaultProfileType() {
// 		String profileType = "type1";
// 		token.setDefault_profile_type(profileType);
// 		assertEquals(profileType, token.getDefault_profile_type());
// 	}

// 	@Test
// 	public void testSetAndGetEnergyContract() {
// 		CPOEnergyContract energyContract = new CPOEnergyContract();
// 		token.setEnergy_contract(energyContract);
// 		assertEquals(energyContract, token.getEnergy_contract());
// 	}

// 	@Test
// 	public void testSetAndGetLastUpdated() {
// 		Instant lastUpdated = Instant.now();
// 		token.setLast_updated(lastUpdated);
// 		assertEquals(lastUpdated, token.getLast_updated());
// 	}

// 	@Test
// 	public void testSetAndGetVersion() {
// 		String version = "1.0";
// 		token.setVersion(version);
// 		assertEquals(version, token.getVersion());
// 	}

// //	@Test
// //	public void testToString() {
// //		CPOEnergyContract energyContract = new CPOEnergyContract();
// //		Instant lastUpdated = Instant.now();
// //
// //		token.setId("token123");
// //		token.setParty_id("party123");
// //		token.setCountry_code("US");
// //		token.setUid("uid123");
// //		token.setType("RFID");
// //		token.setAuth_id("auth123");
// //		token.setContract_id("contract123");
// //		token.setVisual_number("123456");
// //		token.setGroup_id("group123");
// //		token.setIssuer("issuer123");
// //		token.setValid(true);
// //		token.setWhitelist("whitelist123");
// //		token.setLanguage("EN");
// //		token.setDefault_profile_type("type1");
// //		token.setEnergy_contract(energyContract);
// //		token.setLast_updated(lastUpdated);
// //		token.setVersion("1.0");
// //
// //		String expectedString = "CPOTokenModel [id=token123, party_id=party123, country_code=US, uid=uid123, type=RFID, "
// //				+ "auth_id=auth123, contract_id=contract123, visual_number=123456, group_id=group123, issuer=issuer123, "
// //				+ "valid=true, whitelist=whitelist123, language=EN, default_profile_type=type1, energy_contract="
// //				+ energyContract + ", last_updated=" + lastUpdated + "]";
// //
// //		String result = token.toString();
// //		assertNotNull(result);
// //		assertEquals(expectedString, result);
// //	}
// }
