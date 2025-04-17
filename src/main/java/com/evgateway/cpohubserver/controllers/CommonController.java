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
import org.springframework.web.bind.annotation.RestController;

import com.evgateway.cpohubserver.common.Response;
import com.evgateway.cpohubserver.common.StatusCodes;
import com.evgateway.cpohubserver.services.CommonService;

@RestController // Marks this class as a REST controller
@RequestMapping("/api/common") // Base URL mapping for all endpoints in this controller
public class CommonController {

	@Autowired
	private CommonService commonService; // Service to handle common functionality

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class); // Logger instance for logging

	/**
	 * Fetches party details based on the specified role.
	 * 
	 * @param role - Role of the party (e.g., admin, user, etc.)
	 * @return ResponseEntity containing a list of party details as maps
	 */
	@GetMapping("/partners/{role}")
	public ResponseEntity<Response<List<Map<String, Object>>>> getPartyDetails(@PathVariable String role) {

		// Log the request to fetch party details for debugging purposes
		LOGGER.info("CommonController.getPartyDetails - role [" + role + "] ");

		// Fetch party details from the service and return as a response
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(commonService.getPartyDetails(role), StatusCodes.SUCCESS, "Success", new Date()));
	}
}
