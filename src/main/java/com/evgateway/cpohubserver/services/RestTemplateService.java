package com.evgateway.cpohubserver.services;

import org.springframework.http.ResponseEntity;

import com.evgateway.cpohubserver.exception.UserNotFoundException;

public interface RestTemplateService {

	<T> ResponseEntity<String> sendDataViaPost(String url, T entity, String token, String preFix)
			throws UserNotFoundException;

	

//	<T> void sendDataViaPut(String url, T entity, String token, String party_id, String preFix)
//			throws UserNotFoundException;

	<T> ResponseEntity<String> sendDataViaGet(String url, String token, String party_id, String preFix)
			throws UserNotFoundException;

}
