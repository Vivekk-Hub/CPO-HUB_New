package com.evgateway.cpohubserver.form;

public class CPOTariffPriceComponent {

	private String type;

	private double price;

	private double vat;

	private int step_size;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStep_size() {
		return step_size;
	}

	public void setStep_size(int step_size) {
		this.step_size = step_size;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
