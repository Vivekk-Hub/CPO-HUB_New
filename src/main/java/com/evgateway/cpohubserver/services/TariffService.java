package com.evgateway.cpohubserver.services;

import java.util.List;
import java.util.Map;

import com.evgateway.cpohubserver.model.CPOTariffModel;
import com.evgateway.cpohubserver.request.PageResult;

public interface TariffService {

	PageResult<CPOTariffModel> getTariffTableData(int pagesize, int page, Map<String, List<String>> filters);

	CPOTariffModel getTariffById(String id);

	

}