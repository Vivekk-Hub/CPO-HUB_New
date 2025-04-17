package com.evgateway.cpohubserver.form;

public class CPOCdrSignedData  {

	
	private String encoding_method;
	private int encoding_method_version;
	private String public_key;
	
	private CPOCdrSignedValue signed_values;
	
	private String url;

	public String getEncoding_method() {
		return encoding_method;
	}

	public void setEncoding_method(String encoding_method) {
		this.encoding_method = encoding_method;
	}

	public int getEncoding_method_version() {
		return encoding_method_version;
	}

	public void setEncoding_method_version(int encoding_method_version) {
		this.encoding_method_version = encoding_method_version;
	}

	public String getPublic_key() {
		return public_key;
	}

	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}

	
	public CPOCdrSignedValue getSigned_values() {
		return signed_values;
	}

	public void setSigned_values(CPOCdrSignedValue signed_values) {
		this.signed_values = signed_values;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "SignedData [encoding_method=" + encoding_method + ", encoding_method_version=" + encoding_method_version
				+ ", public_key=" + public_key + ", signed_values=" + signed_values + ", url=" + url + "]";
	}

}
