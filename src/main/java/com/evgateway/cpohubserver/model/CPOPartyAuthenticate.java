package com.evgateway.cpohubserver.model;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ocpi_cpo_party_authenticate")
public class CPOPartyAuthenticate {

	private String party_id;

	public String getParty_id() {
		return party_id;
	}

	public void setParty_id(String party_id) {
		this.party_id = party_id;
	}

	@Override
	public String toString() {
		return "CPOPartyAuthenticate [party_id=" + party_id + "]";
	}

}
