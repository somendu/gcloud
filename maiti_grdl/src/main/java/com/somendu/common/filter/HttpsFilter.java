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
import javax.xml.bind.annotation.XmlInlineBinaryData;

import lombok.Data;

/**
 * Https Filter for redirecting from http to https
 * 
 * @author somendu
 *
 */
@WebFilter(filterName = "HttpsFilter", urlPatterns = { "/*" })
@Data
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

 

	public void destroy() {
	}

	public void init(FilterConfig filterConfig) {
		conf = filterConfig;
	}
}