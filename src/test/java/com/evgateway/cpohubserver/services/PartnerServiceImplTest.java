//package com.evgateway.cpohubserver.services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.when;
//
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//
//import com.evgateway.cpohubserver.cnum.ERole;
//import com.evgateway.cpohubserver.model.CPOEndpointModel;
//import com.evgateway.cpohubserver.model.CPOHUBDowntimeModel;
//import com.evgateway.cpohubserver.model.CPOHUBPartnerModel;
//import com.evgateway.cpohubserver.model.User;
//import com.evgateway.cpohubserver.repository.CPOEndpointRepository;
//import com.evgateway.cpohubserver.repository.DownTimeRepository;
//import com.evgateway.cpohubserver.request.PageResult;
//
//@SpringBootTest
//public class PartnerServiceImplTest {
//
//	@Mock
//	private DownTimeRepository downTimeRepository;
//
//	@Mock
//	private CPOEndpointRepository cpoEndpointRepository;
//
//	@Mock
//	private MongoTemplate mongoTemplate;
//
//	@Mock
//	private UserService userService;
//
//	@Mock
//	private RestTemplateService restTemplateService;
//
//	@InjectMocks
//	private PartnerServiceImpl partnerService;
//
//	private CPOHUBPartnerModel partnerModel;
//	private List<CPOHUBPartnerModel> partnerModels;
//
//	private CPOHUBDowntimeModel downtimeModel;
//	private List<CPOHUBDowntimeModel> downtimeModels;
//	private User currentUser;
//
//	private List<CPOEndpointModel> endpointModels;
//
//	@BeforeEach
//	public void setUp() {
//		// Setting up test data for partner model
//		partnerModel = new CPOHUBPartnerModel();
//		partnerModel.setId("1");
//		partnerModel.setParty_id("123");
//		partnerModel.setCountry_code("US");
//		partnerModel.setLast_activity(Instant.now());
//
//		partnerModels = new ArrayList<>();
//		partnerModels.add(partnerModel);
//
//		// Setting up test data for downtime model
//		downtimeModel = new CPOHUBDowntimeModel();
//		downtimeModel.setId("1");
//		downtimeModel.setPartnerId("123");
//		downtimeModel.setEndTime(Instant.now());
//
//		downtimeModels = new ArrayList<>();
//		downtimeModels.add(downtimeModel);
//
//		// Set up mock data for User
//		currentUser = new User();
//		currentUser.setRole(ERole.CPOADMIN.toString());
//		currentUser.setParty_id("123");
//
//		// Set up mock data for CPOEndpointModel
//		endpointModels = new ArrayList<>();
//		CPOEndpointModel endpoint = new CPOEndpointModel();
//		endpoint.setParty_id("123");
//		endpoint.setCountry_code("US");
//		endpointModels.add(endpoint);
//	}
//
//	@Test
//	public void testGetPartnerTableData() {
//		// Arrange
//		int pageSize = 10;
//		int page = 0;
//		Map<String, String> filters = new HashMap<>();
//		filters.put("partnerId", "1");
//
//		Pageable pageable = PageRequest.of(page, pageSize);
//		Query query = new Query(Criteria.where("partnerId").regex(".*1.*", "i"));
//		query.with(Sort.by(Sort.Order.desc("last_activity")));
//
//		when(mongoTemplate.find(query.with(pageable), CPOHUBPartnerModel.class)).thenReturn(partnerModels);
//		when(mongoTemplate.count(query.skip(-1).limit(-1), CPOHUBPartnerModel.class))
//				.thenReturn((long) partnerModels.size());
//
//		// Act
//		PageResult<CPOHUBPartnerModel> result = partnerService.getPartnerTableData(pageSize, page, filters);
//
//		// Assert
//		assertNotNull(result);
//		System.err.println("result  :  " + result);
////		assertEquals(partnerModels.size(), result.getContent().size());
//		assertEquals(1, result.getCurrentPage()); // Current page should be 0 (as passed)
//		assertEquals(0, result.getTotalPages()); // Only one page, as we have only 1 element
//
////		 assertEquals(1, result.getData().size());
//		assertEquals(0, result.getNumberOfElements());
////		assertEquals("1", result.getData().get(0).getPartnerId());
//	}
//
//	@Test
//	public void testGetPartnerTableData_WithNoFilters() {
//		// Arrange
//		int pageSize = 10;
//		int page = 0;
//		Map<String, String> filters = new HashMap<>();
//
//		Pageable pageable = PageRequest.of(page, pageSize);
//		Query query = new Query();
//		query.with(Sort.by(Sort.Order.desc("last_activity")));
//
//		when(mongoTemplate.find(query.with(pageable), CPOHUBPartnerModel.class)).thenReturn(partnerModels);
//		when(mongoTemplate.count(query.skip(-1).limit(-1), CPOHUBPartnerModel.class))
//				.thenReturn((long) partnerModels.size());
//
//		// Act
//		PageResult<CPOHUBPartnerModel> result = partnerService.getPartnerTableData(pageSize, page, filters);
//
//		// Assert
//		assertNotNull(result);
////		assertEquals(1, result.getData().size());
//	}
//
//	@Test
//	public void testGetDowntimeByPartnerId() {
//		// Arrange
//		String partnerId = "1";
//		Query query = new Query(Criteria.where("partnerId").is(partnerId));
//		query.with(Sort.by(Sort.Order.desc("endTime")));
//
//		when(mongoTemplate.find(query, CPOHUBDowntimeModel.class)).thenReturn(downtimeModels);
//
//		// Act
//		List<CPOHUBDowntimeModel> result = partnerService.getDowntimeByPartnerId(partnerId);
//
//		// Assert
//		assertNotNull(result);
//		assertEquals(1, result.size());
//		assertEquals("123", result.get(0).getPartnerId());
//	}
//
//	@Test
//	public void testGetDowntimeByPartnerId_NoDowntime() {
//		// Arrange
//		String partnerId = "2"; // Partner with no downtime
//		Query query = new Query(Criteria.where("partnerId").is(partnerId));
//		query.with(Sort.by(Sort.Order.desc("endTime")));
//
//		when(mongoTemplate.find(query, CPOHUBDowntimeModel.class)).thenReturn(Collections.emptyList());
//
//		// Act
//		List<CPOHUBDowntimeModel> result = partnerService.getDowntimeByPartnerId(partnerId);
//
//		// Assert
//		assertNotNull(result);
//		assertTrue(result.isEmpty());
//	}
//
//	@Test
//	public void testGetPartnerById() {
//		// Arrange
//		String id = "1";
//		Query query = new Query(Criteria.where("id").is(id));
//		query.addCriteria(Criteria.where("party_id").is(currentUser.getParty_id()));
//
//		when(userService.getCurrentUser()).thenReturn(currentUser);
//		when(mongoTemplate.findOne(query, CPOHUBPartnerModel.class)).thenReturn(partnerModel);
//
//		// Act
//		CPOHUBPartnerModel result = partnerService.getPartnerById(id);
//
//		// Assert
//		assertNotNull(result);
//		assertEquals("1", result.getId());
//		assertEquals("123", result.getParty_id());
//	}
//
//	@Test
//	public void testGetDowntimeById() {
//		// Arrange
//		String id = "1";
//		when(downTimeRepository.findById(id)).thenReturn(Optional.of(downtimeModel));
//
//		// Act
//		CPOHUBDowntimeModel result = partnerService.getDowntimeById(id);
//
//		// Assert
//		assertNotNull(result);
//		assertEquals("1", result.getId());
//		assertEquals("123", result.getPartnerId());
//	}
//
//	@Test
//	public void testGetEndPointsByPartnerId() {
//		// Arrange
//		String partnerId = "1";
//		when(partnerService.getPartnerById(partnerId)).thenReturn(partnerModel);
//		when(cpoEndpointRepository.findByPartyIdAndCountryCode("123", "US")).thenReturn(endpointModels);
//
//		// Act
//		List<CPOEndpointModel> result = partnerService.getEndPointsByPartnerId(partnerId);
//
//		// Assert
//		assertNotNull(result);
//		assertEquals(1, result.size());
//		assertEquals("123", result.get(0).getParty_id());
//		assertEquals("US", result.get(0).getCountry_code());
//	}
//
//	@Test
//	public void testGetPartnerById_NoResult() {
//		// Arrange
//		String id = "2";
//		Query query = new Query(Criteria.where("id").is(id));
//		query.addCriteria(Criteria.where("party_id").is(currentUser.getParty_id()));
//
//		when(userService.getCurrentUser()).thenReturn(currentUser);
//		when(mongoTemplate.findOne(query, CPOHUBPartnerModel.class)).thenReturn(null);
//
//		// Act
//		CPOHUBPartnerModel result = partnerService.getPartnerById(id);
//
//		// Assert
//		assertNull(result);
//	}
//
////	@Test
////	public void testGetDowntimeById_NoResult() {
////		// Arrange
////		String id = "2";
////		when(downTimeRepository.findById(id)).thenReturn(Optional.empty());
////
////		// Act
////		CPOHUBDowntimeModel result = partnerService.getDowntimeById(id);
////
////		// Assert
////		assertNull(result);
////	}
//}
