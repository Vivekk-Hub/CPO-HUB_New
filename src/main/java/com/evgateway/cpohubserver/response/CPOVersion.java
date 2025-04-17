package com.evgateway.cpohubserver.response;

public class CPOVersion {

	private String version;

	private String url;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "CPOVersion [version=" + version + ", url=" + url + "]";
	}

}
