package com.evgateway.cpohubserver.services;

import java.util.List;
import java.util.Map;

import com.evgateway.cpohubserver.model.CPOHUBRequest;
import com.evgateway.cpohubserver.model.CPOSessionsActivityModel;
import com.evgateway.cpohubserver.request.PageResult;

public interface EmspRequestService {

	CPOHUBRequest getEmspRequestById(String id);

	CPOSessionsActivityModel getSessionActivityById(String id);

	PageResult<CPOHUBRequest> emspRequest(int pagesize, int page, Map<String, List<String>> filters);

}
