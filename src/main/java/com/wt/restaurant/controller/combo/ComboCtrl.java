package com.wt.restaurant.controller.combo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wt.restaurant.entity.Combo;
import com.wt.restaurant.service.combo.IComboService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.ContextUtil;
import com.wt.restaurant.tool.MapUtils;
import com.wt.restaurant.tool.PageUtil;

@RestController("")
@RequestMapping("/combo")
public class ComboCtrl {
	@Autowired
	private IComboService comboservice;

	@RequestMapping(value = { "/listcombo", "/back/listcombo" }, method = RequestMethod.GET)
	public Map<String, Object> listcombo(@RequestParam("currentPageNo") Integer currentPageNo) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// 总数量（表）
		int totalCount = comboservice.countCombo();
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo, Constants.pageSizes);
		List<Combo> combo = comboservice.listCombo(currentPageNos, Constants.pageSizes);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("combos", combo);
		map.put("totalCount", totalCount);
		map.put("pageSize", Constants.pageSizes);
		return map;
	}

	@RequestMapping(value = { "/back/listallcombo" }, method = RequestMethod.GET)
	public Map<String, Object> listAllcombo() throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		List<Combo> combo = comboservice.listAllCombo();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("combos", combo);
		return map;
	}

	@RequestMapping(value = { "/back/updatecombo" }, method = RequestMethod.POST)
	public Map<String, Object> updatecombo(HttpServletRequest request, Combo combo,
			@RequestParam(value = "comboImg", required = false) MultipartFile file,
			@RequestParam(value = "comboImg2", required = false) MultipartFile file2) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = comboservice.updateCombo(combo, file,file2, staticsPath);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/savecombo" }, method = RequestMethod.POST)
	public Map<String, Object> savecombo(HttpServletRequest request, Combo combo,
			@RequestParam(value = "comboImg", required = false) MultipartFile file,
			@RequestParam(value = "comboImg2", required = false) MultipartFile file2) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = comboservice.saveCombo(combo, file, file2, staticsPath);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/removecombo" }, method = RequestMethod.DELETE)
	public Map<String, Object> removecombo(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = comboservice.removeCombo(id);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/getcombo" }, method = RequestMethod.GET)
	public Map<String, Object> getcombo(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("combo", comboservice.getCombo(id));
		return resultMap;
	}
}
