package com.evgateway.cpohubserver.form;

public class CPOEnergyMix {

	private boolean is_green_energy;

	private CPOEnergySource energy_sources;

	private CPOEnvironmentalImpact environ_impact;

	private String supplier_name;

	private String energy_product_name;

	public boolean isIs_green_energy() {
		return is_green_energy;
	}

	public void setIs_green_energy(boolean is_green_energy) {
		this.is_green_energy = is_green_energy;
	}

	public CPOEnergySource getEnergy_sources() {
		return energy_sources;
	}

	public void setEnergy_sources(CPOEnergySource energy_sources) {
		this.energy_sources = energy_sources;
	}

	public CPOEnvironmentalImpact getEnviron_impact() {
		return environ_impact;
	}

	public void setEnviron_impact(CPOEnvironmentalImpact environ_impact) {
		this.environ_impact = environ_impact;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getEnergy_product_name() {
		return energy_product_name;
	}

	public void setEnergy_product_name(String energy_product_name) {
		this.energy_product_name = energy_product_name;
	}

}
