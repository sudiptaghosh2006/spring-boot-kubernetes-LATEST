package com.sudipta.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BasicSpringBootFilter extends OncePerRequestFilter {
	private Logger LOGGER = LoggerFactory.getLogger(BasicSpringBootFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		LOGGER.debug("Debug Msg in filter");
//	Enumeration<String> headerNames = httpServletRequest.getHeaderNames();

//	LOGGER.debug("Headers name "+ headerNames);
//	while(headerNames.hasMoreElements())
//	{
//	    String element = headerNames.nextElement();
//	    LOGGER.debug("Headers name ::  "+ element +" \t value   :: "+ httpServletRequest.getHeader(element));
//	
//	}
		filterChain.doFilter(httpServletRequest, httpServletResponse);

	}

}
