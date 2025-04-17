package com.evgateway.cpohubserver.form;

import java.util.ArrayList;
import java.util.List;

public class CPOTariffElement {

	private List<CPOTariffPriceComponent> price_components = new ArrayList<CPOTariffPriceComponent>();

	private CPOTariffRestrictions restrictions;

	public List<CPOTariffPriceComponent> getPrice_components() {
		return price_components;
	}

	public void setPrice_components(List<CPOTariffPriceComponent> price_components) {
		this.price_components = price_components;
	}

	public CPOTariffRestrictions getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(CPOTariffRestrictions restrictions) {
		this.restrictions = restrictions;
	}

}
