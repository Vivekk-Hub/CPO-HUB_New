//package com.evgateway.cpohubserver.services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//import org.bson.Document;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//
//import com.evgateway.cpohubserver.cnum.ERole;
//import com.evgateway.cpohubserver.model.CPOCdrModel;
//import com.evgateway.cpohubserver.model.User;
//import com.evgateway.cpohubserver.request.PageResult;
//
//@ExtendWith(MockitoExtension.class)
//public class CdrServiceImplTest {
//
//	@InjectMocks
//	private CdrServiceImpl cdrService;
//
//	@Mock
//	private MongoTemplate mongoTemplate;
//
//	@Mock
//	private UserService userService;
//
////	Due to
////	limitations of
////	the com.mongodb.BasicDocument,you can't add a second 'null' criteria. Query already contains'{"key1":
////	{ "$regularExpression" : { "pattern" : ".*value1.*", "options" : "i"}}}'//
//
////	@Test
////	void testGetCDRTableData_withFiltersAndAdminUser() {
////		// Arrange
////		int pageSize = 10;
////		int page = 0;
////		Map<String, String> filters = Map.of("key","value");
////
////		User mockUser = new User();
////		mockUser.setRole(ERole.EMSPADMIN.toString());
////		mockUser.setParty_id("samplePartyId");
////		mockUser.setCountry_code("sampleCountryCode");
////		System.out.println("working");
////		List<CPOCdrModel> mockCdrs = List.of(new CPOCdrModel(/* Initialize fields as needed */),
////				new CPOCdrModel(/* Initialize fields as needed */));
////		long mockCount = 2L;
////
////		Pageable pageable = PageRequest.of(page, pageSize);
////		System.out.println("working");
////		Query expectedQuery = new Query().with(Sort.by(Sort.Order.desc("last_updated"))).with(pageable);
////
////		filters.forEach((key, value) -> expectedQuery.addCriteria(Criteria.where(key).regex(".*" + value + ".*", "i")));
////		System.out.println("working");
////		expectedQuery.addCriteria(Criteria.where("emsp_party_id").regex(".*samplePartyId.*", "i")
////				.and("emsp_country_code").regex(".*sampleCountryCode.*", "i"));
////
////		when(userService.getCurrentUser()).thenReturn(mockUser);
////		when(mongoTemplate.find(expectedQuery, CPOCdrModel.class)).thenReturn(mockCdrs);
////		when(mongoTemplate.count(expectedQuery.skip(-1).limit(-1), CPOCdrModel.class)).thenReturn(mockCount);
////		System.out.println("working");
////		// Act
////		PageResult<CPOCdrModel> result = cdrService.getCDRTableData(pageSize, page, filters);
////
////		// Assert
////		assertNotNull(result, "The result should not be null.");
////		System.err.println("working");
////		assertEquals(mockCdrs.size(), result.getContent().size(), "The size of the result should match the mock data.");
////
//////		assertEquals(mockCdrs, result.getPage().getContent(), "The content of the result should match the mock data.");
////		verify(userService, times(1)).getCurrentUser();
////		verify(mongoTemplate, times(1)).find(expectedQuery, CPOCdrModel.class);
////		verify(mongoTemplate, times(1)).count(expectedQuery.skip(-1).limit(-1), CPOCdrModel.class);
////	}
//
//	@Test
//	void testGetCdrByIdForCPOADMIN() {
//		// Setup test data
//		String cdrId = "123";
//		User mockUser = new User();
//		mockUser.setRole(ERole.CPOADMIN.toString()); // CPOADMIN role
//		mockUser.setParty_id("party123");
//		mockUser.setCountry_code("US");
//
//		// Mocking user service
//		when(userService.getCurrentUser()).thenReturn(mockUser);
//
//		// Mocking MongoTemplate behavior
//		CPOCdrModel mockCdr = new CPOCdrModel();
//		mockCdr.setId(cdrId);
//		mockCdr.setCurrency("USD");
//
//		List<CPOCdrModel> mockCdrs = Collections.singletonList(mockCdr);
//		when(mongoTemplate.find(any(Query.class), eq(CPOCdrModel.class))).thenReturn(mockCdrs);
//
//		// Service call
//		Object result = cdrService.getCdrById(cdrId);
//
//		// Assertions
//		assertNotNull(result);
//		assertTrue(result instanceof CPOCdrModel);
//		CPOCdrModel cdrResult = (CPOCdrModel) result;
//		assertEquals(cdrId, cdrResult.getId());
//		assertEquals("USD", cdrResult.getCurrency());
//
//		// Verify MongoTemplate interactions
//		ArgumentCaptor<Query> queryCaptor = ArgumentCaptor.forClass(Query.class);
//		verify(mongoTemplate).find(queryCaptor.capture(), eq(CPOCdrModel.class));
//
//		Query capturedQuery = queryCaptor.getValue();
//		assertNotNull(capturedQuery);
//
//		// Check that the query has the expected party_id and country_code criteria
//		Document criteria = capturedQuery.getQueryObject();
//		assertTrue(criteria.toString().contains("cpo_party_id"));
//		assertTrue(criteria.toString().contains("cpo_country_code"));
//	}
//
//	@Test
//	void testGetCdrByIdForEMSPADMIN() {
//		// Setup test data
//		String cdrId = "123";
//		User mockUser = new User();
//		mockUser.setRole(ERole.EMSPADMIN.toString()); // EMSPADMIN role
//		mockUser.setParty_id("party123");
//		mockUser.setCountry_code("US");
//
//		// Mocking user service
//		when(userService.getCurrentUser()).thenReturn(mockUser);
//
//		// Mocking MongoTemplate behavior
//		CPOCdrModel mockCdr = new CPOCdrModel();
//		mockCdr.setId(cdrId);
//		mockCdr.setCurrency("USD");
//
//		List<CPOCdrModel> mockCdrs = Collections.singletonList(mockCdr);
//		when(mongoTemplate.find(any(Query.class), eq(CPOCdrModel.class))).thenReturn(mockCdrs);
//
//		// Service call
//		Object result = cdrService.getCdrById(cdrId);
//
//		// Assertions
//		assertNotNull(result);
//		assertTrue(result instanceof CPOCdrModel);
//		CPOCdrModel cdrResult = (CPOCdrModel) result;
//		assertEquals(cdrId, cdrResult.getId());
//		assertEquals("USD", cdrResult.getCurrency());
//
//		// Verify MongoTemplate interactions
//		ArgumentCaptor<Query> queryCaptor = ArgumentCaptor.forClass(Query.class);
//		verify(mongoTemplate).find(queryCaptor.capture(), eq(CPOCdrModel.class));
//
//		Query capturedQuery = queryCaptor.getValue();
//		assertNotNull(capturedQuery);
//
//		// Check that the query has the expected party_id and country_code criteria
//		Document criteria = capturedQuery.getQueryObject();
//		assertTrue(criteria.toString().contains("emsp_party_id"));
//		assertTrue(criteria.toString().contains("emsp_country_code"));
//	}
//
//	@Test
//	void testGetCdrByIdForNoCdrFound() {
//		// Setup test data
//		String cdrId = "123";
//		User mockUser = new User();
//		mockUser.setRole(ERole.CPOADMIN.toString()); // CPOADMIN role
//		mockUser.setParty_id("party123");
//		mockUser.setCountry_code("US");
//
//		// Mocking user service
//		when(userService.getCurrentUser()).thenReturn(mockUser);
//
//		// Mocking MongoTemplate behavior to return empty list
//		when(mongoTemplate.find(any(Query.class), eq(CPOCdrModel.class))).thenReturn(Collections.emptyList());
//
//		// Service call
//		Object result = cdrService.getCdrById(cdrId);
//
//		// Assertions
//		assertNull(result); // Ensure no CDR is found
//	}
//}
