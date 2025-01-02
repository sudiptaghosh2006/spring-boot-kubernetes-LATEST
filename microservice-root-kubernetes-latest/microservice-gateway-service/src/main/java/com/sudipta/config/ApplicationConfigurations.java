
package com.sudipta.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfigurations {
	/**
	 * A Token Relay is where an OAuth2 consumer acts as a Client and forwards the
	 * incoming token to outgoing resource requests. The consumer can be a pure
	 * Client (like an SSO application) or a Resource Server. and it will (in
	 * addition to logging the user in and grabbing a token) pass the authentication
	 * token downstream to the services like /SERVICE-1/ microservice
	 */

	/*
	 * @Autowired private TokenRelayGatewayFilterFactory factory;
	 * 
	 * public ApplicationConfigurations(TokenRelayGatewayFilterFactory factory) {
	 * super(); this.factory = factory; }
	 */

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes().route(r -> r.path("/FirstController/**")
//			.filters(f -> f.filter(factory.apply()))
				.uri("http://localhost:1001/")).build();
	}

}
