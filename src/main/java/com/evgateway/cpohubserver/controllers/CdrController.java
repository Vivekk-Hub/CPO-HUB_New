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
import com.evgateway.cpohubserver.model.CPOCdrModel;
import com.evgateway.cpohubserver.request.PageResult;
import com.evgateway.cpohubserver.services.CdrService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController // Marks this class as a REST controller
@RequestMapping("/api/cdr") // Base URL mapping for all endpoints in this controller
public class CdrController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CdrController.class); // Logger instance for logging

	@Autowired
	private CdrService cdrService; // Service to manage CDR (Charge Detail Record) related operations

	/**
	 * Fetches a paginated list of CDR data with optional filters.
	 * 
	 * @param filtersJson - JSON string containing filters (optional)
	 * @param page        - Current page number for pagination (default: 0)
	 * @param pagesize    - Number of records per page (default: 10)
	 * @return ResponseEntity containing paginated CDR data
	 * @throws Exception - Handles potential exceptions during data retrieval
	 */
	@GetMapping
	public ResponseEntity<Response<PageResult<CPOCdrModel>>> getCDRTableData(
			@RequestParam(value = "filters", required = false) String filtersJson,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pagesize)
			throws Exception {

		// Parse the filters JSON string into a Map, if provided
		Map<String, List<String>> filters = (filtersJson != null)
				? new ObjectMapper().readValue(filtersJson, new TypeReference<Map<String, List<String>>>() {
				})
				: null;
		// Log the request details for debugging
		LOGGER.info("CdrController.getCDRTableData - pagesize [" + pagesize + "] page - [" + page + "] - filter ["
				+ filters + "]");

		// Fetch the paginated CDR data and return as a response
		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(
				cdrService.getCDRTableData(pagesize, page, filters), StatusCodes.SUCCESS, "Success", new Date()));
	}
	
	@GetMapping("/data")
	public ResponseEntity<Response<Object>> getCDRTableDataNew(
			@RequestParam(value = "filters", required = false) String filtersJson,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pagesize)
			throws Exception {

		// Parse the filters JSON string into a Map, if provided
		Map<String, List<String>> filters = (filtersJson != null)
				? new ObjectMapper().readValue(filtersJson, new TypeReference<Map<String, List<String>>>() {
				})
				: null;
		// Log the request details for debugging
		LOGGER.info("CdrController.getCDRTableData - pagesize [" + pagesize + "] page - [" + page + "] - filter ["
				+ filters + "]");

		// Fetch the paginated CDR data and return as a response
		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(
				cdrService.getCDRTableData(pagesize, page, filters), StatusCodes.SUCCESS, "Success", new Date()));
	}

	/**
	 * Fetches the details of a specific CDR by its ID.
	 * 
	 * @param id - Unique identifier for the CDR
	 * @return ResponseEntity containing the details of the requested CDR
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Response<Map<String, Object>>> getCdrById(@PathVariable String id) {

		// Log the request to fetch CDR by ID for debugging
		LOGGER.info("CdrController.getCdrById - id [" + id + "]");

		// Fetch the CDR details by ID and return as a response
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(cdrService.getCdrById(id), StatusCodes.SUCCESS, "Success", new Date()));
	}
}
