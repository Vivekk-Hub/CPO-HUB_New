package com.evgateway.cpohubserver.services;

import java.util.List;
import java.util.Map;

public interface DashboardService {

	// public List<Map<String, Object>> getAllCount();

	// public List<Map> getRecentTransaction();

	// public List<Map<String, Object>> getPie();

	// public List<Map> getReportClientBased(int basedOn);

	// List<Map> getReportMonthBased(int basedOn);

	public  List<Map<String, Object>> getDashboardReports(int id, int period, String type);



}
