package com.gyorslevel.devmode;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class HttpHeadersFilter implements Filter {

	private static Logger logger = Logger
			.getLogger(HttpHeadersFilter.class);
	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {

			if (request instanceof HttpServletRequest) {
				HttpServletRequest httpServletRequest = (HttpServletRequest) request;
				ModifiedHttpServletRequest fakeRequest = new ModifiedHttpServletRequest(httpServletRequest);
				chain.doFilter(fakeRequest, response);
			} else {
				chain.doFilter(request, response);
			}
		} catch (Exception exception) {
			logger.error(exception);
		}
		return;
	}
}
