package com.wt.restaurant.dao.banquetreserve;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.restaurant.entity.BanquetReserve;
import com.wt.restaurant.entity.DishOrder;

public interface IBanquetReserveMapper {
	/**
	 * 后台查询所有的预订信息
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<BanquetReserve> listBanquetReserve(@Param("currentPageNo") Integer currentPageNo,
			@Param("pageSize") Integer pageSize);

	/**
	 * 修改预订信息
	 * 
	 * @param banquetreserve
	 * @return
	 * @throws Exception
	 */
	Integer updateBanquetReserve(BanquetReserve banquetreserve) throws Exception;

	/**
	 * 保存预订信息
	 * 
	 * @param banquetreserve
	 * @return
	 * @throws Exception
	 */
	Integer saveBanquetReserve(BanquetReserve banquetreserve) throws Exception;

	/**
	 * 保存订单
	 * 
	 * @param dishorder
	 * @return
	 * @throws Exception
	 */
	Integer saveDishOrder(DishOrder dishorder) throws Exception;

	/**
	 * 删除预订信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer removeBanquetReserve(@Param("id") int id) throws Exception;

	/**
	 * 查询单个预订的信息
	 * 
	 * @param id
	 * @return
	 */
	BanquetReserve getBanquetReserve(@Param("id") int id);

	/**
	 * 查询我的预订
	 * 
	 * @param customerId
	 * @return
	 */
	List<BanquetReserve> listBanquetReserveByCustomerId(@Param("customerId") int customerId);

	/**
	 * 获得所有的预订数量
	 * 
	 * @return
	 */
	Integer countBanquetReserve();
}
