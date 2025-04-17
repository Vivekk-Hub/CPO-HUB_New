package com.evgateway.cpohubserver.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evgateway.cpohubserver.exception.InvalidCredentialsException;
import com.evgateway.cpohubserver.jwt.JwtUtils;
import com.evgateway.cpohubserver.model.User;
import com.evgateway.cpohubserver.request.LoginRequest;
import com.evgateway.cpohubserver.response.JwtResponse;
import com.evgateway.cpohubserver.response.MessageResponse;
import com.evgateway.cpohubserver.services.UserService;
import com.evgateway.cpohubserver.services.UserSessionService;

import jakarta.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600) // Allow cross-origin requests from any origin with a maximum age of 3600 seconds
@RestController // Marks this class as a REST controller
@RequestMapping("/api/auth") // Base URL mapping for all endpoints in this controller
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager; // Handles authentication process

	@Autowired
	UserService userService; // Service to manage user-related operations

	@Autowired
	private BCryptPasswordEncoder passwordEncoder; // For encoding and validating passwords

	@Autowired
	UserDetailsService userDetailsService; // Service to load user-specific data

	@Autowired
	private UserSessionService userSessionService; // Service to manage user sessions

	@Autowired
	JwtUtils jwtUtils; // Utility class for handling JWT-related operations

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class); // Logger instance for logging

	/**
	 * Handles user login requests.
	 * 
	 * @param authRequest - LoginRequest object containing username and password
	 * @param request - HttpServletRequest for capturing request details
	 * @return ResponseEntity containing JWT response upon successful authentication
	 * @throws Exception - Throws exception for invalid credentials or user not found
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> login(@RequestBody LoginRequest authRequest, HttpServletRequest request) throws Exception {

		// Log the login request for debugging
		logger.info("AuthController.login() - with [" + authRequest.toString() + "]");
		
	

		// Validate username is not null or empty
		if (authRequest.getUsername().trim().isEmpty() || authRequest.getPassword().trim().isEmpty()) {
			throw new UsernameNotFoundException("Username/Password cannot be empty");
		}


		if (authRequest.getUsername().contains(" ")) {
			throw new IllegalArgumentException("Invalid Username");
		}
		// if (authRequest.getUsername().chars().filter(ch -> ch == '@').count() != 1) {
		// 	throw new IllegalArgumentException("Username must contain exactly one '@' symbol");
		// }
		// if (!authRequest.getUsername().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
		// 	throw new IllegalArgumentException("Username format is invalid.");
		// }


		// Find the user by username
		User user = userService.findByUsername(authRequest.getUsername());
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + authRequest.getUsername());
		}

		// Check if the user account is active
		if (!user.getActive()) {
//			throw new UsernameNotFoundException("User account is not active");
			throw new IllegalArgumentException("User account is disabled");
		}

		// Validate the provided password with the stored password
		if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
			throw new InvalidCredentialsException("Invalid username or password");
		}

		// Authenticate the user
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		// Load user details and generate JWT token
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		final String jwt = jwtUtils.generateToken(userDetails.getUsername());
		userSessionService.createSession(user.getId(), request, jwt); // Create a user session

		// Return JWT response with user details
		return ResponseEntity
				.ok(new JwtResponse(user.getId(), jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	/**
	 * Handles user registration requests.
	 * 
	 * @param user - User object containing registration details
	 * @return ResponseEntity with success or failure message
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {

		// Log the registration request for debugging
		logger.debug("AuthController.registerUser() - with [" + user + "]");

		// Attempt to register the user and return appropriate response
		if (userService.registerUser(user) != null) {
			return ResponseEntity.ok("User registered successfully");
		}

		return ResponseEntity.badRequest().body("User registration failed");
	}

	/**
	 * Validates the provided JWT token.
	 * 
	 * @param map - Map containing token and userId
	 * @return ResponseEntity containing token expiration status
	 * @throws NumberFormatException - Handles parsing errors for userId
	 */
	@PostMapping("/isTokenValid")
	public ResponseEntity<Map<String, Boolean>> validateToken(@RequestBody Map<String, String> map)
			throws NumberFormatException {

		// Map to store token expiration status
		Map<String, Boolean> isExpired = new HashMap<>();
		boolean userpwdExpired = false;

		// Extract token and userId from the input map, handle default values
		String token = map.get("token") == null || map.get("token").equals("undefined") || map.get("token").equals("")
				? "gsadfhgaskjdf"
				: map.get("token");
		String userId = map.get("userId") == null || map.get("userId").equals("")
				|| map.get("userId").equals("undefined") ? "0" : map.get("userId");

		// Log the validation request
		logger.info("AuthController.validateToken() - with [" + token + "] [" + userId + "]");

		try {
			// Check if the token is expired
			boolean tokenExpired2 = jwtUtils.isTokenExpired(token);
			isExpired.put("tokenExpired", tokenExpired2);
			isExpired.put("userpwdExpired", userpwdExpired); // Currently unused in logic

		} catch (Exception e) {
			// Log token expiration exception
			logger.info("AuthController.validateToken() - token Expired  [" + e.getMessage() + "]");
			isExpired.put("tokenExpired", false);
			isExpired.put("userpwdExpired", userpwdExpired);
		}

		// Return token expiration status
		return ResponseEntity.status(HttpStatus.OK).body(isExpired);
	}

	/**
	 * Handles user logout requests.
	 * 
	 * @param request - HttpServletRequest to capture request details
	 * @param response - HttpServletResponse to handle logout response
	 * @return ResponseEntity with logout confirmation message
	 */
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {

		// Log the logout request for debugging
		logger.debug("AuthController.logout() - with [" + request + "]");

		// Retrieve the current authentication context
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			// End user session and handle logout
			userSessionService.endSession(request);
			new SecurityContextLogoutHandler().logout(request, response, auth);
			return ResponseEntity.ok(new MessageResponse("User logout successfully!"));
		}

		// Return logout confirmation even if no active authentication context is found
		return ResponseEntity.ok(new MessageResponse("User logout successfully!"));
	}
}


