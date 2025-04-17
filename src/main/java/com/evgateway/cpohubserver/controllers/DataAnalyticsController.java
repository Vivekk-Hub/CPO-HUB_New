package com.evgateway.cpohubserver.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evgateway.cpohubserver.common.Response;
import com.evgateway.cpohubserver.common.StatusCodes;
import com.evgateway.cpohubserver.model.CPOCdrModel;
import com.evgateway.cpohubserver.request.PageResult;
import com.evgateway.cpohubserver.services.DataAnalyticsService;

@RestController // Marks this class as a REST controller
@RequestMapping("/api/dataAnalytics") // Base URL mapping for all endpoints in this controller
public class DataAnalyticsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CdrController.class); // Logger instance for logging

	@Autowired
	private DataAnalyticsService dataAnalyticsService; // Service to handle data analytics-related functionality

	/**
	 * Fetches table data based on provided filters, page, and page size.
	 * 
	 * @param filters  - A map containing filter criteria
	 * @param page     - The page number for pagination (default: 0)
	 * @param pagesize - The number of records per page (default: 10)
	 * @return ResponseEntity containing the table data in a paginated format
	 * @throws Exception - Handles any exceptions that occur during processing
	 */
	@SuppressWarnings("unchecked")
	@PostMapping
	public ResponseEntity<Response<PageResult<CPOCdrModel>>> fetchTableData(@RequestBody Map filters,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pagesize)
			throws Exception {

		LOGGER.info("DataAnalyticsController.fetchTableData - pagesize [" + pagesize + "] page - [" + page
				+ "] - filter [" + filters + "]");

		// Fetch table data from the service and return it in the response
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(dataAnalyticsService.getTableData(pagesize, page, filters), StatusCodes.SUCCESS,
						"Success", new Date()));
	}

	/**
	 * Exports data as a downloadable report file based on provided filters.
	 * 
	 * @param filters - A map containing filter criteria
	 * @return ResponseEntity containing the report file as a byte array
	 * @throws Exception - Handles any exceptions that occur during processing
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/downloadReport")
	public ResponseEntity<byte[]> exportData(@RequestBody Map filters) throws Exception {

		LOGGER.info("DataAnalyticsController.exportData - filter [" + filters + "]");

		// Generate the file as a byte array
		byte[] fileData = dataAnalyticsService.exportData(filters);

		// Prepare response headers for file download
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", "report.xlsx");
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		// Return the file as the response body
		return ResponseEntity.ok().headers(headers).body(fileData);
	}

	/**
	 * Exports data as a downloadable report file using filters passed via query
	 * parameters.
	 * 
	 * @param filtersJson - JSON string containing filter criteria
	 * @return ResponseEntity containing the report file as a byte array
	 * @throws Exception - Handles any exceptions that occur during processing
	 */
//	@GetMapping("/downloadReport")
//	public ResponseEntity<byte[]> exportDataa(@RequestParam(value = "filters", required = false) String filtersJson)
//			throws Exception {
//
//		// Parse the filters JSON string into a map
//		Map<String, String> filters = (filtersJson != null) ? new ObjectMapper().readValue(filtersJson, Map.class)
//				: null;
//
//		LOGGER.info("DataAnalyticsController.exportDataa - filter [" + filters + "]");
//
//		// Generate the file as a byte array
//		byte[] fileData = dataAnalyticsService.exportData(filters);
//
//		// Prepare response headers for file download
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentDispositionFormData("attachment", "report.xlsx");
//		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//
//		// Return the file as the response body
//		return ResponseEntity.ok().headers(headers).body(fileData);
//	}

	/**
	 * Fetches role information based on the provided role name.
	 * 
	 * @param role - Name of the role for which information is to be fetched
	 * @return ResponseEntity containing a list of role information as maps
	 */
	@GetMapping("/roleInfo/{role}")
	public ResponseEntity<Response<List<Map<String, Object>>>> getRoleInfo(@PathVariable String role) {

		LOGGER.info("DataAnalyticsController.getRoleInfo - role [" + role + "]");

		// Fetch role information from the service and return it in the response
		return ResponseEntity.status(HttpStatus.OK).body(
				new Response<>(dataAnalyticsService.getRoleInfo(role), StatusCodes.SUCCESS, "Success", new Date()));
	}

}
