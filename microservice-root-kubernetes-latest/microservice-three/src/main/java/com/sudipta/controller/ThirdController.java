package com.sudipta.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudipta.common.dto.ApplicationResponseData;
import com.sudipta.common.dto.ServiceResponseStatus;
import com.sudipta.common.dto.ServiceResponseStatusConstant;
//import com.sudipta.services.ISalesService;

@RestController()
@RequestMapping("/ThirdController")
public class ThirdController {
	private Logger LOGGER = LoggerFactory.getLogger(ThirdController.class);

	@Autowired
	private ServletWebServerApplicationContext webServerApplicationContext;

//    private ISalesService salesService;

	@GetMapping("/micro3")
	public ResponseEntity<ApplicationResponseData<List<String>>> getMessage() {
		List<String> dataList = new ArrayList<String>();

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("In Micro Service 3@ :: ").append(LocalDateTime.now()).append("   Port ==>   ")
				.append(webServerApplicationContext.getWebServer().getPort());

		String stringMessage = stringBuilder.toString();
		LOGGER.debug(stringMessage);
		dataList.add(stringMessage);

		ServiceResponseStatus responseStatus = new ServiceResponseStatus();
		responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
		responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);

		ApplicationResponseData<List<String>> responseData = new ApplicationResponseData<List<String>>();
		responseData.setResponseStatus(responseStatus);

		responseData.setResponseData(dataList);

//	salesService.getSales();

		return new ResponseEntity<ApplicationResponseData<List<String>>>(responseData, HttpStatus.OK);
	}

}
