package com.evgateway.cpohubserver.model;

import java.time.Instant;
 
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document(collection = "ocpi_cpohub_alert")
public class CPOHUBAlertModel {
 
    @Id
    private String id;
 
    // private String partnerId;
    private String type;
    private String details;
    private String party_id;
    private String country_code;
    private String status;
    private Instant timeStamp;
    private String comment;
	private String partnerCode;
    public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
    public String getDetails() {
        return details;
    }
 
    public void setDetails(String details) {
        this.details = details;
    }
 
    public String getParty_id() {
        return party_id;
    }
 
    public void setParty_id(String party_id) {
        this.party_id = party_id;
    }
 
    public String getCountry_code() {
        return country_code;
    }
 
    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public Instant getTimeStamp() {
        return timeStamp;
    }
 
    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }
 
    public String getComment() {
        return comment;
    }
 
    public void setComment(String comment) {
        this.comment = comment;
    }

	@Override
	public String toString() {
		return "CPOHUBAlertModel [id=" + id + ", type=" + type + ", details=" + details + ", party_id=" + party_id
				+ ", country_code=" + country_code + ", status=" + status + ", timeStamp=" + timeStamp + ", comment="
				+ comment + ", partnerCode=" + partnerCode + ", getPartnerCode()=" + getPartnerCode() + ", getId()="
				+ getId() + ", getType()=" + getType() + ", getDetails()=" + getDetails() + ", getParty_id()="
				+ getParty_id() + ", getCountry_code()=" + getCountry_code() + ", getStatus()=" + getStatus()
				+ ", getTimeStamp()=" + getTimeStamp() + ", getComment()=" + getComment() + "]";
	}
 
   

}