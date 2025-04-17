package com.evgateway.cpohubserver.model;

import org.junit.jupiter.api.Test;

import com.evgateway.cpohubserver.common.BusinessDetails;
import com.evgateway.cpohubserver.common.OCPIRolesResponse;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CPOCredentialModelTest {

	@Test
	void testCPOCredentialModel() {
		// Create an instance of CPOCredentialModel
		CPOCredentialModel credential = new CPOCredentialModel();

		// Setting values
		credential.setId("123");
		credential.setToken("TOKEN_123");
		credential.setAccess_token("ACCESS_TOKEN_456");
		credential.setUrl("https://example.com/ocpi");
		Set<OCPIRolesResponse> roles = new HashSet<>();
		OCPIRolesResponse role1 = new OCPIRolesResponse();
		role1.setRole("CPO");
		BusinessDetails BusinessDetails = new BusinessDetails();
		BusinessDetails.setName("EV Gateway");
		role1.setBusiness_details(BusinessDetails);
		roles.add(role1);
		credential.setRoles(roles);
		Instant now = Instant.now();
		credential.setLastupdated(now);

		// Assertions for getters
		assertEquals("123", credential.getId());
		assertEquals("TOKEN_123", credential.getToken());
		assertEquals("ACCESS_TOKEN_456", credential.getAccess_token());
		assertEquals("https://example.com/ocpi", credential.getUrl());
		assertEquals(roles, credential.getRoles());
		assertEquals(now, credential.getLastupdated());

		// Validate toString
		String expectedToString = "CPOCredentialModel [token=TOKEN_123, access_token=ACCESS_TOKEN_456, "
				+ "url=https://example.com/ocpi, roles=" + roles + ", lastupdated=" + now + "]";
		assertEquals(expectedToString, credential.toString());
	}
}
