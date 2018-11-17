package com.wt.restaurant.controller.dishorder;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.restaurant.entity.DishOrder;
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
	public Map<String, Object> listDishOrder(@RequestParam("currentPageNo") Integer currentPageNo) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// 总数量（表）
		int totalCount = dishorderservice.countDishOrder();
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo, Constants.pageSizes);
		List<DishOrder> dishorders = dishorderservice.listDishOrder(currentPageNos, Constants.pageSizes);
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

}
