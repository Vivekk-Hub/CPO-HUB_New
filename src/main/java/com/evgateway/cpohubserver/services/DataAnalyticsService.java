package com.evgateway.cpohubserver.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.evgateway.cpohubserver.model.CPOCdrModel;
import com.evgateway.cpohubserver.request.PageResult;

public interface DataAnalyticsService {

//	List<CPOCdrModel> getAllReport(String start_date_time, String end_date_time) throws ParseException;

	PageResult<CPOCdrModel> getTableData(int pagesize, int page, Map<String, String> filters) throws ParseException;

	byte[] exportData(Map<String, String> filters) throws IOException, ParseException;

	List<Map<String, Object>> getRoleInfo(String role);


//	Object fetchTableData(int pagesize, int page, Map filters);



	
}
