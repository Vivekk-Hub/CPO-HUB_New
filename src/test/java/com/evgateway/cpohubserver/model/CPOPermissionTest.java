// package com.evgateway.cpohubserver.model;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertArrayEquals;

// import org.junit.jupiter.api.Test;

// public class CPOPermissionTest {

// 	@Test
// 	public void testSetAndGetId() {
// 		CPOPermission permission = new CPOPermission();
// 		String id = "permission123";
// 		permission.setId(id);
// 		assertEquals(id, permission.getId());
// 	}

// 	@Test
// 	public void testSetAndGetPartyId() {
// 		CPOPermission permission = new CPOPermission();
// 		String partyId = "party123";
// 		permission.setParty_id(partyId);
// 		assertEquals(partyId, permission.getParty_id());
// 	}

// 	@Test
// 	public void testSetAndGetModules() {
// 		CPOPermission permission = new CPOPermission();
// 		String[] modules = { "module1", "module2" };
// 		permission.setModules(modules);
// 		assertArrayEquals(modules, permission.getModules());
// 	}

// 	@Test
// 	public void testToString() {
// 		CPOPermission permission = new CPOPermission();
// 		String id = "permission123";
// 		String partyId = "party123";
// 		String[] modules = {"module 1","module 2"};

// 		permission.setId(id);
// 		permission.setParty_id(partyId);
// 		permission.setModules(modules);

// 		String expectedString = "CPOPermission [id=permission123, party_id=party123, modules="+permission.getModules()+"]";
// 		assertEquals(expectedString, permission.toString());
// 	}
// }
