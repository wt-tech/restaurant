package com.wt.restaurant.service.banquetreserve;

import java.util.List;

import com.wt.restaurant.entity.BanquetReserve;

public interface IBanquetReserveService {
	/**
	 * 后台查询所有的预订信息
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<BanquetReserve> listBanquetReserve(Integer currentPageNo, Integer pageSize,BanquetReserve banquetreserve);

	/**
	 * 修改预订信息
	 * 
	 * @param BanquetReserve
	 * @return
	 * @throws Exception
	 */
	boolean updateBanquetReserve(BanquetReserve banquetreserve) throws Exception;

	/**
	 * 保存预订信息
	 * 
	 * @param BanquetReserve
	 * @return
	 * @throws Exception
	 */
	boolean saveBanquetReserve(BanquetReserve banquetreserve) throws Exception;

	/**
	 * 保存订单
	 * 
	 * @param reserve
	 * @return
	 * @throws Exception
	 */
	boolean saveDishOrder(BanquetReserve banquetreserve) throws Exception;

	/**
	 * 删除预订信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean removeBanquetReserve(int id) throws Exception;

	/**
	 * 查询单个预订的信息
	 * 
	 * @param id
	 * @return
	 */
	BanquetReserve getBanquetReserve(int id);

	/**
	 * 查询我的预订
	 * 
	 * @param customerId
	 * @return
	 */
	List<BanquetReserve> listBanquetReserveByCustomerId(int customerId);

	/**
	 * 获得所有的预订数量
	 * 
	 * @return
	 */
	Integer countBanquetReserve(BanquetReserve banquetreserve);
}
