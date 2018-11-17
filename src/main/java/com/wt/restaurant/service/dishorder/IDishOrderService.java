package com.wt.restaurant.service.dishorder;

import java.util.List;

import com.wt.restaurant.entity.DishOrder;

public interface IDishOrderService {

	/**
	 * 查询所有的订单
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<DishOrder> listDishOrder(Integer currentPageNo, Integer pageSize);

	/**
	 * 查询我的订单
	 * 
	 * @param customerId
	 * @return
	 */
	List<DishOrder> listDishOrderByCustomerId(Integer customerId);

	/**
	 * 查询数量
	 * 
	 * @return
	 */
	Integer countDishOrder();

}
