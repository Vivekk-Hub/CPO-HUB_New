package com.evgateway.cpohubserver.services;

import java.util.UUID;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.evgateway.cpohubserver.config.RestTemplateErrorHandler;
import com.evgateway.cpohubserver.exception.UserNotFoundException;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {

	@Override
	public <T> ResponseEntity<String> sendDataViaPost(String url, T entity, String token, String preFix) {

		HttpEntity<T> requestEntity = new HttpEntity<T>(entity, getHeader(token, preFix));

		RestTemplate restTemplate = getcookie();

		restTemplate.setErrorHandler(new RestTemplateErrorHandler());

		ResponseEntity<String> postresponse = restTemplate.postForEntity(url, requestEntity, String.class);

		return postresponse;

	}

//	@Override
//	public <T> void sendDataViaPut(String url, T entity, String token, String party_id, String preFix) throws UserNotFoundException {
//
//		HttpEntity<T> requestEntity = new HttpEntity<T>(entity, getHeader(token, party_id,preFix));
//
//		RestTemplate restTemplate = getcookie();
//
//		// restTemplate.setErrorHandler(new RestTemplateErrorHandler());
//
//		ResponseEntity<String> putresponse = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
//
//	}
//
	@Override
	public <T> ResponseEntity<String> sendDataViaGet(String url, String token, String party_id, String preFix)
			throws UserNotFoundException {

		HttpEntity<T> requestEntity = new HttpEntity<T>(getHeader(token, preFix));

		RestTemplate restTemplate = getcookie();

		restTemplate.setErrorHandler(new RestTemplateErrorHandler());

		ResponseEntity<String> getresponse = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

		return getresponse;

	}

	public HttpHeaders getHeader(String token, String preFix) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", preFix + " " + token);
		headers.set("X-Request-ID", UUID.randomUUID().toString());
		headers.set("X-Correlation-ID", UUID.randomUUID().toString());
		// headers.set("Party-ID", partyId);
		return headers;

	}

//
	public RestTemplate getcookie() {

		BasicCookieStore cookieStore = new BasicCookieStore();

		RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setDefaultCookieStore(cookieStore)
				.setDefaultRequestConfig(requestConfig);

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
				httpClientBuilder.build());

		RestTemplate restTemplate = new RestTemplate(requestFactory);

		return restTemplate;

	}

}
