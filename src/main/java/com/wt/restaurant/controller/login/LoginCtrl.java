package com.wt.restaurant.controller.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wt.restaurant.entity.Login;
import com.wt.restaurant.service.login.ILoginService;
import com.wt.restaurant.tool.BusinessUtils;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.ContextUtil;
import com.wt.restaurant.tool.MapUtils;

@RestController
public class LoginCtrl {

	@Autowired
	private ILoginService loginService;
	
	private Logger logger = LogManager.getLogger();
	
	@RequestMapping(value="login",method = RequestMethod.POST)
	public Map<String,Object> login(@RequestBody Login login,
			HttpSession session,HttpServletRequest request){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		boolean flag = loginService.login(login) ;
		map.put(Constants.STATUS, flag? Constants.SUCCESS : Constants.FAIL);
		if(flag) {//加入session
			System.err.println(session.getId());
			session.setAttribute(Constants.USER_SESSION, login);
			String ip = ContextUtil.getClientIpAddress(request);
			logger.info("用户: " + login.getUserCode() + ",IP : " + ip + " 登录成功");
		}
		return map;
	}
	
	@RequestMapping(value="login/back/updatepwd",method = RequestMethod.PUT)
	public Map<String,Object> updatePwd(@RequestBody Login login){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, loginService.updatePwd(login) ? Constants.SUCCESS : Constants.FAIL);
		return map;
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.POST)
	public Map<String,Object> logout(HttpSession session){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		if(null != session) {
			session.invalidate();
		}
		return map;
	}
	
	@RequestMapping("authorization")
	public void accessDenied(HttpServletResponse response) {
		BusinessUtils.throwNewBusinessException("请先登录");
	}
}
