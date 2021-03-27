package com.wsh.servlet.Filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

@WebFilter(description = "×Ö·û±àÂë¹ýÂËÆ÷", filterName = "encodingFilter", urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "ENCODING", value = "UTF-8") })
public class EncodingFilter implements Filter {

	private String encoding = "";

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("ENCODING");
		if (encoding == null || "".equals(encoding)) {
			encoding = "utf-8";
		}
	}

}
