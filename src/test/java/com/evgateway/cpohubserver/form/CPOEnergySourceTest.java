package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;

class CPOEnergySourceTest {

	@Test
	void testConstructorAndGettersSetters() {
		CPOEnergySource energySource = new CPOEnergySource();

		// Test setter and getter for source
		energySource.setSource("Solar");
		assertEquals("Solar", energySource.getSource(), "Source should be Solar.");

		// Test setter and getter for percentage
		energySource.setPercentage(40.5);
		assertEquals(40.5, energySource.getPercentage(), "Percentage should be 40.5.");

		// Test setter and getter for energy
		Set<CPOEnergyMix> energyMixSet = new HashSet<>();
		CPOEnergyMix energyMix = new CPOEnergyMix(); // Assuming this class exists
		energyMixSet.add(energyMix);
		energySource.setEnergy(energyMixSet);
		assertEquals(energyMixSet, energySource.getEnergy(),
				"Energy set should contain the correct set of energy mixes.");
	}

//    @Test
//    void testToString() {
//        CPOEnergySource energySource = new CPOEnergySource();
//        energySource.setSource("Wind");
//        energySource.setPercentage(50.0);
//
//        // Assuming you have overridden the toString method for CPOEnergySource
//        String expectedString = "CPOEnergySource [source=Wind, percentage=50.0, energy=" + energySource.getEnergy() + "]";
//        assertEquals(expectedString, energySource.toString(), "toString should return the expected string representation.");
//    }

	@Test
	void testDefaultEnergySet() {
		CPOEnergySource energySource = new CPOEnergySource();
		assertTrue(energySource.getEnergy().isEmpty(), "Energy set should be empty by default.");
	}
}
