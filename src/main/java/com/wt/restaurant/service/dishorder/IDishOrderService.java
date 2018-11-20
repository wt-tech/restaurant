package com.wt.restaurant.service.dishorder;

import java.util.List;

import com.wt.restaurant.entity.DishOrder;
import com.wt.restaurant.entity.DishOrderLine;

public interface IDishOrderService {

	/**
	 * 查询所有的订单
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<DishOrder> listDishOrder(Integer currentPageNo, Integer pageSize, DishOrder dishorder);

	/**
	 * 查询我的订单
	 * 
	 * @param customerId
	 * @return
	 */
	List<DishOrder> listDishOrderByCustomerId(Integer customerId);

	List<DishOrderLine> listDishOrderMenu(Integer dishorderId);

	/**
	 * 查询数量
	 * 
	 * @return
	 */
	Integer countDishOrder(DishOrder dishorder);

	boolean updateDishOrderRemark(Integer id, String remark) throws Exception;
	
	boolean updateDishOrderStatus(Integer id, double totalPayAmount) throws Exception;

	DishOrder getDishOrderRemark(Integer id) throws Exception;

}
