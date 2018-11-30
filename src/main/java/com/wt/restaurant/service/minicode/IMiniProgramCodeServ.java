package com.wt.restaurant.service.minicode;

import com.wt.restaurant.entity.Box;
import com.wt.restaurant.entity.MiniProgramCodeParam;
import com.wt.restaurant.entity.Table;

/**
 * 小程序码相关的接口
 * @author Daryl
 */
public interface IMiniProgramCodeServ {

	/**
	 * GET请求
	 * @return 获取小程序 access_token
	 */
	String fetchAccessToken();
	
	/**
	 * POST请求
	 * @return 获取小程序码是否成功
	 */
	boolean fetchWXACodeUnlimit(MiniProgramCodeParam param,String absoluteDirectory);
	
	/**
	 * 删除table对应的小程序码
	 * @param absoluteDirectory 
	 * @param table
	 */
	void removeTableMiniProgramCode(Table table, String absoluteDirectory);

	/**
	 * 删除box对应的小程序码
	 * @param absoluteDirectory 
	 * @param box
	 */
	void removeBoxMiniProgramCode(Box box, String absoluteDirectory);
	
}