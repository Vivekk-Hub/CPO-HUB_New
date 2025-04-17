package com.evgateway.cpohubserver.services;

import java.util.List;
import java.util.Map;

import com.evgateway.cpohubserver.model.CPOTokenModel;
import com.evgateway.cpohubserver.request.PageResult;

public interface TokenService {

	PageResult<CPOTokenModel> getTokenTableData(int pagesize, int page, Map<String, List<String>> filters);

	CPOTokenModel getTokenById(String id);

}
