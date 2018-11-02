package com.wt.restaurant.controller.tablereserve;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.restaurant.entity.TableReserve;
import com.wt.restaurant.service.tablereserve.ITableReserveService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.MapUtils;
import com.wt.restaurant.tool.PageUtil;

@RestController("")
@RequestMapping("/tablereserve")
public class TableReserveCtrl {
	@Autowired
	private ITableReserveService tablereserveservice;

	@RequestMapping(value = { "/back/listtablereserve" }, method = RequestMethod.GET)
	public Map<String, Object> listTableReserve(@RequestParam("currentPageNo") Integer currentPageNo) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// 总数量（表）
		int totalCount = tablereserveservice.countTableReserve();
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo, Constants.pageSizes);
		List<TableReserve> tablereserves = tablereserveservice.listTableReserve(currentPageNos, Constants.pageSizes);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("tablereserves", tablereserves);
		map.put("totalCount", totalCount);
		map.put("pageSize", Constants.pageSizes);
		return map;
	}

	@RequestMapping(value = { "/back/updatetablereserve" }, method = RequestMethod.PUT)
	public Map<String, Object> updateTableReserve(TableReserve tablereserve) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = tablereserveservice.updateTableReserve(tablereserve);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/savetablereserve" }, method = RequestMethod.POST)
	public Map<String, Object> saveTableReserve(@RequestBody() TableReserve tablereserve) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = tablereserveservice.saveTableReserve(tablereserve);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/removetablereserve" }, method = RequestMethod.DELETE)
	public Map<String, Object> removeTableReserve(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = tablereserveservice.removeTableReserve(id);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/gettablereserve" }, method = RequestMethod.GET)
	public Map<String, Object> getTableReserve(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		TableReserve tablereserve = tablereserveservice.getTableReserve(id);
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("tablereserve", tablereserve);
		return resultMap;
	}

	@RequestMapping(value = { "/listtablereservebycustomerid" }, method = RequestMethod.GET)
	public Map<String, Object> listTableReserveByCustomerId(@RequestParam("customerId") Integer customerId)
			throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		List<TableReserve> tablereserve = tablereserveservice.listTableReserveByCustomerId(customerId);
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("tablereserves", tablereserve);
		return resultMap;
	}
}