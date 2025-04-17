package com.evgateway.cpohubserver.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evgateway.cpohubserver.common.Response;
import com.evgateway.cpohubserver.common.StatusCodes;
import com.evgateway.cpohubserver.model.CPOLocationModel;
import com.evgateway.cpohubserver.request.PageResult;
import com.evgateway.cpohubserver.services.LocationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController // Marks this class as a REST controller for handling HTTP requests
@RequestMapping("/api/location") // Base URL mapping for all endpoints in this controller
public class LocationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class); // Logger instance for
																							// logging activities

	@Autowired
	private LocationService locationService; // Service layer for handling location-related business logic

	/**
	 * Fetches table data with pagination and optional filtering.
	 *
	 * @param pagesize - The number of records per page (default: 0)
	 * @param page     - The page number for pagination (default: 0)
	 * @param filter   - Filter criteria for the data (default: null)
	 * @param request  - The HttpServletRequest object
	 * @return ResponseEntity containing a paginated list of data
	 * @throws Exception - Handles any exceptions that occur during processing
	 */
//	@RequestMapping(value = "/table", method = RequestMethod.GET)
//	public ResponseEntity<Response<PageResult<Map<String, Object>>>> reportdata(
//			@RequestParam(required = false, defaultValue = "0") int pagesize,
//			@RequestParam(required = false, defaultValue = "0") int page,
//			@RequestParam(required = false, defaultValue = "null") String filter, HttpServletRequest request)
//			throws Exception {
//
//		LOGGER.info("LocationController.reportdata - pagesize [" + pagesize + "] page - [" + page + "] - filter ["
//				+ filter + "]");
//
//		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(
//				locationService.getTableData(pagesize, page, filter), StatusCodes.SUCCESS, "Success", new Date()));
//	}
//	
	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public ResponseEntity<Response<PageResult<Map<String, Object>>>> getTableData(
			@RequestParam(required = false, defaultValue = "10") int pagesize,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(value = "filters", required = false) String filtersJson, HttpServletRequest request)
			throws Exception {

		LOGGER.info("LocationController.getTableData - pagesize [" + pagesize + "] page [" + page + "] filters ["
				+ filtersJson + "]");

		// Parse the filters JSON into a Map if not null
		Map<String, List<String>> filters = (filtersJson != null)
				? new ObjectMapper().readValue(filtersJson, new TypeReference<Map<String, List<String>>>() {
				})
				: null;
		// Call the service method and return the result
		PageResult<Map<String, Object>> tableData = locationService.getTableData(pagesize, page, filters);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(tableData, StatusCodes.SUCCESS, "Success", new Date()));
	}

	/**
	 * Fetches data for displaying on a map.
	 *
	 * @return ResponseEntity containing a list of map data
	 * @throws Exception - Handles any exceptions that occur during processing
	 */
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Map>>> getMapData() throws Exception {

		LOGGER.info("LocationController.getMapData");
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(locationService.getMapData(), StatusCodes.SUCCESS, "Success", new Date()));
	}

	/**
	 * Fetches location details by ID.
	 *
	 * @param id - The ID of the location
	 * @return ResponseEntity containing location details
	 * @throws Exception - Handles any exceptions that occur during processing
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ResponseEntity<Response<Object>> locationDetails(@RequestParam(required = true) String evseId) throws Exception {

		LOGGER.info("LocationController.locationDetails.info - id [" + evseId + "]");

		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(locationService.locationDetails(evseId), StatusCodes.SUCCESS, "Success", new Date()));
	}

	/**
	 * Fetches location details by unique ID.
	 *
	 * @param id - The unique ID of the location
	 * @return ResponseEntity containing location details
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Response<CPOLocationModel>> getLocationByUid(@PathVariable String id) {
		LOGGER.info("LocationController.getLocationByUid - id [" + id + "]");
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(locationService.getLocationByUid(id), StatusCodes.SUCCESS, "Success", new Date()));
	}

	/**
	 * Fetches count information related to locations.
	 *
	 * @return ResponseEntity containing a list of count-related information
	 * @throws Exception - Handles any exceptions that occur during processing
	 */
	@GetMapping()
	public ResponseEntity<Response<List<Map<String, Object>>>> getCountInformation() throws Exception {
		LOGGER.info("LocationController.getCountInformation -");
		return ResponseEntity.status(HttpStatus.OK).body(
				new Response<>(locationService.getCountInformation(), StatusCodes.SUCCESS, "Success", new Date()));
	}
}
