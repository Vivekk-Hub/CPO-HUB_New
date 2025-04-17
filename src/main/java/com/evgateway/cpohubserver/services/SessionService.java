package com.evgateway.cpohubserver.services;

import java.util.List;
import java.util.Map;

import com.evgateway.cpohubserver.model.CPOSessionsActivityModel;
import com.evgateway.cpohubserver.request.PageResult;

public interface SessionService {
	
	PageResult<CPOSessionsActivityModel> getUserSession(int pagesize, int page,Map<String, List<String>> filters);

	CPOSessionsActivityModel getSessionActivityById(String sessionId);

}
