package com.wt.restaurant.dao.dishorder;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wt.restaurant.entity.DishOrder;
import com.wt.restaurant.entity.DishOrderLine;

public interface IDishOrderMapper {

	/**
	 * 查询所有的订单
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<DishOrder> listDishOrder(@Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize,
			@Param("dishorder") DishOrder dishorder);

	/**
	 * 查询我的订单
	 * 
	 * @param customerId
	 * @return
	 */
	List<DishOrder> listDishOrderByCustomerId(@Param("customerId") Integer customerId);

	/**
	 * 查询单个订单的菜品
	 * 
	 * @param dishorderId
	 * @return
	 */
	List<DishOrderLine> listDishOrderMenu(@Param("dishorderId") Integer dishorderId);

	/**
	 * 查询数量
	 * 
	 * @return
	 */
	Integer countDishOrder(@Param("dishorder") DishOrder dishorder);

	/**
	 * 编辑订单备注
	 * 
	 * @param id
	 * @param remark
	 * @return
	 * @throws Exception
	 */
	@Update("update dish_order set remark=#{remark} where id=#{id}")
	Integer updateDishOrderRemark(@Param("id") Integer id, @Param("remark") String remark) throws Exception;
	
	/**
	 * 订单结账
	 * @param id
	 * @param totalPayAmount
	 * @return
	 * @throws Exception
	 */
	@Update("update dish_order set total_pay_amount=#{totalPayAmount},order_status=1 where id=#{id}")
	Integer updateDishOrderStatus(@Param("id") Integer id, @Param("totalPayAmount") double totalPayAmount) throws Exception;

	/**
	 * 查询备注
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Select("select remark from dish_order where id=#{id}")
	DishOrder getDishOrderRemark(@Param("id") Integer id) throws Exception;

}
