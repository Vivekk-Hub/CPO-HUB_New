package com.evgateway.cpohubserver.form;

import java.util.ArrayList;
import java.util.List;

public class CPOCdrTariffElement {

	private List<CPOCdrPriceComponent> price_components = new ArrayList<CPOCdrPriceComponent>();

	private CPOCdrTariffRestrictions restrictions;

	private CPOCdrTariff cdr_tariff;

	public List<CPOCdrPriceComponent> getPrice_components() {
		return price_components;
	}

	public void setPrice_components(List<CPOCdrPriceComponent> price_components) {
		this.price_components = price_components;
	}

	public CPOCdrTariffRestrictions getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(CPOCdrTariffRestrictions restrictions) {
		this.restrictions = restrictions;
	}

	public CPOCdrTariff getCdr_tariff() {
		return cdr_tariff;
	}

	public void setCdr_tariff(CPOCdrTariff cdr_tariff) {
		this.cdr_tariff = cdr_tariff;
	}

}
