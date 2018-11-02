package com.wt.restaurant.service.reserve;

import java.util.List;

import com.wt.restaurant.entity.Reserve;

public interface IReserveService {
	/**
	 * 后台查询所有的预订信息
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<Reserve> listReserve(Integer currentPageNo,Integer pageSize,Reserve reserve);

	/**
	 * 修改预订信息
	 * 
	 * @param reserve
	 * @return
	 * @throws Exception
	 */
	boolean updateReserve(Reserve reserve) throws Exception;

	/**
	 * 保存预订信息
	 * 
	 * @param reserve
	 * @return
	 * @throws Exception
	 */
	boolean saveReserve(Reserve reserve) throws Exception;
	/**
	 * 保存订单
	 * @param reserve
	 * @return
	 * @throws Exception
	 */
	boolean saveDishOrder(Reserve reserve) throws Exception;

	/**
	 * 删除预订信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean removeReserve(int id) throws Exception;

	/**
	 * 查询单个预订的信息
	 * 
	 * @param id
	 * @return
	 */
	Reserve getReserve(int id);

	/**
	 * 查询我的预订
	 * 
	 * @param customerId
	 * @return
	 */
	List<Reserve> listReserveByCustomerId(int customerId);

	/**
	 * 获得所有的预订数量
	 * 
	 * @return
	 */
	Integer countReserve(Reserve reserve);
}
