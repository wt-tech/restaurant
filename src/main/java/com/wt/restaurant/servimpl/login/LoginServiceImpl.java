package com.wt.restaurant.servimpl.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.login.ILoginMapper;
import com.wt.restaurant.entity.Login;
import com.wt.restaurant.service.login.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService{

	@Autowired
	private ILoginMapper mapper;

	@Override
	public boolean login(Login login){
		return mapper.login(login) != null;
	}

	@Override
	public boolean updatePwd(Login login){
		return mapper.updatePwd(login);
	}
	
	

}
