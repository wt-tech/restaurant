package com.wt.restaurant.controller.classification;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.restaurant.entity.Classification;
import com.wt.restaurant.service.classification.IClassificationService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.MapUtils;

@RestController("")
@RequestMapping("/classification")
public class ClassificationCtrl {
	@Autowired
	private IClassificationService classificationservice;

	@RequestMapping(value = { "/listclassification", "/back/listclassification" }, method = RequestMethod.GET)
	public Map<String, Object> listClassification() throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		List<Classification> classification = classificationservice.listClassification();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("classifications", classification);
		return map;
	}

	@RequestMapping(value = { "/back/updateclassification" }, method = RequestMethod.PUT)
	public Map<String, Object> updateClassification(Classification classification) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = classificationservice.updateClassification(classification);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/saveclassification" }, method = RequestMethod.POST)
	public Map<String, Object> saveClassification(Classification classification) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = classificationservice.saveClassification(classification);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/removeclassification" }, method = RequestMethod.DELETE)
	public Map<String, Object> removeClassification(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = classificationservice.removeClassification(id);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/getclassification" }, method = RequestMethod.GET)
	public Map<String, Object> getClassification(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("classification", classificationservice.getClassification(id));
		return resultMap;
	}

}
