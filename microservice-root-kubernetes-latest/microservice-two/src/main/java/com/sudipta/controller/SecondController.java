package com.sudipta.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException.ServiceUnavailable;

import com.sudipta.common.dto.ApplicationResponseData;
import com.sudipta.common.dto.ServiceResponseStatus;
import com.sudipta.common.dto.ServiceResponseStatusConstant;

//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/SecondController")
public class SecondController {
	@Autowired
	private WebClient.Builder builder;

	@Autowired
	private ServerProperties serverProperties;

	private Logger LOGGER = LoggerFactory.getLogger(SecondController.class);
//	private String url = "http://SERVICE-3/ThirdController/micro3";
	private String url = "http://localhost:1003/ThirdController/micro3";

	@GetMapping("/micro2")
	public ResponseEntity<ApplicationResponseData<List<String>>> getMessage() {
		ParameterizedTypeReference<ApplicationResponseData<List<String>>> typeRef = new ParameterizedTypeReference<ApplicationResponseData<List<String>>>() {
		};

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("In Micro Service 2 @ :: ").append(LocalDateTime.now()).append("   Port ==>   ")
				.append(serverProperties.getPort());

		String stringMessage = stringBuilder.toString();
		LOGGER.debug(stringMessage);

		Flux<ApplicationResponseData<List<String>>> bodyToFlux = builder.build().get().uri(url).retrieve()
				.bodyToFlux(typeRef);

		ApplicationResponseData<List<String>> responseData = bodyToFlux.blockFirst();

		responseData.getResponseData().add(stringMessage);

		return new ResponseEntity<ApplicationResponseData<List<String>>>(responseData, HttpStatus.OK);

	}

	public ResponseEntity<ApplicationResponseData<List<String>>> getMessageFallback(Throwable throwable) {
		List<String> errorList = new ArrayList<String>();
		if (throwable instanceof ServiceUnavailable) {
			ServiceUnavailable serviceUnavailable = (ServiceUnavailable) throwable;
			errorList.add(serviceUnavailable.getStatusCode().toString());

			Throwable[] suppressed = serviceUnavailable.getSuppressed();
			for (Throwable throwable2 : suppressed) {
				errorList.add(throwable2.getMessage());
			}

		} else {
			errorList.add(throwable.getMessage());

		}
		errorList.add("url Called " + url);
		ServiceResponseStatus responseStatus = new ServiceResponseStatus();
		responseStatus.setResponseCode(ServiceResponseStatusConstant.ERROR_CODE);
		responseStatus.setResponseMessage(ServiceResponseStatusConstant.ERROR_MESSAGE);
		responseStatus.setDetailMessageList(errorList);

		ApplicationResponseData<List<String>> responseData = new ApplicationResponseData<List<String>>();
		responseData.setResponseStatus(responseStatus);

		return new ResponseEntity<ApplicationResponseData<List<String>>>(responseData, HttpStatus.SERVICE_UNAVAILABLE);
	}

}
