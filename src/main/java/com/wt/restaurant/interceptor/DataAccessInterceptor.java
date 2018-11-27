package com.wt.restaurant.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wt.restaurant.entity.Login;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.ContextUtil;

@SuppressWarnings("unused")
public class DataAccessInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LogManager.getLogger();

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute(Constants.USER_SESSION);
//		if (null == user) {//用户未登录 , 跳转到首页
//			response.sendRedirect(request.getContextPath() + "/authorization");
//			String ip = ContextUtil.getClientIpAddress(request);
//			logger.warn("用户: " + ip + " 尝试非法访问接口,URI : " + request.getRequestURI());
//			return false;
//		}
		return true;
	}
}
