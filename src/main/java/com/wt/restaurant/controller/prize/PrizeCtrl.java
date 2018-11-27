package com.wt.restaurant.controller.prize;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wt.restaurant.service.prize.IPrizeService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.MapUtils;

@RestController
@RequestMapping("prize")
public class PrizeCtrl {

	@Autowired
	private IPrizeService prizeServ;
	
	@RequestMapping("/list")
	public Map<String,Object> listPrize(){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS,Constants.SUCCESS);
		map.put("prizeList", prizeServ.listPrize());
		return map;
	}
	
}
