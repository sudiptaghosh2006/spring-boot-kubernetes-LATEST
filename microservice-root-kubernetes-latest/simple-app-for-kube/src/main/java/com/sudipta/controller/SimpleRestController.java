package com.sudipta.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
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
@RequestMapping("/SimpleRestController")
public class SimpleRestController
{
    private Logger LOGGER = LoggerFactory.getLogger(SimpleRestController.class);

    @Autowired
    private ServerProperties serverProperties;
    


    @GetMapping("/message")
    public ResponseEntity<ApplicationResponseData<List<String>>> getMessage()
    {
	List<String> dataList=new ArrayList<String>();

	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("In  Service @ :: ").append(LocalDateTime.now()).append("   Port ==>   ")
		.append(serverProperties.getPort());

	String stringMessage = stringBuilder.toString();
	LOGGER.debug(stringMessage);
	dataList.add(stringMessage);
	
	ServiceResponseStatus responseStatus=new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
	
	
	ApplicationResponseData<List<String>> responseData=new ApplicationResponseData<List<String>>();
	responseData.setResponseStatus(responseStatus);
	
	responseData.setResponseData(dataList);
	


	return new ResponseEntity<ApplicationResponseData<List<String>>>(responseData, HttpStatus.OK);
    }

}
