package com.evgateway.cpohubserver.form;

public class CPODimension {

	private String type;

	private double volume;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "CdrDimension [type=" + type + ", volume=" + volume + "]";
	}

}
