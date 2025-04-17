package com.evgateway.cpohubserver.services;

import java.util.List;
import java.util.Map;

import com.evgateway.cpohubserver.model.CPOLocationModel;
import com.evgateway.cpohubserver.request.PageResult;

public interface LocationService {

	List<Map> getMapData();

	Object locationDetails(String id);

	CPOLocationModel getLocationByUid(String id);

	List<Map<String, Object>> getCountInformation();

	PageResult<Map<String, Object>> getTableData(int pagesize, int page, Map<String, List<String>> filters);

}
