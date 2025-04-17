package com.evgateway.cpohubserver.form;

public class CPOCdrSignedValue  {


	private String nature;
	private String plain_data;
	private String signed_data;

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getPlain_data() {
		return plain_data;
	}

	public void setPlain_data(String plain_data) {
		this.plain_data = plain_data;
	}

	public String getSigned_data() {
		return signed_data;
	}

	public void setSigned_data(String signed_data) {
		this.signed_data = signed_data;
	}

	@Override
	public String toString() {
		return "SignedValue [nature=" + nature + ", plain_data=" + plain_data + ", signed_data=" + signed_data + "]";
	}

}
