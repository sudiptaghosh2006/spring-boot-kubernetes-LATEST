package com.sudipta.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
//import org.springframework.cloud.client.loadbalancer.ResponseData;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException.ServiceUnavailable;

import com.sudipta.common.dto.ApplicationResponseData;
import com.sudipta.common.dto.ServiceResponseStatus;
import com.sudipta.common.dto.ServiceResponseStatusConstant;

//import com.sudipta.common.dto.ApplicationResponseData;
//import com.sudipta.common.dto.ServiceResponseStatus;
//import com.sudipta.common.dto.ServiceResponseStatusConstant;

//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/FirstController")
public class FirstController
{

    @Autowired
    private WebClient.Builder builder;


    private Logger LOGGER = LoggerFactory.getLogger(FirstController.class);
    private String url = "http://SERVICE-2/SecondController/micro2";
//    private String url = "http://localhost:1002/SecondController/micro2";

    @Autowired
	private ServletWebServerApplicationContext webServerApplicationContext;

    @GetMapping("/microAdmin1")

    public ResponseEntity<ApplicationResponseData<List<String>>> getAdminMessage()
//    public ResponseEntity<ApplicationResponseData<String>> getAdminMessage()
    {
	
	 ParameterizedTypeReference<ApplicationResponseData<List<String>>> typeRef	 
	 =new ParameterizedTypeReference<ApplicationResponseData<List<String>>>() {};
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("In Micro Service 1 @ :: ").append(LocalDateTime.now()).append("   Port ==>   ")
		.append(webServerApplicationContext.getWebServer().getPort());

	String stringMessage = stringBuilder.toString();
	LOGGER.debug(stringMessage);

	Flux<ApplicationResponseData<List<String>>> bodyToFlux = builder.build().get().uri(url).retrieve()
		.bodyToFlux(typeRef);
	
//	Flux<ApplicationResponseData<List<String>>> bodyToFlux = webClient.get().uri(url).retrieve()
//		.bodyToFlux(typeRef);
	
	ApplicationResponseData<List<String>> responseData = bodyToFlux.blockFirst();
	responseData.getResponseData().add(stringMessage );
	return new ResponseEntity<ApplicationResponseData<List<String>>>(responseData, HttpStatus.OK);


    }
    
    @GetMapping("/microUser1")
//    @Operation(tags = "Microservice-1", description = "gets the message ", summary = "Gets the Message")
//    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Message Found", content = {
//	    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
//	    @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
//	      content = @Content), 
//	    @ApiResponse(responseCode = "404", description = "No  Message found", content = @Content) })

//    @CircuitBreaker(name = "SERVICE-1", fallbackMethod = "getMessageFallback")

//    @PreAuthorize("hasAnyRole('APP-USER')")
    public ResponseEntity<ApplicationResponseData<List<String>>> getUserMessage()
    {
	
	 ParameterizedTypeReference<ApplicationResponseData<List<String>>> typeRef	 
	 =new ParameterizedTypeReference<ApplicationResponseData<List<String>>>() {};
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("In Micro Service 1 @ :: ").append(LocalDateTime.now()).append("   Port ==>   ")
		.append(webServerApplicationContext.getWebServer().getPort());

	String stringMessage = stringBuilder.toString();
	LOGGER.debug(stringMessage);

	Flux<ApplicationResponseData<List<String>>> bodyToFlux = builder.build().get().uri(url).retrieve()
		.bodyToFlux(typeRef);
	
//	Flux<ApplicationResponseData<List<String>>> bodyToFlux = webClient.get().uri(url).retrieve()
//		.bodyToFlux(typeRef);
	
	ApplicationResponseData<List<String>> responseData = bodyToFlux.blockFirst();
	responseData.getResponseData().add("Information added in First Controller  @  "+LocalDateTime.now() +"==>  PORT NO :::   " +webServerApplicationContext.getWebServer().getPort());
	return new ResponseEntity<ApplicationResponseData<List<String>>>(responseData, HttpStatus.OK);
	  
	

    }


    public ResponseEntity<ApplicationResponseData<List<String>>> getMessageFallback(Throwable throwable)
    {
	List<String> errorList=new ArrayList<String>();
	if (throwable instanceof ServiceUnavailable)
	{
	    ServiceUnavailable serviceUnavailable = (ServiceUnavailable) throwable;
	    errorList.add(serviceUnavailable.getStatusCode().toString());
	}
	else
	{
	    errorList.add(throwable.getMessage());

	}
	errorList.add("url Called "+url);	
	
	ServiceResponseStatus responseStatus=new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.ERROR_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.ERROR_MESSAGE);
	responseStatus.setDetailMessageList(errorList);
	
	ApplicationResponseData<List<String>> responseData=new ApplicationResponseData<List<String>>();
	responseData.setResponseStatus(responseStatus);

	return new ResponseEntity<ApplicationResponseData<List<String>>>(responseData, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
