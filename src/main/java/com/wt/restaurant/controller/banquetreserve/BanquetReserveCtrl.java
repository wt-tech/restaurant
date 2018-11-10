package com.wt.restaurant.controller.banquetreserve;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.wt.restaurant.entity.BanquetReserve;
import com.wt.restaurant.service.banquetreserve.IBanquetReserveService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.MapUtils;
import com.wt.restaurant.tool.PageUtil;
import com.wt.restaurant.websocket.ControllerHandlerBridge;
import com.wt.restaurant.websocket.entity.Message;
import com.wt.restaurant.websocket.entity.MessageType;

@RestController("")
@RequestMapping("/banquetreserve")
public class BanquetReserveCtrl implements ApplicationContextAware{
	@Autowired
	private IBanquetReserveService banquetreserveservice;

	@RequestMapping(value = { "/back/listbanquetreserve" }, method = RequestMethod.GET)
	public Map<String, Object> listBanquetReserve(@RequestParam("currentPageNo") Integer currentPageNo,
			BanquetReserve banquetreserve,
			@RequestParam(value = "newReserveNum", required = false) Integer newReserveNum) throws Exception {
		String name = banquetreserve.getReservationsName();
		if(name != null && name.length() > 0) {
			banquetreserve.setReservationsName(URLDecoder.decode(name, "UTF-8"));
		} 
		Map<String, Object> map = MapUtils.getHashMapInstance();
		Integer pagesizes= PageUtil.getPageNum(newReserveNum);
		// 总数量（表）
		int totalCount = banquetreserveservice.countBanquetReserve(banquetreserve);
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo, pagesizes);
		List<BanquetReserve> banquetreserves = banquetreserveservice.listBanquetReserve(currentPageNos,
				pagesizes,banquetreserve);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("banquetreserves", banquetreserves);
		map.put("totalCount", totalCount);
		map.put("pageSize", pagesizes);
		return map;
	}

	@RequestMapping(value = { "/back/updatebanquetreserve" }, method = RequestMethod.PUT)
	public Map<String, Object> updateBanquetReserve(BanquetReserve banquetreserve) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = banquetreserveservice.updateBanquetReserve(banquetreserve);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/savebanquetreserve" }, method = RequestMethod.POST)
	public Map<String, Object> saveBanquetReserve(@RequestBody() BanquetReserve banquetreserve) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = banquetreserveservice.saveBanquetReserve(banquetreserve);
//		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		resultMap.put(Constants.STATUS,Constants.FAIL);
		if(flag) {
			resultMap.put(Constants.STATUS,Constants.SUCCESS);
			context.getBean(ControllerHandlerBridge.class).notifyManager(new Message(MessageType.BANQUET_RESERVE));
		}
		return resultMap;
	}

	@RequestMapping(value = { "/back/removebanquetreserve" }, method = RequestMethod.DELETE)
	public Map<String, Object> removeBanquetReserve(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = banquetreserveservice.removeBanquetReserve(id);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/getbanquetreserve" }, method = RequestMethod.GET)
	public Map<String, Object> getBanquetReserve(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		BanquetReserve banquetreserve = banquetreserveservice.getBanquetReserve(id);
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("banquetreserve", banquetreserve);
		return resultMap;
	}

	@RequestMapping(value = { "/listbanquetreservebycustomerid" }, method = RequestMethod.GET)
	public Map<String, Object> listBanquetReserveByCustomerId(@RequestParam("customerId") Integer customerId)
			throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		List<BanquetReserve> banquetreserve = banquetreserveservice.listBanquetReserveByCustomerId(customerId);
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("banquetreserves", banquetreserve);
		return resultMap;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = (WebApplicationContext)applicationContext;
	}
	private WebApplicationContext context;
}
