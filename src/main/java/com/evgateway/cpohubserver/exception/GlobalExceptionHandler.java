package com.evgateway.cpohubserver.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.evgateway.cpohubserver.common.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Response<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
		Response<String> errorResponse = new Response<>(null, HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				new Date());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response<String>> handleGenericException(Exception ex) {
		Response<String> errorResponse = new Response<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"An unexpected error occurred", new Date());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	// Handle UserNotFoundException globally
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Response<String>> handleUserNotFoundException(UserNotFoundException ex) {
		Response<String> errorResponse = new Response<>(null, HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				new Date());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<Response<String>> handleInvalidCredentialsException(InvalidCredentialsException ex) {
		Response<String> errorResponse = new Response<>(null, HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				new Date());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	
	@ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Response> handleDataNotFoundException(DataNotFoundException ex) {
        // Extract the message from the exception
		Response<String> response = new Response<>(null, HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				new Date());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}