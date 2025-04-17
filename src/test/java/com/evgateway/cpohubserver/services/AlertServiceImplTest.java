//package com.evgateway.cpohubserver.services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.bson.Document;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//
//import com.evgateway.cpohubserver.cnum.ERole;
//import com.evgateway.cpohubserver.model.CPOHUBAlertModel;
//import com.evgateway.cpohubserver.model.CPOHUBApiLogModel;
//import com.evgateway.cpohubserver.model.User;
//import com.evgateway.cpohubserver.request.PageResult;
//
//public class AlertServiceImplTest {
//
//	@InjectMocks
//	private AlertServiceImpl alertService;
//
//	@Mock
//	private MongoTemplate mongoTemplate;
//
//	@Mock
//	private UserService userService;
//
//	private User mockCPOAdminUser;
//	private User mockEMSPAdminUser;
//
//	@BeforeEach
//	void setUp() {
//		MockitoAnnotations.openMocks(this);
//
//		// Common mock user objects
//		mockCPOAdminUser = new User();
//		mockCPOAdminUser.setRole(ERole.CPOADMIN.toString());
//		mockCPOAdminUser.setParty_id("party123");
//		mockCPOAdminUser.setCountry_code("US");
//
//		mockEMSPAdminUser = new User();
//		mockEMSPAdminUser.setRole(ERole.EMSPADMIN.toString());
//		mockEMSPAdminUser.setParty_id("party456");
//		mockEMSPAdminUser.setCountry_code("FR");
//	}
//
//	@Test
//	void testGetAlertTableDataForNonAdminUser() {
//		// Test parameters
//		int pageSize = 10;
//		int page = 0;
//		Map<String, String> filters = new HashMap<>();
//		filters.put("field1", "value1");
//
//		// Mocking a non-admin user
//		User mockUser = new User();
//		mockUser.setRole(ERole.EMSPADMIN.toString()); // Non-ADMIN role
//		mockUser.setParty_id("party123");
//		mockUser.setCountry_code("US");
//		when(userService.getCurrentUser()).thenReturn(mockUser);
//
//		// Mocking data
//		List<CPOHUBAlertModel> mockAlerts = new ArrayList<>();
//		for (int i = 0; i < 5; i++) {
//			CPOHUBAlertModel alert = new CPOHUBAlertModel();
//			alert.setId("id" + i);
//			alert.setType("Alert " + i);
//			mockAlerts.add(alert);
//		}
//		long totalCount = 5;
//
//		// Mocking MongoTemplate behavior
//		when(mongoTemplate.find(any(Query.class), eq(CPOHUBAlertModel.class))).thenReturn(mockAlerts);
//		when(mongoTemplate.count(any(Query.class), eq(CPOHUBAlertModel.class))).thenReturn(totalCount);
//
//		// Service call
//		alertService.getAlertTableData(pageSize, page, filters);
//
//		// Capture the query passed to MongoTemplate
//		ArgumentCaptor<Query> queryCaptor = ArgumentCaptor.forClass(Query.class);
//		verify(mongoTemplate).find(queryCaptor.capture(), eq(CPOHUBAlertModel.class));
//
//		Query capturedQuery = queryCaptor.getValue();
//
//		// Assertions to verify criteria
//		assertNotNull(capturedQuery);
//		org.bson.Document queryDocument = capturedQuery.getQueryObject();
//
//		// Check party_id and country_code criteria for non-admin user
//		assertTrue(queryDocument.containsKey("party_id"),
//				"Criteria for 'party_id' should be applied for non-admin users");
//		assertTrue(queryDocument.containsKey("country_code"),
//				"Criteria for 'country_code' should be applied for non-admin users");
//		assertEquals("party123", queryDocument.get("party_id"));
//		assertEquals("US", queryDocument.get("country_code"));
//
//		// Verify service interaction
//		verify(userService, times(1)).getCurrentUser();
//
//	}
////	@Test
////	void testGetAlertTableData() {
////		// Test parameters
////		int pageSize = 10;
////		int page = 0;
////		Map<String, String> filters = new HashMap<>();
////		filters.put("field1", "value1");
////		filters.put("field2", "value2");
////
////		// Mocking user
////		User mockUser = new User();
////		mockUser.setRole(ERole.ADMIN.toString());
////		mockUser.setParty_id("party123");
////		mockUser.setCountry_code("US");
////		when(userService.getCurrentUser()).thenReturn(mockUser);
////
////		// Mocking data
////		List<CPOHUBAlertModel> mockAlerts = new ArrayList<>();
////		for (int i = 0; i < 5; i++) {
////			CPOHUBAlertModel alert = new CPOHUBAlertModel();
////			alert.setId("id" + i);
////			alert.setType("Alert " + i);
////			mockAlerts.add(alert);
////		}
////		long totalCount = 5;
////
////		// Mocking MongoTemplate behavior
////		when(mongoTemplate.find(any(Query.class), eq(CPOHUBAlertModel.class))).thenReturn(mockAlerts);
////		when(mongoTemplate.count(any(Query.class), eq(CPOHUBAlertModel.class))).thenReturn(totalCount);
////
////		// Expected content size
////		int expectedContentSize = mockAlerts.size();
////
////		// Service call
////		PageResult<CPOHUBAlertModel> result = alertService.getAlertTableData(pageSize, page, filters);
////
////		// Assertions based on available methods in PageResult
////		assertNotNull(result);
////		assertEquals(expectedContentSize, result.getContent().size()); // Assuming `getContent()` returns the list
////		assertEquals(totalCount, result.getTotalElements()); // Assuming `getTotalElements()` exists
////
////		// Verify MongoTemplate interactions
////		verify(mongoTemplate, times(1)).find(any(Query.class), eq(CPOHUBAlertModel.class));
////		verify(mongoTemplate, times(1)).count(any(Query.class), eq(CPOHUBAlertModel.class));
////	}
//
////	@Test
////	void testGetAlertTableData() {
////		// Test parameters
////		int pageSize = 10;
////		int page = 0;
////		Map<String, String> filters = new HashMap<>();
////		filters.put("field1", "value1");
////		filters.put("field2", "value2");
////
////		// Mocking user
////		User mockUser = new User();
////		mockUser.setRole(ERole.ADMIN.toString());
////		mockUser.setParty_id("party123");
////		mockUser.setCountry_code("US");
////		when(userService.getCurrentUser()).thenReturn(mockUser);
////
////		// Mocking data
////		List<CPOHUBAlertModel> mockAlerts = new ArrayList<>();
////		for (int i = 0; i < 5; i++) {
////			CPOHUBAlertModel alert = new CPOHUBAlertModel();
////			alert.setId("id" + i);
////			alert.setType("Alert " + i);
////			mockAlerts.add(alert);
////		}
////		long totalCount = 5;
////
////		// Mocking MongoTemplate behavior
////		when(mongoTemplate.find(any(Query.class), eq(CPOHUBAlertModel.class))).thenReturn(mockAlerts);
////		when(mongoTemplate.count(any(Query.class), eq(CPOHUBAlertModel.class))).thenReturn(totalCount);
////
////		// Expected PageResult
////		Page<CPOHUBAlertModel> expectedPage = new PageImpl<>(mockAlerts, PageRequest.of(page, pageSize), totalCount);
////
////		// Service call
////		PageResult<CPOHUBAlertModel> result = alertService.getAlertTableData(pageSize, page, filters);
////
////		System.err.println("hi  "+expectedPage.getTotalElements());
////		// Assertions
////		assertNotNull(result);
////		assertEquals(expectedPage.getTotalElements(), result.getPage().getTotalElements());
//////		assertEquals(expectedPage.getContent().size(), result.getPage().getContent().size());
////
////		// Verify MongoTemplate interactions
////		verify(mongoTemplate, times(1)).find(any(Query.class), eq(CPOHUBAlertModel.class));
////		verify(mongoTemplate, times(1)).count(any(Query.class), eq(CPOHUBAlertModel.class));
////	}
//
//	@Test
//	void testGetAlertByIdAsCPOAdmin() {
//		// Test data
//		String alertId = "alert123";
//		CPOHUBAlertModel alert = createAlert(alertId);
//
//		List<CPOHUBAlertModel> mockAlerts = List.of(alert);
//
//		// Mocking behavior
//		when(userService.getCurrentUser()).thenReturn(mockCPOAdminUser);
//		when(mongoTemplate.find(any(Query.class), eq(CPOHUBAlertModel.class))).thenReturn(mockAlerts);
//
//		// Service call
//		CPOHUBAlertModel result = alertService.getAlertById(alertId);
//
//		// Assertions
//		assertNotNull(result);
//		assertEquals(alertId, result.getId());
//
//		// Verify interactions
//		verify(userService, times(1)).getCurrentUser();
//		verify(mongoTemplate, times(1)).find(any(Query.class), eq(CPOHUBAlertModel.class));
//	}
//
//	@Test
//	void testGetAlertByIdAsEMSPAdmin() {
//		// Test data
//		String alertId = "alert456";
//		CPOHUBAlertModel alert = createAlert(alertId);
//
//		List<CPOHUBAlertModel> mockAlerts = List.of(alert);
//
//		// Mocking behavior
//		when(userService.getCurrentUser()).thenReturn(mockEMSPAdminUser);
//		when(mongoTemplate.find(any(Query.class), eq(CPOHUBAlertModel.class))).thenReturn(mockAlerts);
//
//		// Service call
//		CPOHUBAlertModel result = alertService.getAlertById(alertId);
//
//		// Assertions
//		assertNotNull(result);
//		assertEquals(alertId, result.getId());
//
//		// Verify interactions
//		verify(userService, times(1)).getCurrentUser();
//		verify(mongoTemplate, times(1)).find(any(Query.class), eq(CPOHUBAlertModel.class));
//	}
//
//	@Test
//	void testGetAlertByIdNotFound() {
//		// Test data
//		String alertId = "nonexistent123";
//
//		// Mocking behavior
//		when(userService.getCurrentUser()).thenReturn(mockCPOAdminUser);
//		when(mongoTemplate.find(any(Query.class), eq(CPOHUBAlertModel.class))).thenReturn(new ArrayList<>());
//
//		// Service call
//		CPOHUBAlertModel result = alertService.getAlertById(alertId);
//
//		// Assertions
//		assertNull(result);
//
//		// Verify interactions
//		verify(userService, times(1)).getCurrentUser();
//		verify(mongoTemplate, times(1)).find(any(Query.class), eq(CPOHUBAlertModel.class));
//	}
//
//	// Helper method to create a mock alert
//	private CPOHUBAlertModel createAlert(String alertId) {
//		CPOHUBAlertModel alert = new CPOHUBAlertModel();
//		alert.setId(alertId);
//		return alert;
//	}
//
//	@Test
//	void testGetLogTableDataForNonAdminUser() {
//		// Test parameters
//		int pageSize = 10;
//		int page = 0;
//		Map<String, String> filters = new HashMap<>();
//		filters.put("field1", "value1");
//
//		// Mocking a non-admin user
//		User mockUser = new User();
//		mockUser.setRole(ERole.CPOADMIN.toString()); // Non-ADMIN role
//		mockUser.setParty_id("party123");
//		mockUser.setCountry_code("US");
//		when(userService.getCurrentUser()).thenReturn(mockUser);
//
//		// Mocking data
//		List<CPOHUBApiLogModel> mockLogs = new ArrayList<>();
//		for (int i = 0; i < 5; i++) {
//			CPOHUBApiLogModel log = new CPOHUBApiLogModel();
//			log.setId("id" + i);
//			log.setStatusText("Log message " + i);
//			mockLogs.add(log);
//		}
//		long totalCount = 5;
//
//		// Mocking MongoTemplate behavior
//		when(mongoTemplate.find(any(Query.class), eq(CPOHUBApiLogModel.class))).thenReturn(mockLogs);
//		when(mongoTemplate.count(any(Query.class), eq(CPOHUBApiLogModel.class))).thenReturn(totalCount);
//
//		// Service call
//		PageResult<CPOHUBApiLogModel> result = alertService.getLogTableData(pageSize, page, filters);
//
//		// Assertions
//		assertNotNull(result);
//		assertEquals(mockLogs.size(), result.getContent().size()); // Validate content size
//		assertEquals(totalCount, result.getTotalElements()); // Validate total elements
//
//		// Verify MongoTemplate interactions
//		ArgumentCaptor<Query> queryCaptor = ArgumentCaptor.forClass(Query.class);
//		verify(mongoTemplate).find(queryCaptor.capture(), eq(CPOHUBApiLogModel.class));
//
//		Query capturedQuery = queryCaptor.getValue();
//		assertNotNull(capturedQuery);
//		Document queryDocument = capturedQuery.getQueryObject();
//
//		// Check party_id and country_code criteria for non-admin user
//		assertTrue(queryDocument.containsKey("party_id"),
//				"Criteria for 'party_id' should be applied for non-admin users");
//		assertTrue(queryDocument.containsKey("country_code"),
//				"Criteria for 'country_code' should be applied for non-admin users");
//		assertEquals("party123", queryDocument.get("party_id"));
//		assertEquals("US", queryDocument.get("country_code"));
//
//		// Verify service interaction
//		verify(userService, times(1)).getCurrentUser();
//	}
//
//	@Test
//	void testGetLogTableDataForAdminUser() {
//		// Test parameters
//		int pageSize = 10;
//		int page = 0;
//		Map<String, String> filters = new HashMap<>();
//		filters.put("field1", "value1");
//
//		// Mocking an admin user
//		User mockUser = new User();
//		mockUser.setRole(ERole.ADMIN.toString()); // Admin role
//		when(userService.getCurrentUser()).thenReturn(mockUser);
//
//		// Mocking data
//		List<CPOHUBApiLogModel> mockLogs = new ArrayList<>();
//		for (int i = 0; i < 5; i++) {
//			CPOHUBApiLogModel log = new CPOHUBApiLogModel();
//			log.setId("id" + i);
//			log.setStatusText("Log message " + i);
//			mockLogs.add(log);
//		}
//		long totalCount = 5;
//
//		// Mocking MongoTemplate behavior
//		when(mongoTemplate.find(any(Query.class), eq(CPOHUBApiLogModel.class))).thenReturn(mockLogs);
//		when(mongoTemplate.count(any(Query.class), eq(CPOHUBApiLogModel.class))).thenReturn(totalCount);
//
//		// Service call
//		PageResult<CPOHUBApiLogModel> result = alertService.getLogTableData(pageSize, page, filters);
//
//		// Assertions
//		assertNotNull(result);
//		assertEquals(mockLogs.size(), result.getContent().size()); // Validate content size
//		assertEquals(totalCount, result.getTotalElements()); // Validate total elements
//
//		// Verify MongoTemplate interactions
//		ArgumentCaptor<Query> queryCaptor = ArgumentCaptor.forClass(Query.class);
//		verify(mongoTemplate).find(queryCaptor.capture(), eq(CPOHUBApiLogModel.class));
//
//		Query capturedQuery = queryCaptor.getValue();
//		assertNotNull(capturedQuery);
//		Document queryDocument = capturedQuery.getQueryObject();
//
//		// Ensure no party_id or country_code criteria for admin user
//		assertFalse(queryDocument.containsKey("party_id"),
//				"Criteria for 'party_id' should not be applied for admin users");
//		assertFalse(queryDocument.containsKey("country_code"),
//				"Criteria for 'country_code' should not be applied for admin users");
//
//		// Verify service interaction
//		verify(userService, times(1)).getCurrentUser();
//	}
//
//	@Test
//	void testGetLogsByIdForCPOADMIN() {
//		// Setup test data
//		String logId = "123";
//		User mockUser = new User();
//		mockUser.setRole(ERole.CPOADMIN.toString()); // CPOADMIN role
//		mockUser.setParty_id("party123");
//		mockUser.setCountry_code("US");
//
//		// Mocking user service
//		when(userService.getCurrentUser()).thenReturn(mockUser);
//
//		// Mocking MongoTemplate behavior
//		CPOHUBApiLogModel mockLog = new CPOHUBApiLogModel();
//		mockLog.setId(logId);
//		mockLog.setStatusText("Test log for CPOADMIN");
//
//		List<CPOHUBApiLogModel> mockLogs = Collections.singletonList(mockLog);
//		when(mongoTemplate.find(any(Query.class), eq(CPOHUBApiLogModel.class))).thenReturn(mockLogs);
//
//		// Service call
//		CPOHUBApiLogModel result = alertService.getLogsById(logId);
//
//		// Assertions
//		assertNotNull(result);
//		assertEquals(logId, result.getId()); // Validate the log ID
//		assertEquals("Test log for CPOADMIN", result.getStatusText()); // Validate the message
//
//		// Verify MongoTemplate interactions
//		ArgumentCaptor<Query> queryCaptor = ArgumentCaptor.forClass(Query.class);
//		verify(mongoTemplate).find(queryCaptor.capture(), eq(CPOHUBApiLogModel.class));
//
//		Query capturedQuery = queryCaptor.getValue();
//		assertNotNull(capturedQuery);
//
//		// Check that the query has the expected party_id and country_code criteria
//		Document criteria = capturedQuery.getQueryObject();
//		assertTrue(criteria.toString().contains("party_id"));
//		assertTrue(criteria.toString().contains("country_code"));
//	}
//
//	@Test
//	void testGetLogsByIdForEMSPADMIN() {
//		// Setup test data
//		String logId = "123";
//		User mockUser = new User();
//		mockUser.setRole(ERole.EMSPADMIN.toString()); // EMSPADMIN role
//		mockUser.setParty_id("party123");
//		mockUser.setCountry_code("US");
//
//		// Mocking user service
//		when(userService.getCurrentUser()).thenReturn(mockUser);
//
//		// Mocking MongoTemplate behavior
//		CPOHUBApiLogModel mockLog = new CPOHUBApiLogModel();
//		mockLog.setId(logId);
//		mockLog.setStatusText("Test log for EMSPADMIN");
//
//		List<CPOHUBApiLogModel> mockLogs = Collections.singletonList(mockLog);
//		when(mongoTemplate.find(any(Query.class), eq(CPOHUBApiLogModel.class))).thenReturn(mockLogs);
//
//		// Service call
//		CPOHUBApiLogModel result = alertService.getLogsById(logId);
//
//		// Assertions
//		assertNotNull(result);
//		assertEquals(logId, result.getId()); // Validate the log ID
//		assertEquals("Test log for EMSPADMIN", result.getStatusText()); // Validate the message
//
//		// Verify MongoTemplate interactions
//		ArgumentCaptor<Query> queryCaptor = ArgumentCaptor.forClass(Query.class);
//		verify(mongoTemplate).find(queryCaptor.capture(), eq(CPOHUBApiLogModel.class));
//
//		Query capturedQuery = queryCaptor.getValue();
//		assertNotNull(capturedQuery);
//
//		// Check that the query has the expected party_id and country_code criteria
//		Document criteria = capturedQuery.getQueryObject();
//		assertTrue(criteria.toString().contains("party_id"));
//		assertTrue(criteria.toString().contains("country_code"));
//	}
//
//	@Test
//	void testGetLogsByIdForNoLogsFound() {
//		// Setup test data
//		String logId = "123";
//		User mockUser = new User();
//		mockUser.setRole(ERole.CPOADMIN.toString()); // CPOADMIN role
//		mockUser.setParty_id("party123");
//		mockUser.setCountry_code("US");
//
//		// Mocking user service
//		when(userService.getCurrentUser()).thenReturn(mockUser);
//
//		// Mocking MongoTemplate behavior to return empty list
//		when(mongoTemplate.find(any(Query.class), eq(CPOHUBApiLogModel.class))).thenReturn(Collections.emptyList());
//
//		// Service call
//		CPOHUBApiLogModel result = alertService.getLogsById(logId);
//
//		// Assertions
//		assertNull(result); // Ensure no log is found
//	}
//}
