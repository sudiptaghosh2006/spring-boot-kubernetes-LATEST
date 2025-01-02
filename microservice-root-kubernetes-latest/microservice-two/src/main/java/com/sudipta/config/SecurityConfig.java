//package com.sudipta.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig
//{
//    @Bean
//    public SecurityFilterChain getSecurityFilterChain(HttpSecurity security) throws Exception
//    {
//	
////	JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
////        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new JwtGrantedAuthoritiesConverter());
//
//	security.authorizeHttpRequests(
//		(authorizeRequests) ->authorizeRequests.anyRequest().authenticated())
//	.oauth2ResourceServer().jwt();
////	.jwtAuthenticationConverter(jwtAuthenticationConverter)
//	
//	return security.build();
//    }
//
//}
