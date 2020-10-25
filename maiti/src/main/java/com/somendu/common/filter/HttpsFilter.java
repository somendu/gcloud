package com.somendu.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Https Filter for redirecting from http to https
 * 
 * @author somendu
 *
 */
@WebFilter(filterName = "HttpsFilter", urlPatterns = { "/*" })
public class HttpsFilter implements Filter {
	private FilterConfig conf;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (req.getRemoteUser() != null && req.getScheme().equals("http")) {
			String url = "https://" + req.getServerName() + req.getContextPath() + req.getServletPath();
			if (req.getPathInfo() != null) {
				url += req.getPathInfo();
			}
			resp.sendRedirect(url);
		} else {
			chain.doFilter(request, response);
		}
	}

	public FilterConfig getFilterConfig() {
		return conf;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		conf = filterConfig;
	}

	public void destroy() {
	}

	public void init(FilterConfig filterConfig) {
		conf = filterConfig;
	}
}