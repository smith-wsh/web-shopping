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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(description = "µÇÂ¼¹ýÂËÆ÷", filterName = "loginFilter", urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "loginPage", value = "login.jsp") })
public class LoginFilter implements Filter {

	private FilterConfig config;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String loginPage = config.getInitParameter("loginPage");
		HttpSession session = ((HttpServletRequest) request).getSession();
		String requestPath = ((HttpServletRequest) request).getServletPath();
		if (session.getAttribute("uname") == null
				&& (requestPath.endsWith("alreadyBuy.jsp")
						|| requestPath.endsWith("shoppingCart.jsp")
						|| requestPath.endsWith("addToCart.jsp") || requestPath
							.endsWith("showMessage.jsp"))) {
			request.getRequestDispatcher(loginPage).forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
