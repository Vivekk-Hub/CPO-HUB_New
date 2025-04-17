package com.evgateway.cpohubserver.request;

public class EvseWithNestedLocation {
	
	private String evseId;
	private String status;
	private String uid;
	private String physicalReference;
	private String cpoEvseId;
	private Location location; 
	

	
	
	
	public String getCpoEvseId() {
		return cpoEvseId;
	}

	public void setCpoEvseId(String cpoEvseId) {
		this.cpoEvseId = cpoEvseId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getEvseId() {
		return evseId;
	}

	public void setEvseId(String evseId) {
		this.evseId = evseId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhysicalReference() {
		return physicalReference;
	}

	public void setPhysicalReference(String physicalReference) {
		this.physicalReference = physicalReference;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public static class Location {
		private String name;
		private String address;
		private String countryCode;
		private String partyId;
		private String ids;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCountryCode() {
			return countryCode;
		}

		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

		public String getPartyId() {
			return partyId;
		}

		public void setPartyId(String partyId) {
			this.partyId = partyId;
		}

		public String getIds() {
			return ids;
		}

		public void setIds(String id) {
			this.ids = id;
		}

	}

	@Override
	public String toString() {
		return "EvseWithNestedLocation [evseId=" + evseId + ", status=" + status + ", uid=" + uid
				+ ", physicalReference=" + physicalReference + ", cpoEvseId=" + cpoEvseId + ", location=" + location
				+ "]";
	}
	
	
}
