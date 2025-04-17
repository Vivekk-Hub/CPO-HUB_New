package com.evgateway.cpohubserver.form;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class CPOEnergySource {

	private String source;

	private double percentage;

	@DBRef
	private Set<CPOEnergyMix> energy = new HashSet<CPOEnergyMix>();

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public Set<CPOEnergyMix> getEnergy() {
		return energy;
	}

	public void setEnergy(Set<CPOEnergyMix> energy) {
		this.energy = energy;
	}

}
