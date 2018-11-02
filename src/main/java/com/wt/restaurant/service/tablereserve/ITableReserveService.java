package com.wt.restaurant.service.tablereserve;

import java.util.List;

import com.wt.restaurant.entity.TableReserve;

public interface ITableReserveService {
	/**
	 * 后台查询所有的预订信息
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<TableReserve> listTableReserve(Integer currentPageNo, Integer pageSize);

	/**
	 * 修改预订信息
	 * 
	 * @param TableReserve
	 * @return
	 * @throws Exception
	 */
	boolean updateTableReserve(TableReserve tablereserve) throws Exception;

	/**
	 * 保存预订信息
	 * 
	 * @param TableReserve
	 * @return
	 * @throws Exception
	 */
	boolean saveTableReserve(TableReserve tablereserve) throws Exception;

	/**
	 * 保存订单
	 * 
	 * @param TableReserve
	 * @return
	 * @throws Exception
	 */
	boolean saveDishOrder(TableReserve tablereserve) throws Exception;

	/**
	 * 删除预订信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean removeTableReserve(int id) throws Exception;

	/**
	 * 查询单个预订的信息
	 * 
	 * @param id
	 * @return
	 */
	TableReserve getTableReserve(int id);

	/**
	 * 查询我的预订
	 * 
	 * @param customerId
	 * @return
	 */
	List<TableReserve> listTableReserveByCustomerId(int customerId);

	/**
	 * 获得所有的预订数量
	 * 
	 * @return
	 */
	Integer countTableReserve();
}
