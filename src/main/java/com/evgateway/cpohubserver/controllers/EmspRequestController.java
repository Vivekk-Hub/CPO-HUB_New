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
import com.evgateway.cpohubserver.model.CPOHUBRequest;
import com.evgateway.cpohubserver.request.PageResult;
import com.evgateway.cpohubserver.services.EmspRequestService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController // Marks this class as a REST controller to handle HTTP requests
@RequestMapping("/api/emsp") // Base URL mapping for all endpoints in this controller
public class EmspRequestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmspRequestController.class); // Logger instance for
																								// logging activities

	@Autowired
	private EmspRequestService emspRequestService; // Service layer for handling EMSP-related business logic

	/**
	 * Fetches EMSP requests with pagination support.
	 *
	 * @param page     - The page number for pagination (default: 0)
	 * @param pagesize - The number of records per page (default: 0)
	 * @param request  - The HttpServletRequest object
	 * @return ResponseEntity containing a paginated list of EMSP requests
	 * @throws Exception - Handles any exceptions that occur during processing
	 */
	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public ResponseEntity<Response<PageResult<CPOHUBRequest>>> emspRequest(
			@RequestParam(value = "filters", required = false) String filtersJson,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "0") int pagesize, HttpServletRequest request)
			throws Exception {
		Map<String, List<String>> filters = (filtersJson != null)
				? new ObjectMapper().readValue(filtersJson, new TypeReference<Map<String, List<String>>>() {
				})
				: null;
		LOGGER.info("EmspRequestController.emspRequest - pagesize [" + pagesize + "] page - [" + page
				+ "] - filters - [" + filters + "]");

		// Fetch EMSP requests with pagination and return the response
		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(
				emspRequestService.emspRequest(pagesize, page, filters), StatusCodes.SUCCESS, "Success", new Date()));
	}

	/**
	 * Fetches an EMSP request by its ID.
	 *
	 * @param id - The ID of the EMSP request
	 * @return ResponseEntity containing the EMSP request details
	 */
	@GetMapping("/request/{id}")
	public ResponseEntity<Response<CPOHUBRequest>> getEmspRequestById(@PathVariable String id) {

		LOGGER.info("EmspRequestController.getEmspRequestById - id [" + id + "]");

		// Fetch the EMSP request details by ID and return the response
		return ResponseEntity.status(HttpStatus.OK).body(
				new Response<>(emspRequestService.getEmspRequestById(id), StatusCodes.SUCCESS, "Success", new Date()));
	}

	/**
	 * Fetches session activity details by session ID.
	 *
	 * @param id - The ID of the session
	 * @return ResponseEntity containing the session activity details
	 */
//	@GetMapping("sessionActivity/{id}")
//	public ResponseEntity<Response<CPOSessionsActivityModel>> getSessionActivityById(@PathVariable String id) {
//
//		LOGGER.info("EmspRequestController.getSessionActivityById - id [" + id + "]");
//
//		// Fetch session activity details by ID and return the response
//		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(emspRequestService.getSessionActivityById(id),
//				StatusCodes.SUCCESS, "Success", new Date()));
//	}
}
