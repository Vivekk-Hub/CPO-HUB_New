package com.evgateway.cpohubserver.services;

import java.util.List;
import java.util.Map;

import com.evgateway.cpohubserver.model.CPOHUBAlertModel;
import com.evgateway.cpohubserver.model.CPOHUBApiLogModel;
import com.evgateway.cpohubserver.request.PageResult;

public interface AlertService {

	PageResult<CPOHUBAlertModel> getAlertTableData(int pagesize, int page, Map<String, List<String>> filters);

	PageResult<CPOHUBApiLogModel> getLogTableData(int pagesize, int page, Map<String, List<String>> filters);

	CPOHUBAlertModel getAlertById(String id);

	CPOHUBApiLogModel getLogsById(String id);

}
