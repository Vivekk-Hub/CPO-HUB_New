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
import com.evgateway.cpohubserver.model.CPOSessionsActivityModel;
import com.evgateway.cpohubserver.request.PageResult;
import com.evgateway.cpohubserver.services.SessionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private SessionService sessionService;

	@GetMapping()
	public ResponseEntity<Response<PageResult<CPOSessionsActivityModel>>> getUserSession(
			@RequestParam(value = "filters", required = false) String filtersJson,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pagesize)
			throws JsonMappingException, JsonProcessingException {
		Map<String, List<String>> filters = (filtersJson != null)
				? new ObjectMapper().readValue(filtersJson, new TypeReference<Map<String, List<String>>>() {
				})
				: null;

		LOGGER.info("SessionController.getUserSession - pagesize [" + pagesize + "] page - [" + page + "] - filter ["
				+ filters + "]");
		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(
				sessionService.getUserSession(pagesize, page, filters), StatusCodes.SUCCESS, "Success", new Date()));

	}

	@GetMapping("/{sessionId}")
	public ResponseEntity<Response<CPOSessionsActivityModel>> getSessionActivityById(@PathVariable String sessionId) {

		LOGGER.info("EmspRequestController.getSessionActivityById - sessionId [" + sessionId + "]");
		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(
				sessionService.getSessionActivityById(sessionId), StatusCodes.SUCCESS, "Success", new Date()));

	}

}
