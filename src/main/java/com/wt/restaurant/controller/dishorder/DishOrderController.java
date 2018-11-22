package com.wt.restaurant.controller.dishorder;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.restaurant.entity.Box;
import com.wt.restaurant.entity.DishOrder;
import com.wt.restaurant.entity.DishOrderLine;
import com.wt.restaurant.service.dishorder.IDishOrderService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.MapUtils;
import com.wt.restaurant.tool.PageUtil;

@RestController("")
@RequestMapping("/dishorder")
public class DishOrderController {
	@Autowired
	private IDishOrderService dishorderservice;

	@RequestMapping(value = { "/back/listdishorder" }, method = RequestMethod.GET)
	public Map<String, Object> listDishOrder(@RequestParam("currentPageNo") Integer currentPageNo,DishOrder dishorder) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		String reservationType = dishorder.getReserveType();
		if (reservationType != null && reservationType.length() > 0) {
			dishorder.setReserveType(URLDecoder.decode(reservationType, "UTF-8"));
		}
		// 总数量（表）
		int totalCount = dishorderservice.countDishOrder(dishorder);
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo, Constants.pageSizes);
		List<DishOrder> dishorders = dishorderservice.listDishOrder(currentPageNos, Constants.pageSizes,dishorder);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("dishorders", dishorders);
		map.put("totalCount", totalCount);
		map.put("pageSize", Constants.pageSizes);
		return map;
	}

	@RequestMapping(value = { "/back/listdishorderbycustomerid" }, method = RequestMethod.GET)
	public Map<String, Object> listDishOrderByCustomerId(@RequestParam("customerId") Integer customerId)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		List<DishOrder> dishorder = dishorderservice.listDishOrderByCustomerId(customerId);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("dishorder", dishorder);
		return map;
	}
	
	@RequestMapping(value = { "/back/listdishordermenu/{dishorderId}" }, method = RequestMethod.GET)
	public Map<String, Object> listDishOrderMenu(@PathVariable("dishorderId") Integer dishorderId)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		List<DishOrderLine> dishordermenu = dishorderservice.listDishOrderMenu(dishorderId);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("dishordermenu", dishordermenu);
		return map;
	}
	
	@RequestMapping(value = { "/back/listdishorderbox/{dishorderId}" }, method = RequestMethod.GET)
	public Map<String, Object> listDishOrderBox(@PathVariable("dishorderId") Integer dishorderId)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		List<Box> box = dishorderservice.listDishOrderBox(dishorderId);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("dishorderbox", box);
		return map;
	}
	
	@RequestMapping(value = { "/back/updatedishorderremark" }, method = RequestMethod.POST)
	public Map<String, Object> updateDishOrderRemark(DishOrder dishorder)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag = dishorderservice.updateDishOrderRemark(dishorder.getId(),dishorder.getRemark());
		map.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return map;
	}
	
	@RequestMapping(value = { "/back/updatedishorderstatus" }, method = RequestMethod.POST)
	public Map<String, Object> updateDishOrderStatus(DishOrder dishorder)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag = dishorderservice.updateDishOrderStatus(dishorder.getId(),dishorder.getTotalPayAmount());
		map.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return map;
	}
	
	@RequestMapping(value = { "/back/getdishorderremark/{id}" }, method = RequestMethod.GET)
	public Map<String, Object> getDishOrderRemark(@PathVariable("id") Integer id)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		DishOrder dishorder = dishorderservice.getDishOrderRemark(id);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("dishorder", dishorder);
		return map;
	}

}
