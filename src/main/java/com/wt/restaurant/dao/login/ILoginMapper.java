package com.wt.restaurant.dao.login;

import com.wt.restaurant.entity.Login;

public interface ILoginMapper {
	/**
	 * 通过userCode获取User
	 * 
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public Login login(Login login);

	/**
	 * 更新密码
	 * 
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public boolean updatePwd(Login login);
	
}
