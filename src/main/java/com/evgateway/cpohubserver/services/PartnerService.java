package com.evgateway.cpohubserver.services;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.evgateway.cpohubserver.exception.UserNotFoundException;
import com.evgateway.cpohubserver.model.CPOEndpointModel;
import com.evgateway.cpohubserver.model.CPOHUBDowntimeModel;
import com.evgateway.cpohubserver.model.CPOHUBPartnerModel;
import com.evgateway.cpohubserver.model.CPOPullRequestModel;
import com.evgateway.cpohubserver.model.CPOSchedulerFrequencyModel;
import com.evgateway.cpohubserver.request.CPODetails;
import com.evgateway.cpohubserver.request.PageResult;
import com.evgateway.cpohubserver.response.CPOSchedularFrequencyResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface PartnerService {

	PageResult<CPOHUBPartnerModel> getPartnerTableData(int pagesize, int page, Map<String, List<String>> filters);

	CPOHUBPartnerModel getPartnerById(String id);

	PageResult<CPOHUBDowntimeModel> getDowntimeByPartnerId(String partnerId, int pagesize, int page,
			Map<String, List<String>> filters);

	List<CPOEndpointModel> getEndPointsByPartnerId(String partnerId);

	String initiate(CPODetails cpoDetails) throws JsonMappingException, JsonProcessingException, UserNotFoundException;

	PageResult<CPOPullRequestModel> cpoinitiate(Map<String, Object> map) throws NumberFormatException, ParseException;

	Object initiateByParterId(String partnerId)
			throws JsonMappingException, JsonProcessingException, UserNotFoundException;

	PageResult<CPOPullRequestModel> getAllPullRequest(String partnerId, int pagesize, int page);

	CPOSchedularFrequencyResponse addCPOSchedularFrequency(CPOSchedulerFrequencyModel cpoSchedularFrequencyModel);

	PageResult<CPOSchedularFrequencyResponse> getSchedularFrequencyTable(String partnerId, int pagesize, int page,
			Map<String, String> filter);

	CPOSchedularFrequencyResponse getCPOSchedulerFrequencyById(String id);

	CPOSchedularFrequencyResponse updateCPOSchedulerFrequency(CPOSchedulerFrequencyModel cpoSchedularFrequencyModel,
			String id);

	CPOSchedularFrequencyResponse deleteCPOSchedulerFrequency(String id);

	void weeklyBasedInitiate(String partyId, String identifier);

	CPOHUBPartnerModel getPartnerByUserId(String id);

//	PageResult<CPOHUBPartnerModel> getPartnerTableData(int pagesize, int page, Map<String, List<String>> filters);
//
////	PageResult<CPOHUBDowntimeModel> getDowntimeByPartnerId(String partnerId, int pagesize, int page,
////			Map<String, String> filters);
//
//	PageResult<CPOHUBDowntimeModel> getDowntimeByPartnerId(String partnerId, int pagesize, int page,
//			Map<String, List<String>> filters);
//
//	CPOHUBPartnerModel getPartnerById(String id);
//
//	CPOHUBDowntimeModel getDowntimeById(String id);
//
//	List<CPOEndpointModel> getEndPointsByPartnerId(String partnerId);
//
//	String initiate(CPODetails cpoDetails) throws JsonMappingException, JsonProcessingException, UserNotFoundException;
//
//	Object initiateByParterId(String partnerId)
//			throws JsonMappingException, JsonProcessingException, UserNotFoundException;
//
//	void weeklyBasedInitiate(String partyId, String identifier);
//
//	String getCpoPullFrequency(String partyId, String identifier, String reqId, String range);
//
//	PageResult<CPOPullRequestModel> getAllPullRequest(String partnerId, int pagesize, int page);
//
//	PageResult<CPOPullRequestModel> cpoinitiate(Map<String, Object> map) throws NumberFormatException, ParseException;
//
//	CPOSchedularFrequencyResponse addCPOSchedularFrequency(CPOSchedulerFrequencyModel cpoSchedulerFrequencyModel);
//
//	CPOSchedularFrequencyResponse updateCPOSchedulerFrequency(CPOSchedulerFrequencyModel cpoSchedulerFrequencyModel,
//			String id);
//
//	CPOSchedularFrequencyResponse getCPOSchedulerFrequencyById(String id);
//
//	CPOSchedularFrequencyResponse deleteCPOSchedulerFrequency(String id);
//
//	PageResult<CPOSchedularFrequencyResponse> getSchedularFrequencyTable(String partnerId, int pagesize, int page,
//			Map<String, String> filter);

}
