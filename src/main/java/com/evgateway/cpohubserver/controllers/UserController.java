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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evgateway.cpohubserver.common.Response;
import com.evgateway.cpohubserver.common.StatusCodes;
import com.evgateway.cpohubserver.model.CPOHUBEMSPPermission;
import com.evgateway.cpohubserver.model.User;
import com.evgateway.cpohubserver.model.UserSession;
import com.evgateway.cpohubserver.request.PageResult;
import com.evgateway.cpohubserver.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

//	@GetMapping
//	public ResponseEntity<Response<PageResult<User>>> getUsers(
//			@RequestParam(value = "filters", required = false) String filtersJson,
//			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pagesize)
//			throws JsonMappingException, JsonProcessingException {
//
//		Map<String, String> filters = (filtersJson != null) ? new ObjectMapper().readValue(filtersJson, Map.class)
//				: null;
//
//		LOGGER.info("UserController.getUsers - pagesize [" + pagesize + "] page - [" + page + "] - filter [" + filters
//				+ "]");
//		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(
//				userService.getTableData(pagesize, page, filters), StatusCodes.SUCCESS, "Success", new Date()));
//
//	}

	@GetMapping
	public ResponseEntity<Response<PageResult<User>>> getUsers(
			@RequestParam(value = "filters", required = false) String filtersJson,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pagesize)
			throws JsonMappingException, JsonProcessingException {

		Map<String, List<String>> filters = (filtersJson != null)
				? new ObjectMapper().readValue(filtersJson, new TypeReference<Map<String, List<String>>>() {
				})
				: null;

		LOGGER.info("UserController.getUsers - pagesize [" + pagesize + "] page - [" + page + "] - filter [" + filters
				+ "]");
		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(
				userService.getTableData(pagesize, page, filters), StatusCodes.SUCCESS, "Success", new Date()));

	}

	@PostMapping
	public ResponseEntity<Response<User>> addUser(@Valid @RequestBody User userDTO) {

		LOGGER.info("UserController.addUser - user [" + userDTO + "]");

		User user = userService.addUser(userDTO);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new Response<>(user, StatusCodes.SUCCESS, "User created successfully", new Date()));

	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<User>> getUserById(@PathVariable String id) {
		LOGGER.info("UserController.getUserById - id [" + id + "]");
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(userService.getUserById(id), StatusCodes.SUCCESS, "Success", new Date()));
	}

	// Update user by ID
	@PutMapping("/{id}")
	public ResponseEntity<Response<User>> updateUserById(@PathVariable String id,
			@Valid @RequestBody User updatedUser) {
		LOGGER.info("UserController.updateUserById - id [" + id + "]");
		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(userService.updateUserById(id, updatedUser),
				StatusCodes.SUCCESS, "Success", new Date()));
	}

//	// Update current user by ID
//		@PutMapping("currentUser/{id}")
//		public ResponseEntity<Response<User>> updateCurrentUserById(@PathVariable String id,
//				@Valid @RequestBody Map<String, Object> updatedUser) {
//			LOGGER.info("UserController.updateCurrentUserById - id [" + id + "]");
//			return ResponseEntity.status(HttpStatus.OK).body(new Response<>(userService.updateCurrentUserById(id, updatedUser),
//					StatusCodes.SUCCESS, "Success", new Date()));
//		}

	// Update current user by ID
	@PutMapping("currentUser/{id}")
	public ResponseEntity<Response<User>> updateCurrentUserById(@PathVariable String id,
			@Valid @RequestBody User updatedUser) {
		LOGGER.info("UserController.updateCurrentUserById - id [" + id + "]");
		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(
				userService.updateCurrentUserById(id, updatedUser), StatusCodes.SUCCESS, "Success", new Date()));
	}

	@GetMapping("/usersession/{id}")
	public ResponseEntity<Response<UserSession>> getUserSessionById(@PathVariable String id) {

		LOGGER.info("UserController.getUserSessionById - id [" + id + "]");
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(userService.getUserSessionById(id), StatusCodes.SUCCESS, "Success", new Date()));

	}

	@GetMapping("/usersessions/{userId}")
	public ResponseEntity<Response<PageResult<UserSession>>> getUserSessionByUserId(
			@RequestParam(value = "filters", required = false) String filtersJson,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pagesize,
			@PathVariable String userId) throws JsonMappingException, JsonProcessingException {

		Map<String, String> filters = (filtersJson != null) ? new ObjectMapper().readValue(filtersJson, Map.class)
				: null;
		LOGGER.info("UserController.getUserSessionByUserId - userId [" + userId + "]- pagesize [" + pagesize
				+ "] page - [" + page + "] - filter [" + filters + "]");
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(userService.getUserSessionByUserId(pagesize, page, filters, userId),
						StatusCodes.SUCCESS, "Success", new Date()));

	}

	@GetMapping("/{party_id}/{country_code}")
	public ResponseEntity<Response<PageResult<User>>> getUserByPartyIdAndCountryCode(
			@RequestParam(value = "filters", required = false) String filtersJson,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pagesize,
			@PathVariable String party_id, @PathVariable String country_code)
			throws JsonMappingException, JsonProcessingException {

		Map<String, String> filters = (filtersJson != null) ? new ObjectMapper().readValue(filtersJson, Map.class)
				: null;
		LOGGER.info("UserController.getUserByPartyIdAndCountryCode - party_id [" + party_id + "]-  country_code ["
				+ country_code + "]- pagesize [" + pagesize + "] page - [" + page + "] - filter [" + filters + "]");
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(
						userService.getUserByPartyIdAndCountryCode(pagesize, page, filters, party_id, country_code),
						StatusCodes.SUCCESS, "Success", new Date()));

	}

	// To Get ALL Permission
	@GetMapping("/permission/{role}/{partyId}")
	public ResponseEntity<Response<Object>> getAllPermissionBasedOnPartyId(@PathVariable String role,
			@PathVariable String partyId) throws Exception {

		LOGGER.info(
				"UserController.getAllEMSPPermissionBasedOnPartyId - role [" + role + "] - partyId [" + partyId + "]");
		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(
				userService.getAllPermissionBasedOnPartyId(role, partyId), StatusCodes.SUCCESS, "Success", new Date()));

	}

	// Update EMSP Permission
	@PutMapping("/emspPermission/{emspPartyId}")
	public ResponseEntity<Response<Object>> updateAndSaveEMSPPermissionBasedOnPartyId(@PathVariable String emspPartyId,
			@RequestBody CPOHUBEMSPPermission emspPermission) throws Exception {

		LOGGER.info("UserController.updateAndSaveEMSPPermissionBasedOnPartyId - emspPartyId [" + emspPartyId + "]");
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(userService.updateAndSaveEMSPPermissionBasedOnPartyId(emspPartyId, emspPermission),
						StatusCodes.SUCCESS, "Success", new Date()));

	}

}
