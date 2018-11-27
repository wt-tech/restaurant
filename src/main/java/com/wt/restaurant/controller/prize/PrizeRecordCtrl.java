package com.wt.restaurant.controller.prize;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.restaurant.entity.PrizeRecord;
import com.wt.restaurant.service.prize.IPrizeRecordService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.MapUtils;

@RestController
@RequestMapping("prize")
public class PrizeRecordCtrl {

	@Autowired
	private IPrizeRecordService recordServImpl;
	
	@RequestMapping(value="/record",method=RequestMethod.GET)
	public Map<String,Object> getPrize(@RequestParam Integer id){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS,Constants.SUCCESS);
		map.put("prizeRecord", recordServImpl.getPrizeRecord(id));
		return map;
	}
	
	@RequestMapping(value="/records",method=RequestMethod.GET)
	public Map<String,Object> listPrizeByCustomerId(@RequestParam Integer customerId){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS,Constants.SUCCESS);
		map.put("prizeRecordList", recordServImpl.listPrizeRecordByCustomerId(customerId));
		return map;
	}
	
	@RequestMapping(value="/record",method=RequestMethod.POST)
	public Map<String,Object> addRecord(@RequestBody PrizeRecord prizeRecord){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, recordServImpl.savePrizeRecord(prizeRecord)?Constants.SUCCESS : Constants.FAIL);
		return map;
	}
	
	
	
}
