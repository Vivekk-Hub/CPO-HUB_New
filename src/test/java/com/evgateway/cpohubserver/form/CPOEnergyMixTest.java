package com.evgateway.cpohubserver.form;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CPOEnergyMixTest {

    @Test
    void testConstructorAndGettersSetters() {
        CPOEnergyMix energyMix = new CPOEnergyMix();

        // Test setter and getter for is_green_energy
        energyMix.setIs_green_energy(true);
        assertTrue(energyMix.isIs_green_energy(), "is_green_energy should be true.");

        // Test setter and getter for supplier_name
        energyMix.setSupplier_name("Green Power Co.");
        assertEquals("Green Power Co.", energyMix.getSupplier_name(), "Supplier name should be Green Power Co.");

        // Test setter and getter for energy_product_name
        energyMix.setEnergy_product_name("EcoEnergy");
        assertEquals("EcoEnergy", energyMix.getEnergy_product_name(), "Energy product name should be EcoEnergy.");

        // Test setter and getter for energy_sources
        CPOEnergySource energySource = new CPOEnergySource();  // Assuming CPOEnergySource is a valid class
        energyMix.setEnergy_sources(energySource);
        assertEquals(energySource, energyMix.getEnergy_sources(), "Energy sources should be set correctly.");

        // Test setter and getter for environ_impact
        CPOEnvironmentalImpact impact = new CPOEnvironmentalImpact();  // Assuming CPOEnvironmentalImpact is a valid class
        energyMix.setEnviron_impact(impact);
        assertEquals(impact, energyMix.getEnviron_impact(), "Environmental impact should be set correctly.");
    }

//    @Test
//    void testToString() {
//        // Test the toString method once it is overridden in the CPOEnergyMix class
//        CPOEnergyMix energyMix = new CPOEnergyMix();
//        energyMix.setIs_green_energy(true);
//        energyMix.setSupplier_name("Green Power Co.");
//        energyMix.setEnergy_product_name("EcoEnergy");
//
//        // Assuming the toString method in CPOEnergyMix produces a string with all its fields
//        String expectedString = "CPOEnergyMix [is_green_energy=true, supplier_name=Green Power Co., energy_product_name=EcoEnergy]";
//        assertEquals(expectedString, energyMix.toString(), "toString should return the expected string representation.");
//    }
}
