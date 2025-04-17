package com.evgateway.cpohubserver.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evgateway.cpohubserver.common.Response;
import com.evgateway.cpohubserver.common.StatusCodes;
import com.evgateway.cpohubserver.model.CPOEndpointModel;
import com.evgateway.cpohubserver.model.CPOHUBDowntimeModel;
import com.evgateway.cpohubserver.model.CPOHUBPartnerModel;
import com.evgateway.cpohubserver.model.CPOPullRequestModel;
import com.evgateway.cpohubserver.model.CPOSchedulerFrequencyModel;
import com.evgateway.cpohubserver.request.CPODetails;
import com.evgateway.cpohubserver.request.PageResult;
import com.evgateway.cpohubserver.response.CPOSchedularFrequencyResponse;
import com.evgateway.cpohubserver.services.PartnerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/partner")
public class PartnerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PartnerController.class);

	@Autowired
	private PartnerService partnerService;

	@GetMapping
	public ResponseEntity<Response<PageResult<CPOHUBPartnerModel>>> getPartnerTableData(
			@RequestParam(value = "filters", required = false) String filtersJson,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pagesize)
			throws Exception {

		Map<String, List<String>> filters = (filtersJson != null)
				? new ObjectMapper().readValue(filtersJson, new TypeReference<Map<String, List<String>>>() {
				})
				: null;
		LOGGER.info("PartnerController.getPartnerTableData - pagesize [" + pagesize + "] page - [" + page
				+ "] - filter [" + filters + "]");

		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(partnerService.getPartnerTableData(pagesize, page, filters), StatusCodes.SUCCESS,
						"Success", new Date()));

	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<CPOHUBPartnerModel>> getPartnerById(@PathVariable String id) {
		LOGGER.info("PartnerController.getPartnerById - id [" + id + "]");
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(partnerService.getPartnerById(id), StatusCodes.SUCCESS, "Success", new Date()));
	}
	
	
	@GetMapping("partnerdetails/{id}")
	public ResponseEntity<Response<CPOHUBPartnerModel>> getPartnerByUserId(@PathVariable String id) {
		LOGGER.info("PartnerController.getPartnerByUserId - id [" + id + "]");
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(partnerService.getPartnerByUserId(id), StatusCodes.SUCCESS, "Success", new Date()));
	}

	

	@GetMapping("downtimes/{partnerId}")
	public ResponseEntity<Response<PageResult<CPOHUBDowntimeModel>>> getDowntimeByPartnerId(
			@RequestParam(value = "filters", required = false) String filtersJson,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pagesize,
			@PathVariable String partnerId) throws Exception {

//		Map<String, String> filter = (filtersJson != null) ? new ObjectMapper().readValue(filtersJson, Map.class)
//				: null;

		Map<String, List<String>> filters = null;
		try {
			if (filtersJson != null) {
				filters = new ObjectMapper().readValue(filtersJson, new TypeReference<Map<String, List<String>>>() {
				});
			}
		} catch (JsonProcessingException e) {
			LOGGER.error("Error parsing filtersJson: " + filtersJson, e);
			return ResponseEntity.badRequest()
					.body(new Response<>(null, StatusCodes.BADREQUEST, "Invalid filter format", new Date()));
		}

		LOGGER.info("Fetching downtime data for Partner ID [{}], page [{}], pagesize [{}], filters [{}]", partnerId,
				page, pagesize, filters);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(partnerService.getDowntimeByPartnerId(partnerId, pagesize, page, filters),
						StatusCodes.SUCCESS, "Success", new Date()));

//		Map<String, List<String>> filters = (filtersJson != null)
//				? new ObjectMapper().readValue(filtersJson, new TypeReference<Map<String, List<String>>>() {
//				})
//				: null;
//
//		LOGGER.info("PartnerController.getDowntimeByPartnerId_Table Data - pagesize [" + pagesize + "] page - [" + page
//				+ "] - filter [" + filters + "] - partnerId [" + partnerId + "]");
//
//		return ResponseEntity.status(HttpStatus.OK)
//				.body(new Response<>(partnerService.getDowntimeByPartnerId(partnerId, pagesize, page, filters),
//						StatusCodes.SUCCESS, "Success", new Date()));

	}

//	@GetMapping("downtime/{id}")
//	public ResponseEntity<Response<CPOHUBDowntimeModel>> getDowntimeById(@PathVariable String id) {
//		LOGGER.info("PartnerController.getDowntimeById - id [" + id + "]");
//		return ResponseEntity.status(HttpStatus.OK)
//				.body(new Response<>(partnerService.getDowntimeById(id), StatusCodes.SUCCESS, "Success", new Date()));
//	}

	@GetMapping("endpoints/{partnerId}")
	public ResponseEntity<Response<List<CPOEndpointModel>>> getEndPointsByPartnerId(@PathVariable String partnerId)
			throws Exception {

		LOGGER.info("PartnerController.getEndPointsBtPartnerId() - partnerId  [" + partnerId + "]");

		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(
				partnerService.getEndPointsByPartnerId(partnerId), StatusCodes.SUCCESS, "Success", new Date()));

	}

	@PostMapping("initiate")
	public ResponseEntity<Response<?>> initiate(@RequestBody CPODetails cpoDetails) throws Exception {

		LOGGER.info("PartnerController.initiate() - map  [" + cpoDetails + "]");

		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(partnerService.initiate(cpoDetails), StatusCodes.SUCCESS, "Success", new Date()));

	}

	@PostMapping("weeklyBasedInitiate/{partyId}/{identifier}")
	public void weeklyBasedInitiate(@PathVariable String partyId, @PathVariable String identifier) throws Exception {

		LOGGER.info("PartnerController.weeklyBasedInitiate() - partyId  [" + partyId + "] - identifier - [" + identifier
				+ "]");

		partnerService.weeklyBasedInitiate(partyId, identifier);

	}

	@PostMapping("/cpoinitiate")
	public ResponseEntity<Response<PageResult<CPOPullRequestModel>>> getCpoPullFrequency(
			@RequestBody Map<String, Object> map) throws Exception {

		LOGGER.info(
				"PartnerController.getCpoPullFrequency() - Initiating process for partyId [{}],reqId [{}] ,range [{}] and identifier [{}]",
				map.toString());

		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(partnerService.cpoinitiate(map), StatusCodes.SUCCESS, "Success", new Date()));

	}

	@GetMapping("updatedData/{partnerId}")
	public ResponseEntity<Response<?>> refreshedData(@PathVariable String partnerId) throws Exception {

		LOGGER.info("PartnerController.refreshedData() - partnerId  [" + partnerId + "]");

		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(partnerService.initiateByParterId(partnerId),
				StatusCodes.SUCCESS, "Success", new Date()));

	}

	// After the update API we need to work on that.

//	@PostMapping("refreshData")
//	public ResponseEntity<Response<?>> initiate(@PathVariable party_id, @RequestBody Modules) throws Exception {
//
//		LOGGER.info("PartnerController.initiate() - map  [" + cpoDetails + "]");
//
//		return ResponseEntity.status(HttpStatus.OK)
//				.body(new Response<>(partnerService.initiate(cpoDetails), StatusCodes.SUCCESS, "Success", new Date()));
//
//	}
	@GetMapping("getAllPullRequest")
	public ResponseEntity<Response<PageResult<CPOPullRequestModel>>> getAllPullRequest(@RequestParam String partnerId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pagesize)
			throws Exception {

		LOGGER.info("PartnerController.getAllPullRequest() - partnerId  [" + partnerId + "]");

		return ResponseEntity.status(HttpStatus.OK)

				.body(new Response<>(partnerService.getAllPullRequest(partnerId, pagesize, page),

						StatusCodes.SUCCESS, "Success", new Date()));

	}

	@PostMapping("cposcheduler")
	public ResponseEntity<Response<CPOSchedularFrequencyResponse>> addCPOSchedularFrequency(
			@RequestBody CPOSchedulerFrequencyModel cpoSchedularFrequencyModel) throws Exception {

		LOGGER.info("PartnerController.addCPOSchedularFrequency() - [" + cpoSchedularFrequencyModel + "]");

		CPOSchedularFrequencyResponse addCPOSchedularFrequency = partnerService
				.addCPOSchedularFrequency(cpoSchedularFrequencyModel);
		if (addCPOSchedularFrequency != null) {

			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response<>(addCPOSchedularFrequency, StatusCodes.SUCCESS, "Success", new Date()));
		} else {
			System.err.println("addCPOSchedularFrequency in controller inside else          "+addCPOSchedularFrequency);

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response<>(null, StatusCodes.SUCCESS, "Identifier Already Exist", new Date()));
		}

	}

	@GetMapping("cposcheduler")
	public ResponseEntity<Response<PageResult<CPOSchedularFrequencyResponse>>> getSchedularFrequencyTable(
			@RequestParam String partnerId, @RequestParam(value = "filters", required = false) String filtersJson,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pagesize)
			throws Exception {

		Map<String, String> filter = (filtersJson != null) ? new ObjectMapper().readValue(filtersJson, Map.class)
				: null;

		LOGGER.info("PartnerController.getSchedularFrequencyTable - pagesize [" + pagesize + "] page - [" + page
				+ "] - filter [" + filter + "]");

		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(partnerService.getSchedularFrequencyTable(partnerId, pagesize, page, filter),
						StatusCodes.SUCCESS, "Success", new Date()));

	}

	@GetMapping("cposcheduler/{id}")
	public ResponseEntity<Response<CPOSchedularFrequencyResponse>> getCPOSchedularFrequencyById(
			@PathVariable String id) {

		LOGGER.info("PartnerController.getCPOSchedularFrequencyById() - id  [" + id + "]");

		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(partnerService.getCPOSchedulerFrequencyById(id),
				StatusCodes.SUCCESS, "Success", new Date()));

	}

	@PutMapping("cposcheduler/{id}")
	public ResponseEntity<Response<CPOSchedularFrequencyResponse>> updateCPOSchedularFrequency(
			@RequestBody CPOSchedulerFrequencyModel cpoSchedularFrequencyModel, @PathVariable String id) {

		LOGGER.info("PartnerController.updateCPOSchedularFrequency() -cpoSchedularFrequencyModel ["
				+ cpoSchedularFrequencyModel + "]- id  [" + id + "]");

		CPOSchedularFrequencyResponse updateCPOSchedulerFrequency = partnerService
				.updateCPOSchedulerFrequency(cpoSchedularFrequencyModel, id);

		if (updateCPOSchedulerFrequency != null) {

			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response<>(updateCPOSchedulerFrequency, StatusCodes.SUCCESS, "Success", new Date()));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response<>(null, StatusCodes.SUCCESS, "Identifier Already Exist", new Date()));
		}

	}

	@DeleteMapping("cposcheduler/{id}")
	public ResponseEntity<Response<CPOSchedularFrequencyResponse>> deleteCPOSchedulerFrequency(
			@PathVariable String id) {

		LOGGER.info("PartnerController.deleteCPOSchedulerFrequency() - id  [" + id + "]");

		CPOSchedularFrequencyResponse deleteCPOSchedulerFrequency = partnerService.deleteCPOSchedulerFrequency(id);
		if (deleteCPOSchedulerFrequency != null) {

			return ResponseEntity.status(HttpStatus.OK).body(
					new Response<>(null, StatusCodes.SUCCESS, "'" + id + "' is Successfully Deleted", new Date()));

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Response<>(null, StatusCodes.SUCCESS, "Invalid Id", new Date()));
		}
	}
}
