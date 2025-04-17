package com.evgateway.cpohubserver.services;

import java.util.List;
import java.util.Map;

import com.evgateway.cpohubserver.model.CPOCdrModel;
import com.evgateway.cpohubserver.request.PageResult;

public interface CdrService {

	PageResult<CPOCdrModel> getCDRTableData(int pagesize, int page, Map<String, List<String>> filters);

	Map<String, Object> getCdrById(String id);

	

}
