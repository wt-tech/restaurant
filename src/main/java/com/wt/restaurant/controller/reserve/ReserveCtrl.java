package com.wt.restaurant.controller.reserve;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.restaurant.entity.Reserve;
import com.wt.restaurant.service.reserve.IReserveService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.MapUtils;
import com.wt.restaurant.tool.PageUtil;

@RestController("")
@RequestMapping("/reserve")
public class ReserveCtrl {
	@Autowired
	private IReserveService reserveservice;

	@RequestMapping(value = { "/back/listreserve" }, method = RequestMethod.GET)
	public Map<String, Object> listReserve(@RequestParam("currentPageNo") Integer currentPageNo,
			@RequestParam(value = "reserve", required = false) Reserve reserve) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// 总数量（表）
		int totalCount = reserveservice.countReserve(reserve);
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo, Constants.pageSizes);
		List<Reserve> reserves = reserveservice.listReserve(currentPageNos, Constants.pageSizes, reserve);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("reserves", reserves);
		map.put("totalCount", totalCount);
		map.put("pageSize", Constants.pageSizes);
		return map;
	}

	@RequestMapping(value = { "/back/updatereserve" }, method = RequestMethod.PUT)
	public Map<String, Object> updateReserve(Reserve reserve) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = reserveservice.updateReserve(reserve);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/savereserve" }, method = RequestMethod.POST)
	public Map<String, Object> saveReserve(@RequestBody() Reserve reserve) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = reserveservice.saveReserve(reserve);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/removereserve" }, method = RequestMethod.DELETE)
	public Map<String, Object> removeReserve(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = reserveservice.removeReserve(id);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/getreserve" }, method = RequestMethod.GET)
	public Map<String, Object> getReserve(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		Reserve reserve = reserveservice.getReserve(id);
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("reserve", reserve);
		return resultMap;
	}

	@RequestMapping(value = { "/listreservebycustomerid" }, method = RequestMethod.GET)
	public Map<String, Object> listReserveByCustomerId(@RequestParam("customerId") Integer customerId)
			throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		List<Reserve> reserve = reserveservice.listReserveByCustomerId(customerId);
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("reserves", reserve);
		return resultMap;
	}
}
