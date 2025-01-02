package com.sudipta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration

public class ApplicationConfigurations {
	@Bean
//  @LoadBalanced
	public WebClient.Builder getWebClientBuilder() {
		return WebClient.builder();
	}

}
