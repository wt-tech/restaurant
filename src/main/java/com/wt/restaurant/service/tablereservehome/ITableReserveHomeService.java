package com.wt.restaurant.service.tablereservehome;

import java.util.List;

import com.wt.restaurant.entity.TableReserveHome;

public interface ITableReserveHomeService {
	/**
	 * 后台查询所有的预订信息
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<TableReserveHome> listTableReserveHome(Integer currentPageNo,Integer pageSize);

	/**
	 * 修改预订信息
	 * 
	 * @param TableReserveHome
	 * @return
	 * @throws Exception
	 */
	boolean updateTableReserveHome(TableReserveHome tablereservehome) throws Exception;

	/**
	 * 保存预订信息
	 * 
	 * @param TableReserveHome
	 * @return
	 * @throws Exception
	 */
	boolean saveTableReserveHome(TableReserveHome tablereservehome) throws Exception;
	/**
	 * 保存订单
	 * @param TableReserveHome
	 * @return
	 * @throws Exception
	 */
	boolean saveDishOrder(TableReserveHome tablereservehome) throws Exception;

	/**
	 * 删除预订信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean removeTableReserveHome(int id) throws Exception;

	/**
	 * 查询单个预订的信息
	 * 
	 * @param id
	 * @return
	 */
	TableReserveHome getTableReserveHome(int id);

	/**
	 * 查询我的预订
	 * 
	 * @param customerId
	 * @return
	 */
	List<TableReserveHome> listTableReserveHomeByCustomerId(int customerId);

	/**
	 * 获得所有的预订数量
	 * 
	 * @return
	 */
	Integer countTableReserveHome();
}
