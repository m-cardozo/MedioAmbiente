package com.utils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import javax.servlet.ServletException;

public class NoCacheFilter implements Filter {

	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
	}

	private FilterConfig filterConfig;

	public FilterConfig getFilterConfig() {
		return this.filterConfig;
	}

	public void setFilterConfig (FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) {
		try {
			if (response instanceof HttpServletResponse) {
				HttpServletResponse httpresponse = (HttpServletResponse)response ;
				// Set the Cache-Control and Expires header
				httpresponse.setHeader("Cache-Control", "no-cache") ;
				httpresponse.setHeader("Expires", "0") ;
				// Print out the URL we're filtering
				String name = ((HttpServletRequest)request).getRequestURI();
				System.out.println("No Cache Filtering: " + name) ;
			}

			chain.doFilter (request, response);
		} catch (IOException e) {
			System.out.println ("IOException in NoCacheFilter");
			e.printStackTrace() ;
		} catch (ServletException e) {
			System.out.println ("ServletException in NoCacheFilter");
			e.printStackTrace() ;
		}
	}
}