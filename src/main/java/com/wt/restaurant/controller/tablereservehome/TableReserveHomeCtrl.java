package com.wt.restaurant.controller.tablereservehome;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.restaurant.entity.TableReserveHome;
import com.wt.restaurant.service.tablereservehome.ITableReserveHomeService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.MapUtils;
import com.wt.restaurant.tool.PageUtil;
import com.wt.restaurant.tool.SpringContextUtils;
import com.wt.restaurant.websocket.ControllerHandlerBridge;
import com.wt.restaurant.websocket.entity.Message;
import com.wt.restaurant.websocket.entity.MessageType;

@RestController("")
@RequestMapping("/tablereservehome")
public class TableReserveHomeCtrl {
	@Autowired
	private ITableReserveHomeService tablereservehomeservice;

	@RequestMapping(value = { "/back/listtablereservehome" }, method = RequestMethod.GET)
	public Map<String, Object> listTableReserveHome(@RequestParam("currentPageNo") Integer currentPageNo,
			TableReserveHome tablereservehome,
			@RequestParam(value = "newReserveNum", required = false) Integer newReserveNum) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		String name = tablereservehome.getReservationsName();
		if (name != null && name.length() > 0) {
			tablereservehome.setReservationsName(URLDecoder.decode(name, "UTF-8"));
		}
		Integer pagesizes = PageUtil.getPageNum(newReserveNum);
		// 总数量（表）
		int totalCount = tablereservehomeservice.countTableReserveHome(tablereservehome);
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo, pagesizes);
		List<TableReserveHome> tablereservehomes = tablereservehomeservice.listTableReserveHome(currentPageNos,
				pagesizes, tablereservehome);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("tablereservehomes", tablereservehomes);
		map.put("totalCount", totalCount);
		map.put("pageSize", pagesizes);
		return map;
	}

	@RequestMapping(value = { "/back/updatetablereservehome" }, method = RequestMethod.PUT)
	public Map<String, Object> updateTableReserveHome(TableReserveHome tablereservehome) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = tablereservehomeservice.updateTableReserveHome(tablereservehome);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/savetablereservehome" }, method = RequestMethod.POST)
	public Map<String, Object> saveTableReserveHome(@RequestBody() TableReserveHome tablereservehome) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = tablereservehomeservice.saveTableReserveHome(tablereservehome);
		resultMap.put(Constants.STATUS,Constants.FAIL);
		if (flag) {
			resultMap.put(Constants.STATUS, Constants.SUCCESS);
			SpringContextUtils.getBeanByClass(ControllerHandlerBridge.class).notifyManager(new Message(MessageType.TABLE_RESERVE));
		}
		return resultMap;
	}

	@RequestMapping(value = { "/back/removetablereservehome" }, method = RequestMethod.DELETE)
	public Map<String, Object> removeTableReserveHome(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = tablereservehomeservice.removeTableReserveHome(id);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/gettablereservehome/{id}" }, method = RequestMethod.GET)
	public Map<String, Object> getTableReserveHome(@PathVariable Integer id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		TableReserveHome tablereservehome = tablereservehomeservice.getTableReserveHome(id);
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("tablereservehome", tablereservehome);
		return resultMap;
	}

	@RequestMapping(value = { "/listtablereservehomebycustomerid" }, method = RequestMethod.GET)
	public Map<String, Object> listTableReserveHomeByCustomerId(@RequestParam("customerId") Integer customerId)
			throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		List<TableReserveHome> tablereservehome = tablereservehomeservice.listTableReserveHomeByCustomerId(customerId);
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("tablereservehomes", tablereservehome);
		return resultMap;
	}

	@RequestMapping(value = { "/back/updatetablenum" }, method = RequestMethod.POST)
	public Map<String, Object> updateTableNum(TableReserveHome tablereservehome) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag = tablereservehomeservice.updateTableNum(tablereservehome.getId(), tablereservehome.getTableNum());
		map.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return map;
	}

}
