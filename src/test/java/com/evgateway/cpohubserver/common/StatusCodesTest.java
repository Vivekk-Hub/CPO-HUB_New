package com.evgateway.cpohubserver.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StatusCodesTest {

	@Test
	void testStatusCodes() {
		// Testing the values of status codes
		assertEquals(200, StatusCodes.SUCCESS, "SUCCESS status code should be 200");
		assertEquals(400, StatusCodes.BADREQUEST, "BADREQUEST status code should be 400");
		assertEquals(401, StatusCodes.UNAUTHORIZED, "UNAUTHORIZED status code should be 401");
		assertEquals(404, StatusCodes.NOT_FOUND, "NOT_FOUND status code should be 404");
		assertEquals(500, StatusCodes.Internal_Server_Error, "Internal_Server_Error status code should be 500");
	}
}
