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
import com.evgateway.cpohubserver.services.DashboardService;



@RestController // Marks this class as a REST controller
@RequestMapping("/api/dashboard") // Base URL mapping for all endpoints in this controller
public class DashboardController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class); // Logger instance for
																								// logging

	@Autowired
	private DashboardService dashboardService; // Service to handle dashboard-related functionality

	/**
	 * Fetches dashboard reports based on the provided ID, period, and optional
	 * type.
	 * 
	 * @param id     - Unique identifier for the entity (e.g., user or organization)
	 * @param period - Time period for which reports are to be fetched
	 * @param type   - (Optional) Type of report to fetch (e.g., summary, detailed)
	 * @return ResponseEntity containing a list of report data as maps
	 */
	@GetMapping("/{id}/{period}")
	public ResponseEntity<Response<List<Map<String, Object>>>> getDashboardReports(@PathVariable int id,
			@PathVariable int period, @RequestParam(value = "type", required = false) String type) {

		// Log the request details for debugging purposes
		LOGGER.info("DashboardController.getDashboardReports - id [" + id + "] - period [" + period + "] - type ["
				+ type + "]");

		// Fetch the dashboard reports from the service and return as a response
		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(
				dashboardService.getDashboardReports(id, period, type), StatusCodes.SUCCESS, "Success", new Date()));
	}
}
