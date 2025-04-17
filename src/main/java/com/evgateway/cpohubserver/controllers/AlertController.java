package com.evgateway.cpohubserver.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evgateway.cpohubserver.common.Response;
import com.evgateway.cpohubserver.common.StatusCodes;
import com.evgateway.cpohubserver.model.CPOHUBAlertModel;
import com.evgateway.cpohubserver.model.CPOHUBApiLogModel;
import com.evgateway.cpohubserver.request.PageResult;
import com.evgateway.cpohubserver.services.AlertService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class AlertController {
	
	// Import necessary packages and classes
	private static final Logger LOGGER = LoggerFactory.getLogger(AlertController.class); // Logger instance for logging
																							// information

	@Autowired
	private AlertService alertService; // Autowired AlertService to handle business logic for alerts

	/**
	 * Handles GET requests to fetch paginated alert table data.
	 * 
	 * @param filtersJson - JSON string containing filter parameters (optional)
	 * @param page        - The page number for pagination (default = 0)
	 * @param pagesize    - Number of items per page for pagination (default = 10)
	 * @return ResponseEntity containing a response with the paginated alert data
	 * @throws Exception - Handles potential exceptions during filter parsing
	 */
	@GetMapping("/alert")
	public ResponseEntity<Response<PageResult<CPOHUBAlertModel>>> getAlertTableData(
			@RequestParam(value = "filters", required = false) String filtersJson,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pagesize)
			throws Exception {

		// Parse the filters JSON into a Map if not null
		Map<String, List<String>> filters = (filtersJson != null)
				? new ObjectMapper().readValue(filtersJson, new TypeReference<Map<String, List<String>>>() {
				})
				: null;
		// Log the details of the request for debugging and tracking purposes
		LOGGER.info("AlertController.getAlertTableData - pagesize [" + pagesize + "] page - [" + page + "] - filter ["
				+ filters + "]");

		// Fetch the alert table data from the alertService and return it in the
		// response
		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(
				alertService.getAlertTableData(pagesize, page, filters), StatusCodes.SUCCESS, "Success", new Date()));
	}

	/**
	 * Handles GET requests to fetch a specific alert by its ID.
	 * 
	 * @param id - The unique identifier of the alert
	 * @return ResponseEntity containing the response with the alert details
	 */
	@GetMapping("/alert/{id}")
	public ResponseEntity<Response<CPOHUBAlertModel>> getAlertById(@PathVariable String id) {
		// Log the request with the alert ID for tracking
		LOGGER.info("AlertController.getAlertById - id [" + id + "]");

		// Fetch the alert details by ID from the alertService and return it in the
		// response
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(alertService.getAlertById(id), StatusCodes.SUCCESS, "Success", new Date()));
	}

	/**
	 * Handles GET requests to fetch paginated log table data.
	 * 
	 * @param filtersJson - JSON string containing filter parameters (optional)
	 * @param page        - The page number for pagination (default = 0)
	 * @param pagesize    - Number of items per page for pagination (default = 10)
	 * @return ResponseEntity containing a response with the paginated log data
	 * @throws Exception - Handles potential exceptions during filter parsing
	 */
	@GetMapping("/logs")
	public ResponseEntity<Response<PageResult<CPOHUBApiLogModel>>> getLogTableData(
			@RequestParam(value = "filters", required = false) String filtersJson,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pagesize)
			throws Exception {

		// Parse the filters JSON into a Map if not null
		Map<String, List<String>> filters = (filtersJson != null)
				? new ObjectMapper().readValue(filtersJson, new TypeReference<Map<String, List<String>>>() {
				})
				: null;

		// Log the details of the request for debugging and tracking purposes
		LOGGER.info("AlertController.getLogTableData - pagesize [" + pagesize + "] page - [" + page + "] - filter ["
				+ filters + "]");

		// Fetch the log table data from the alertService and return it in the response
		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(
				alertService.getLogTableData(pagesize, page, filters), StatusCodes.SUCCESS, "Success", new Date()));
	}

	/**
	 * Handles GET requests to fetch a specific log by its ID.
	 * 
	 * @param id - The unique identifier of the log
	 * @return ResponseEntity containing the response with the log details
	 */
	@GetMapping("/logs/{id}")
	public ResponseEntity<Response<CPOHUBApiLogModel>> getLogsById(@PathVariable String id) {
		// Log the request with the log ID for tracking
		LOGGER.info("AlertController.getLogsById - id [" + id + "]");

		// Fetch the log details by ID from the alertService and return it in the
		// response
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(alertService.getLogsById(id), StatusCodes.SUCCESS, "Success", new Date()));
	}
}
