package com.sudipta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class MicroserviceTwo
{
    private static final  Logger LOGGER = LoggerFactory.getLogger(MicroserviceTwo.class);
    public static void main(String[] args)
    {
	LOGGER.debug("MicroserviceTwo Starting....................");
	SpringApplication.run(MicroserviceTwo.class);
	
	LOGGER.debug("MicroserviceTwo Started Successfully....................");
    }

}
