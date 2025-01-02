package com.sudipta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleMicroservice {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMicroservice.class);

	public static void main(String[] args) {
		LOGGER.debug("MicroserviceThree Starting....................");
		SpringApplication.run(SimpleMicroservice.class);
		LOGGER.debug("MicroserviceThree Started Successfully....................");
	}

}
