package com.wt.restaurant.service.login;

import com.wt.restaurant.entity.Login;

public interface ILoginService {
	/**
	 * 通过userCode获取User
	 * 
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public boolean login(Login login);

	/**
	 * 更新密码
	 * 
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public boolean updatePwd(Login login);
	
}
