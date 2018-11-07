package com.wt.restaurant.dao.tablereservehome;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.restaurant.entity.DishOrder;
import com.wt.restaurant.entity.DishOrderLine;
import com.wt.restaurant.entity.Menu;
import com.wt.restaurant.entity.TableReserveHome;

public interface ITableReserveHomeMapper {
	/**
	 * 后台查询所有的预订信息
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<TableReserveHome> listTableReserveHome(@Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize);

	/**
	 * 修改预订信息
	 * 
	 * @param tablereservehome
	 * @return
	 * @throws Exception
	 */
	Integer updateTableReserveHome(TableReserveHome tablereservehome) throws Exception;

	/**
	 * 保存预订信息
	 * 
	 * @param tablereservehome
	 * @return
	 * @throws Exception
	 */
	Integer saveTableReserveHome(TableReserveHome tablereservehome) throws Exception;

	/**
	 * 保存预订的菜品信息
	 * 
	 * @param menuList
	 * @return
	 * @throws Exception
	 */
	Integer saveMenu(@Param("menuList") List<Menu> menuList) throws Exception;

	/**
	 * 保存订单
	 * 
	 * @param dishorder
	 * @return
	 * @throws Exception
	 */
	Integer saveDishOrder(DishOrder dishorder) throws Exception;

	/**
	 * 保存订单项
	 * 
	 * @param dishorderline
	 * @return
	 * @throws Exception
	 */
	Integer saveDishOrderLine(@Param("dishorderlinelist") List<DishOrderLine> dishorderlinelist) throws Exception;

	/**
	 * 删除预订信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer removeTableReserveHome(@Param("id") int id) throws Exception;

	/**
	 * 查询单个预订的信息
	 * 
	 * @param id
	 * @return
	 */
	TableReserveHome getTableReserveHome(@Param("id") int id);

	/**
	 * 查询我的预订
	 * 
	 * @param customerId
	 * @return
	 */
	List<TableReserveHome> listTableReserveHomeByCustomerId(@Param("customerId") int customerId);

	/**
	 * 获得所有的预订数量
	 * 
	 * @return
	 */
	Integer countTableReserveHome();
}
