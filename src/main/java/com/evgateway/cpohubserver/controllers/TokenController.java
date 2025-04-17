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
import com.evgateway.cpohubserver.model.CPOTokenModel;
import com.evgateway.cpohubserver.request.PageResult;
import com.evgateway.cpohubserver.services.TokenService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/token")
public class TokenController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenController.class);

	@Autowired
	private TokenService tokenService;

	@GetMapping
	public ResponseEntity<Response<PageResult<CPOTokenModel>>> getTokenTableData(
			@RequestParam(value = "filters", required = false) String filtersJson,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pagesize)
			throws Exception {

		Map<String, List<String>> filters = (filtersJson != null)
				? new ObjectMapper().readValue(filtersJson, new TypeReference<Map<String, List<String>>>() {
				})
				: null;

		LOGGER.info("TokenController.getTokenTableData - pagesize [" + pagesize + "] page - [" + page + "] - filter ["
				+ filters + "]");

		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(
				tokenService.getTokenTableData(pagesize, page, filters), StatusCodes.SUCCESS, "Success", new Date()));

	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<CPOTokenModel>> getTokenById(@PathVariable String id) {
		LOGGER.info("TokenController.getTokenById - id [" + id + "]");
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(tokenService.getTokenById(id), StatusCodes.SUCCESS, "Success", new Date()));
	}
}
