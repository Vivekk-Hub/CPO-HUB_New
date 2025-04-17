package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

class CPOConnectorTest {

    @Test
    void testGettersAndSetters() {
        // Create an instance of CPOConnector
        CPOConnector connector = new CPOConnector();

        // Set values for the fields
        Date date = new Date();
        connector.setId("C123");
        connector.setStandard("Standard1");
        connector.setFormat("Format1");
        connector.setPower_type("AC");
        connector.setMax_voltage(400);
        connector.setMax_amperage(20);
        connector.setMax_electric_power(8000);
        connector.setTerms_and_conditions("Terms");
        connector.setTariff_ids(new String[]{"T1", "T2"});
        connector.setLast_updated(date);

        // Test each field with corresponding getters
        assertEquals("C123", connector.getId(), "ID should match the value set.");
        assertEquals("Standard1", connector.getStandard(), "Standard should match the value set.");
        assertEquals("Format1", connector.getFormat(), "Format should match the value set.");
        assertEquals("AC", connector.getPower_type(), "Power type should match the value set.");
        assertEquals(400, connector.getMax_voltage(), "Max voltage should match the value set.");
        assertEquals(20, connector.getMax_amperage(), "Max amperage should match the value set.");
        assertEquals(8000, connector.getMax_electric_power(), "Max electric power should match the value set.");
        assertEquals("Terms", connector.getTerms_and_conditions(), "Terms and conditions should match the value set.");
        assertArrayEquals(new String[]{"T1", "T2"}, connector.getTariff_ids(), "Tariff IDs should match the value set.");
        assertEquals(date, connector.getLast_updated(), "Last updated should match the value set.");
    }

    @Test
    void testNullValues() {
        // Create an instance of CPOConnector
        CPOConnector connector = new CPOConnector();

        // Test default values (should be null or empty by default)
        assertNull(connector.getId(), "ID should be null by default.");
        assertNull(connector.getStandard(), "Standard should be null by default.");
        assertNull(connector.getFormat(), "Format should be null by default.");
        assertNull(connector.getPower_type(), "Power type should be null by default.");
        assertEquals(0, connector.getMax_voltage(), "Max voltage should be 0 by default.");
        assertEquals(0, connector.getMax_amperage(), "Max amperage should be 0 by default.");
        assertEquals(0, connector.getMax_electric_power(), "Max electric power should be 0 by default.");
        assertNull(connector.getTerms_and_conditions(), "Terms and conditions should be null by default.");
        assertNull(connector.getTariff_ids(), "Tariff IDs should be null by default.");
        assertNull(connector.getLast_updated(), "Last updated should be null by default.");
    }

    @Test
    void testToString() {
        // Create an instance of CPOConnector
        CPOConnector connector = new CPOConnector();

        // Set values for the fields
        Date date = new Date();
        connector.setId("C123");
        connector.setStandard("Standard1");
        connector.setFormat("Format1");
        connector.setPower_type("AC");
        connector.setMax_voltage(400);
        connector.setMax_amperage(20);
        connector.setMax_electric_power(8000);
        connector.setTerms_and_conditions("Terms");
        connector.setTariff_ids(new String[]{"T1", "T2"});
        connector.setLast_updated(date);

        // Test the toString method
        String expectedString = "EmspConnector [id=C123, standard=Standard1, format=Format1, power_type=AC, max_voltage=400, max_amperage=20, max_electric_power=8000, terms_and_conditions=Terms, tariff_ids=[T1, T2], last_updated=" + date + "]";
        assertEquals(expectedString, connector.toString(), "toString should return the expected string.");
    }
}
