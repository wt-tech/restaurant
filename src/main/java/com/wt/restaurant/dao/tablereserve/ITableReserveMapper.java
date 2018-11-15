package com.wt.restaurant.dao.tablereserve;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.restaurant.entity.DishOrder;
import com.wt.restaurant.entity.DishOrderLine;
import com.wt.restaurant.entity.Menu;
import com.wt.restaurant.entity.TableReserve;

public interface ITableReserveMapper {
	/**
	 * 查询所有的扫码点餐
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<TableReserve> listTableReserve(@Param("currentPageNo") Integer currentPageNo,
			@Param("pageSize") Integer pageSize,@Param("tablereserve") TableReserve tablereserve);

	/**
	 * 修改点餐信息
	 * 
	 * @param tablereserve
	 * @return
	 * @throws Exception
	 */
	Integer updateTableReserve(TableReserve tablereserve) throws Exception;

	/**
	 * 保存扫码点餐信息
	 * 
	 * @param tablereserve
	 * @return
	 * @throws Exception
	 */
	Integer saveTableReserve(TableReserve tablereserve) throws Exception;

	/**
	 * 保存点餐菜品
	 * @param reserveId
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
	 * 删除点餐信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer removeTableReserve(@Param("id") int id) throws Exception;

	/**
	 * 查询单个点餐的信息
	 * 
	 * @param id
	 * @return
	 */
	TableReserve getTableReserve(@Param("id") int id);

	/**
	 * 查询我的点餐信息
	 * 
	 * @param customerId
	 * @return
	 */
	List<TableReserve> listTableReserveByCustomerId(@Param("customerId") int customerId);

	/**
	 * 获得所有的点餐数量
	 * 
	 * @return
	 */
	Integer countTableReserve(@Param("tablereserve") TableReserve tablereserve);
}
