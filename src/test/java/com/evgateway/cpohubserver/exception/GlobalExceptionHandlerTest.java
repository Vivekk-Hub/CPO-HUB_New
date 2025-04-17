//package com.evgateway.cpohubserver.exception;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@SpringBootTest
//class GlobalExceptionHandlerTest {
//
//	@Autowired
//	private MockMvc mockMvc; // Use @Autowired to inject MockMvc
//
//	@Autowired
//	private GlobalExceptionHandler globalExceptionHandler; // Inject the handler to test
//
//	@BeforeEach
//	public void setUp() {
//		// Make sure MockMvc is properly initialized with the GlobalExceptionHandler
//		mockMvc = MockMvcBuilders.standaloneSetup(globalExceptionHandler).build();
//	}
//
//	@Test
//	void testHandleIllegalArgumentException() throws Exception {
//		// Mock an endpoint that will trigger IllegalArgumentException
//		mockMvc.perform(get("/some-endpoint")) // Adjust the endpoint to match your actual endpoint
//				.andExpect(status().isBadRequest())
//				.andExpect(jsonPath("$.status_code").value(HttpStatus.BAD_REQUEST.value()))
//				.andExpect(jsonPath("$.status_message").value("Invalid argument"));
//	}
//
//	@Test
//	void testHandleGenericException() throws Exception {
//		// Mock an endpoint that will trigger Exception
//		mockMvc.perform(get("/some-endpoint")) // Adjust the endpoint to match your actual endpoint
//				.andExpect(status().isInternalServerError())
//				.andExpect(jsonPath("$.status_code").value(HttpStatus.INTERNAL_SERVER_ERROR.value()))
//				.andExpect(jsonPath("$.status_message").value("An unexpected error occurred"));
//	}
//
//	@Test
//	void testHandleUserNotFoundException() throws Exception {
//		// Mock an endpoint that will trigger UserNotFoundException
//		mockMvc.perform(get("/some-endpoint")) // Adjust the endpoint to match your actual endpoint
//				.andExpect(status().isNotFound())
//				.andExpect(jsonPath("$.status_code").value(HttpStatus.NOT_FOUND.value()))
//				.andExpect(jsonPath("$.status_message").value("User not found"));
//	}
//
//	@Test
//	void testHandleInvalidCredentialsException() throws Exception {
//		// Mock an endpoint that will trigger InvalidCredentialsException
//		mockMvc.perform(get("/some-endpoint")) // Adjust the endpoint to match your actual endpoint
//				.andExpect(status().isBadRequest())
//				.andExpect(jsonPath("$.status_code").value(HttpStatus.BAD_REQUEST.value()))
//				.andExpect(jsonPath("$.status_message").value("Invalid credentials"));
//	}
//}
