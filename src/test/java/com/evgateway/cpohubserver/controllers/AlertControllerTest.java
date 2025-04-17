//package com.evgateway.cpohubserver.controllers;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.evgateway.cpohubserver.common.Response;
//import com.evgateway.cpohubserver.common.StatusCodes;
//import com.evgateway.cpohubserver.model.CPOHUBAlertModel;
//import com.evgateway.cpohubserver.request.PageResult;
//import com.evgateway.cpohubserver.services.AlertService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@WebMvcTest(AlertController.class)
//class AlertControllerTest {
//
//	private MockMvc mockMvc;
//
//	@Mock
//	private AlertService alertService;
//
//	@InjectMocks
//	private AlertController alertController;
//
//	private ObjectMapper objectMapper;
//
//	@BeforeEach
//	void setUp() {
//		MockitoAnnotations.openMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(alertController).build();
//		objectMapper = new ObjectMapper();
//	}
//
//	@Test
//	void testGetAlertTableData() throws Exception {
//		Map<String, String> filters = new HashMap<>();
//		filters.put("status", "Success");
//
//		PageResult<CPOHUBAlertModel> pageResult = new PageResult<>();
//		pageResult.setContent(Collections.emptyList());
//		pageResult.setTotalElements(0);
//		pageResult.setSize(10);
//		pageResult.setTotalPages(1);
//
//		when(alertService.getAlertTableData(10, 0, filters)).thenReturn(pageResult);
//
//		mockMvc.perform(get("/api/alert").param("filters", objectMapper.writeValueAsString(filters)).param("page", "0")
//				.param("pagesize", "10").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
////	            .andExpect(jsonPath("$.statusCode").value(StatusCodes.SUCCESS))
//				.andExpect(jsonPath("$.data.totalElements").value(0));
//
//		verify(alertService, times(1)).getAlertTableData(10, 0, filters);
//	}
//
//	@Test
//	public void testGetAlertById() throws Exception {
//		String alertId = "123";
//		CPOHUBAlertModel alert = new CPOHUBAlertModel(); 
//		alert.setCountry_code("US");
//		alert.setId("540");
//		Response<CPOHUBAlertModel> response = new Response<>(alert, StatusCodes.SUCCESS, "Success", new Date());
//		System.out.println("1");
//		// Mock the service call
//		when(alertService.getAlertById(alertId)).thenReturn(alert);
//		System.out.println("2");
//		// Perform the GET request
//		MvcResult result = mockMvc.perform(get("/alert/{id}", alertId))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.statusCode").value(StatusCodes.SUCCESS))
//				.andExpect(jsonPath("$.message").value("Success")).andExpect(jsonPath("$.data").exists()).andReturn();
//		System.out.println("3");
//		// Optional: you can print the result for debugging
//		String content = result.getResponse().getContentAsString();
//		System.out.println(content);
//System.out.println("4");
//		// Verify service interaction
//		verify(alertService, times(1)).getAlertById(alertId);
//	}
//
////	@Test
////	void testGetLogTableData() throws Exception {
////		Map<String, String> filters = new HashMap<>();
////		filters.put("type", "error");
////
////		PageResult<CPOHUBApiLogModel> pageResult = new PageResult<>(Collections.emptyList(), 0, 10, 1);
////		when(alertService.getLogTableData(10, 0, filters)).thenReturn(pageResult);
////
////		mockMvc.perform(get("/api/logs").param("filters", objectMapper.writeValueAsString(filters)).param("page", "0")
////				.param("pagesize", "10").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
////				.andExpect(jsonPath("$.statusCode").value(StatusCodes.SUCCESS))
////				.andExpect(jsonPath("$.data.totalElements").value(0));
////
////		verify(alertService, times(1)).getLogTableData(10, 0, filters);
////	}
//
////	@Test
////	void testGetLogsById() throws Exception {
////		String logId = "67890";
////		CPOHUBApiLogModel logModel = new CPOHUBApiLogModel();
////		when(alertService.getLogsById(logId)).thenReturn(logModel);
////
////		mockMvc.perform(get("/api/logs/{id}", logId).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
////				.andExpect(jsonPath("$.statusCode").value(StatusCodes.SUCCESS));
////
////		verify(alertService, times(1)).getLogsById(logId);
////	}
//}
