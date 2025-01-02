package com.sudipta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroserviceOne {
	private static final Logger LOGGER = LoggerFactory.getLogger(MicroserviceOne.class);

	public static void main(String[] args) {

		LOGGER.debug("MicroserviceOne Starting....................");
		SpringApplication.run(MicroserviceOne.class);
		LOGGER.debug("MicroserviceOne Started Successfully....................");
	}

}
