package com.wt.restaurant.controller.global;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.restaurant.entity.GlobalInfo;
import com.wt.restaurant.service.global.IGlobalInfoService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.MapUtils;

@RestController
public class GlobalInfoCtrl {

	@Autowired
	private IGlobalInfoService globalInfoServ;
	
	@RequestMapping("gvalue")
	public Map<String,Object> getValueByKey(@RequestParam String key){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS,Constants.SUCCESS);
		map.put("value", globalInfoServ.getGlobalInfo(key));
		return map;
	}
	
	@RequestMapping(value="uvalue",method = RequestMethod.POST)
	public Map<String,Object> updateGlobalInfo(GlobalInfo globalInfo){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS,globalInfoServ.updateGlobalInfo(globalInfo)?Constants.SUCCESS:Constants.FAIL);
		return map;
	}
	
	
}
