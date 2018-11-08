package com.wt.restaurant.controller.banquetreserve;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.restaurant.entity.BanquetReserve;
import com.wt.restaurant.service.banquetreserve.IBanquetReserveService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.MapUtils;
import com.wt.restaurant.tool.PageUtil;

@RestController("")
@RequestMapping("/banquetreserve")
public class BanquetReserveCtrl {
	@Autowired
	private IBanquetReserveService banquetreserveservice;

	@RequestMapping(value = { "/back/listbanquetreserve" }, method = RequestMethod.GET)
	public Map<String, Object> listBanquetReserve(@RequestParam("currentPageNo") Integer currentPageNo,
			@RequestParam(value = "newReserveNum", required = false) Integer newReserveNum) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		Integer pagesizes= PageUtil.getPageNum(newReserveNum);
		// 总数量（表）
		int totalCount = banquetreserveservice.countBanquetReserve();
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo, pagesizes);
		List<BanquetReserve> banquetreserves = banquetreserveservice.listBanquetReserve(currentPageNos,
				pagesizes);
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
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
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
}
