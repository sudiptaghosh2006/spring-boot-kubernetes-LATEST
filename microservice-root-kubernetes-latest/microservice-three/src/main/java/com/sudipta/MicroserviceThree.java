package com.sudipta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
public class MicroserviceThree
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MicroserviceThree.class);

    public static void main(String[] args)
    {
	LOGGER.debug("MicroserviceThree Starting....................");
	SpringApplication.run(MicroserviceThree.class);
	LOGGER.debug("MicroserviceThree Started Successfully....................");
    }

}
