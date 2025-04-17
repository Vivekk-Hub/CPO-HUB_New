package com.evgateway.cpohubserver.cnum;

public enum Status {
	
	AVAILABLE, // The EVSE/Connector is able to start a new charging session.
    BLOCKED, // The EVSE/Connector is not accessible because of a physical barrier, i.e.a car.
    CHARGING, // The EVSE/Connector is in use.
    INOPERATIVE, // The EVSE/Connector is not yet active or it is no longer available (deleted).
    OUTOFORDER, // The EVSE/Connector is currently out of order.
    PLANNED, // The EVSE/Connector is planned, will be operating soon
    REMOVED, // The EVSE/Connector/charge point is discontinued/removed.
    RESERVED, // The EVSE/Connector is reserved for a particular EV driver and is unavailable for other drivers.
    UNKNOWN, // No status information available. (Also used when offline)
    ONLINE,
    OFFLINE

}
