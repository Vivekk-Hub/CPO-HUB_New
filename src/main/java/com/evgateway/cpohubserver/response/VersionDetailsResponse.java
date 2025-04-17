package com.evgateway.cpohubserver.response;

import java.util.List;

public class VersionDetailsResponse {

	private String version;

	private List<CPOEndpointResponse> endpoints;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<CPOEndpointResponse> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(List<CPOEndpointResponse> endpoints) {
		this.endpoints = endpoints;
	}

	@Override
	public String toString() {
		return "VersionDetailsResponse [version=" + version + ", endpoints=" + endpoints + "]";
	}

}
