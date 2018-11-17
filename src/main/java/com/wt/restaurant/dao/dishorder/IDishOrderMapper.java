package com.wt.restaurant.dao.dishorder;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.restaurant.entity.DishOrder;

public interface IDishOrderMapper {

	/**
	 * 查询所有的订单
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<DishOrder> listDishOrder(@Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize);

	/**
	 * 查询我的订单
	 * 
	 * @param customerId
	 * @return
	 */
	List<DishOrder> listDishOrderByCustomerId(@Param("customerId") Integer customerId);
	
	/**
	 * 查询数量
	 * @return
	 */
	Integer countDishOrder();

}
