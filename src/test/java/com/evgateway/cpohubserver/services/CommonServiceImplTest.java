//package com.evgateway.cpohubserver.services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.when;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.evgateway.cpohubserver.model.CPOHUBPartnerModel;
//import com.evgateway.cpohubserver.repository.PartnerRepository;
//
//@SpringBootTest
//public class CommonServiceImplTest {
//
//	@Mock
//	private PartnerRepository partnerRepository;
//
//	@Mock
//	private UserService userService;
//
//	@InjectMocks
//	private CommonServiceImpl commonService;
//
//	private List<CPOHUBPartnerModel> partnerModels;
//
//	@BeforeEach
//	public void setUp() {
//		// Initialize test data
//		CPOHUBPartnerModel partner1 = new CPOHUBPartnerModel();
//		partner1.setParty_id("1");
//		partner1.setCountry_code("US");
//
//		CPOHUBPartnerModel partner2 = new CPOHUBPartnerModel();
//		partner2.setParty_id("2");
//		partner2.setCountry_code("IN");
//
//		partnerModels = Arrays.asList(partner1, partner2);
//	}
//
//	@Test
//	public void testGetPartyDetails() {
//		String role = "Admin";
//		when(partnerRepository.findByRole(role)).thenReturn(partnerModels);
//
//		List<Map<String, Object>> result = commonService.getPartyDetails(role);
//
//		assertNotNull(result);
//		assertEquals(2, result.size());
//
//		Map<String, Object> party1 = result.get(0);
//		assertEquals("1", party1.get("party_id"));
//		assertEquals("US", party1.get("country_code"));
//
//		Map<String, Object> party2 = result.get(1);
//		assertEquals("2", party2.get("party_id"));
//		assertEquals("IN", party2.get("country_code"));
//	}
//
//	@Test
//	public void testGetPartyDetails_EmptyList() {
//		String role = "NonExistentRole";
//		when(partnerRepository.findByRole(role)).thenReturn(Collections.emptyList());
//
//		List<Map<String, Object>> result = commonService.getPartyDetails(role);
//
//		assertNotNull(result);
//		assertTrue(result.isEmpty());
//	}
//
//	@Test
//	public void testGetPartyDetails_NullRole() {
//		when(partnerRepository.findByRole(null)).thenReturn(Collections.emptyList());
//
//		List<Map<String, Object>> result = commonService.getPartyDetails(null);
//
//		assertNotNull(result);
//		assertTrue(result.isEmpty());
//	}
//}
