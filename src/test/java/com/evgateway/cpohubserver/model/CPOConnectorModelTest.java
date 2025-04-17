//package com.evgateway.cpohubserver.model;
//
//import org.junit.jupiter.api.Test;
//
//import java.time.Instant;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CPOConnectorModelTest {
//
//	@Test
//	void testCPOConnectorModel() {
//		CPOConnectorModel connector = new CPOConnectorModel();
//
//		// Setting values
//		connector.setU_id("1");
//		connector.setUid("UID123");
//		connector.setId("ID456");
//		connector.setStandard("IEC 62196");
//		connector.setFormat("SOCKET");
//		connector.setPower_type("AC");
//		connector.setMax_voltage(400);
//		connector.setMax_amperage(32);
//		connector.setMax_electric_power(22000);
//		connector.setTerms_and_conditions("https://example.com/terms");
//		connector.setTariff_ids(new String[] { "TARIFF1", "TARIFF2" });
//		connector.setEvse_ref_id("EVSE001");
//		connector.setEvse_uid("EVSE_UID001");
//		Instant now = Instant.now();
//		connector.setLast_updated(now);
//
//		// Assertions for getters
//		assertEquals("1", connector.getU_id());
//		assertEquals("UID123", connector.getUid());
//		assertEquals("ID456", connector.getId());
//		assertEquals("IEC 62196", connector.getStandard());
//		assertEquals("SOCKET", connector.getFormat());
//		assertEquals("AC", connector.getPower_type());
//		assertEquals(400, connector.getMax_voltage());
//		assertEquals(32, connector.getMax_amperage());
//		assertEquals(22000, connector.getMax_electric_power());
//		assertEquals("https://example.com/terms", connector.getTerms_and_conditions());
//		assertArrayEquals(new String[] { "TARIFF1", "TARIFF2" }, connector.getTariff_ids());
//		assertEquals("EVSE001", connector.getEvse_ref_id());
//		assertEquals("EVSE_UID001", connector.getEvse_uid());
//		assertEquals(now, connector.getLast_updated());
//
//		// toString validation
//		String expectedToString = "CPOConnectorModel [u_id=1, uid=UID123, id=ID456, standard=IEC 62196, format=SOCKET, "
//				+ "power_type=AC, max_voltage=400, max_amperage=32, max_electric_power=22000, "
//				+ "terms_and_conditions=https://example.com/terms, tariff_ids=[TARIFF1, TARIFF2], "
//				+ "evse_ref_id=EVSE001, evse_uid=EVSE_UID001, last_updated=" + now + "]";
//		assertEquals(expectedToString, connector.toString());
//	}
//}
