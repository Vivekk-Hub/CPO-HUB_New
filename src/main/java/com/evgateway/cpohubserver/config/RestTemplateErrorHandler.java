package com.evgateway.cpohubserver.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateErrorHandler.class);

	public boolean hasError(ClientHttpResponse response) throws IOException {
		return new DefaultResponseErrorHandler().hasError(response);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {

		HttpStatus statusCode = response.getStatusCode();

		if (statusCode.is5xxServerError()) {
			// Log server-side errors (500-599)
			LOGGER.error("RestTemplateErrorHandler.handleError()--Server error: [{}] - {}", statusCode.value(),
					statusCode.getReasonPhrase());
		} else if (statusCode.is4xxClientError()) {
			// Log client-side errors (400-499)
			LOGGER.warn("RestTemplateErrorHandler.handleError()--Client error: [{}] - {}", statusCode.value(),
					statusCode.getReasonPhrase());

			if (statusCode == HttpStatus.UNAUTHORIZED) {
				LOGGER.warn(
						"RestTemplateErrorHandler.handleError()--Unauthorized access: Please check your credentials or authentication token.");
			}
		} else {
			LOGGER.info("RestTemplateErrorHandler.handleError()--Unexpected status code: [{}] - {}", statusCode.value(),
					statusCode.getReasonPhrase());
		}
	}
}