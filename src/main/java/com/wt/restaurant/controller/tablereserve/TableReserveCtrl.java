package com.wt.restaurant.controller.tablereserve;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wt.restaurant.entity.Customer;
import com.wt.restaurant.entity.TableReserve;
import com.wt.restaurant.service.tablereserve.ITableReserveService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.MapUtils;
import com.wt.restaurant.tool.PageUtil;
import com.wt.restaurant.tool.SpringContextUtils;
import com.wt.restaurant.websocket.ControllerHandlerBridge;
import com.wt.restaurant.websocket.entity.Message;
import com.wt.restaurant.websocket.entity.MessageType;

@RestController("")
@RequestMapping("/tablereserve")
public class TableReserveCtrl {
	@Autowired
	private ITableReserveService tablereserveservice;

	@RequestMapping(value = { "/back/listtablereserve" }, method = RequestMethod.GET)
	public Map<String, Object> listTableReserve(@RequestParam("currentPageNo") Integer currentPageNo,
			@RequestParam(value = "newReserveNum", required = false) Integer newReserveNum,
			@RequestParam(value = "queryString", required = false) String queryString) throws Exception {
		TableReserve tablereserve = null;
		if (null != queryString && queryString.length() > 0) {
			JSONObject jsonObj = (JSONObject) JSON.parse(URLDecoder.decode(queryString, "UTF-8"));
			tablereserve = jsonObj.toJavaObject(TableReserve.class);
			if (tablereserve.getCustomer() != null) {
				String nickname = tablereserve.getCustomer().getNickname();
				if (nickname != null && nickname.length() > 0) {
					Customer customer = new Customer();
					customer.setNickname(URLDecoder.decode(nickname, "UTF-8"));
					tablereserve.setCustomer(customer);
				}
			}
			String tablereserveType = tablereserve.getType();
			if (tablereserveType != null && tablereserveType.length() > 0) {
				tablereserve.setType(URLDecoder.decode(tablereserveType, "UTF-8"));
			}
		}

		Map<String, Object> map = MapUtils.getHashMapInstance();

/*		if(tablereserveType != null && "请选择".equals(tablereserveType)) {
			tablereserve.setType("包厢桌子");
		} */
		Integer pagesizes= PageUtil.getPageNum(newReserveNum);

		// 总数量（表）
		int totalCount = tablereserveservice.countTableReserve(tablereserve);
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo, pagesizes);
		List<TableReserve> tablereserves = tablereserveservice.listTableReserve(currentPageNos, pagesizes,
				tablereserve);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("tablereserves", tablereserves);
		map.put("totalCount", totalCount);
		map.put("pageSize", pagesizes);
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
		resultMap.put(Constants.STATUS,Constants.FAIL);
		if (flag) {
			resultMap.put(Constants.STATUS, Constants.SUCCESS);
			SpringContextUtils.getBeanByClass(ControllerHandlerBridge.class).notifyManager(new Message(MessageType.CODE_SCAN_ORDER));
		}
		return resultMap;
	}

	@RequestMapping(value = { "/back/removetablereserve" }, method = RequestMethod.DELETE)
	public Map<String, Object> removeTableReserve(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = tablereserveservice.removeTableReserve(id);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/gettablereserve/{id}" }, method = RequestMethod.GET)
	public Map<String, Object> getTableReserve(@PathVariable Integer id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		TableReserve tablereserve = tablereserveservice.getTableReserve(id);
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("tablereserve", tablereserve);
		return resultMap;
	}

	@RequestMapping(value = { "/listtablereservebycustomerid" }, method = RequestMethod.GET)
	public Map<String, Object> listTableReserveByCustomerId(HttpSession session,
			@RequestParam("customerId") Integer customerId) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		List<TableReserve> tablereserve = tablereserveservice.listTableReserveByCustomerId(customerId);
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("tablereserves", tablereserve);
		return resultMap;
	}
}
