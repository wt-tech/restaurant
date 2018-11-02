package com.wt.restaurant.dao.reserve;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.restaurant.entity.DishOrder;
import com.wt.restaurant.entity.DishOrderLine;
import com.wt.restaurant.entity.Reserve;

public interface IReserveMapper {
	/**
	 * 后台查询所有的预订信息
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<Reserve> listReserve(@Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize,
			@Param("reserve") Reserve reserve);

	/**
	 * 修改预订信息
	 * 
	 * @param reserve
	 * @return
	 * @throws Exception
	 */
	Integer updateReserve(Reserve reserve) throws Exception;

	/**
	 * 保存预订信息
	 * 
	 * @param reserve
	 * @return
	 * @throws Exception
	 */
	Integer saveReserve(Reserve reserve) throws Exception;

	/**
	 * 保存预订的包厢信息
	 * 
	 * @param reserveId
	 * @param boxId
	 * @return
	 * @throws Exception
	 */
	Integer saveBox(@Param("reserveId") int reserveId, @Param("boxId") int boxId) throws Exception;

	/**
	 * 保存预订的菜品信息
	 * 
	 * @param reserveId
	 * @param menuId
	 * @return
	 * @throws Exception
	 */
	Integer saveMenu(@Param("reserveId") int reserveId, @Param("menuId") int menuId,
			@Param("specifications") String specifications, @Param("choosePrice") double choosePrice,
			@Param("menuCount") int menuCount) throws Exception;

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
	Integer saveDishOrderLine(DishOrderLine dishorderline) throws Exception;

	/**
	 * 删除预订信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer removeReserve(@Param("id") int id) throws Exception;

	/**
	 * 查询单个预订的信息
	 * 
	 * @param id
	 * @return
	 */
	Reserve getReserve(@Param("id") int id);

	/**
	 * 查询我的预订
	 * 
	 * @param customerId
	 * @return
	 */
	List<Reserve> listReserveByCustomerId(@Param("customerId") int customerId);

	/**
	 * 获得所有的预订数量
	 * 
	 * @return
	 */
	Integer countReserve(@Param("reserve") Reserve reserve);
}
