package com.wt.restaurant.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wt.restaurant.entity.Login;
import com.wt.restaurant.tool.Constants;

@SuppressWarnings("unused")
public class HtmlAccessInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LogManager.getLogger();

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute(Constants.USER_SESSION);
		if (null == user) {//用户未登录 , 跳转到登录页
			response.setStatus(302);
			response.setHeader("Location", "/market/login/login.html");
			return false;
		}
		return true;
	}
}
