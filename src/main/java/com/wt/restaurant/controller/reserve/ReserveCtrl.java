package com.wt.restaurant.controller.reserve;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.wt.restaurant.entity.Reserve;
import com.wt.restaurant.service.reserve.IReserveService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.MapUtils;
import com.wt.restaurant.tool.PageUtil;
import com.wt.restaurant.websocket.ControllerHandlerBridge;
import com.wt.restaurant.websocket.entity.Message;
import com.wt.restaurant.websocket.entity.MessageType;

@RestController("")
@RequestMapping("/reserve")
public class ReserveCtrl implements ApplicationContextAware {
	@Autowired
	private IReserveService reserveservice;
	
	@RequestMapping(value = { "/back/listreserve" }, method = RequestMethod.GET)
	public Map<String, Object> listReserve(@RequestParam("currentPageNo") Integer currentPageNo,
	    Reserve reserve,@RequestParam(value = "newReserveNum", required = false) Integer newReserveNum) throws Exception {
		String name = reserve.getReservationsName();
		if(name != null && name.length() > 0) {
			reserve.setReservationsName(URLDecoder.decode(name, "UTF-8"));
		} 
		String reservationType=reserve.getReservationType();
		if(reservationType != null && reservationType.length() > 0) {
			reserve.setReservationType(URLDecoder.decode(reservationType, "UTF-8"));
		} 
		String reservationsMode=reserve.getReservationsMode();
		if(reservationsMode != null && reservationsMode.length() > 0) {
			reserve.setReservationsMode(URLDecoder.decode(reservationsMode, "UTF-8"));
		} 
		Map<String, Object> map = MapUtils.getHashMapInstance();
		Integer pagesizes= PageUtil.getPageNum(newReserveNum);
		// 总数量（表）
		int totalCount = reserveservice.countReserve(reserve);
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo, pagesizes);
		List<Reserve> reserves = reserveservice.listReserve(currentPageNos, pagesizes, reserve);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("reserves", reserves);
		map.put("totalCount", totalCount);
		map.put("pageSize", pagesizes);
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
//		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		resultMap.put(Constants.STATUS,Constants.FAIL);
		if(flag) {
			resultMap.put(Constants.STATUS, Constants.SUCCESS);
			context.getBean(ControllerHandlerBridge.class).notifyManager(new Message(MessageType.BOX_RESERVE));
		}
		return resultMap;
	}

	@RequestMapping(value = { "/back/removereserve" }, method = RequestMethod.DELETE)
	public Map<String, Object> removeReserve(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = reserveservice.removeReserve(id);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/getreserve/{id}" }, method = RequestMethod.GET)
	public Map<String, Object> getReserve(@PathVariable Integer id) throws Exception {
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

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context=(WebApplicationContext) applicationContext;
	}
	private WebApplicationContext context;
	
}
