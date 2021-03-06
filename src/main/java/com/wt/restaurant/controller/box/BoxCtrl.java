package com.wt.restaurant.controller.box;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.wt.restaurant.entity.Box;
import com.wt.restaurant.entity.Reserve;
import com.wt.restaurant.entity.Table;
import com.wt.restaurant.service.box.IBoxService;
import com.wt.restaurant.service.minicode.IMiniProgramCodeServ;
import com.wt.restaurant.service.reserve.IReserveService;
import com.wt.restaurant.tool.CompareBox;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.ContextUtil;
import com.wt.restaurant.tool.MapUtils;
import com.wt.restaurant.tool.PageUtil;

@RestController("")
@RequestMapping("/box")
public class BoxCtrl {
	@Autowired
	private IBoxService boxservice;
	@Autowired
	private IReserveService reserveservice;
	@Autowired
	private IMiniProgramCodeServ codeService;

	@RequestMapping(value = { "/listnullbox" }, method = RequestMethod.GET)
	public Map<String, Object> listNullBox(
			@RequestParam(value = "currentPageNo", required = false) Integer currentPageNo,
			@RequestParam(value = "roomNumber", required = false) Integer roomNumber,
			@RequestParam("reservationsStartTime") Date reservationsStartTime) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();

		Reserve reserve = new Reserve();
		List<Box> box = new ArrayList<Box>();
		reserve.setReservationsStartTime(reservationsStartTime);
		// 根据传过来的时间筛选出预订的总数量（表）
		int totalCount = reserveservice.countReserve(reserve);
		// 根据传过来的时间查询出所有的预订
		List<Reserve> reserves = reserveservice.listReserve(1, totalCount, reserve);
		// 查询出午餐预订的所有包厢
		List<Box> singlelistbox = CompareBox.LuncheonReserve(reserves);
		// 查询出晚餐预订的所有包厢
		List<Box> pairlistbox = CompareBox.DinnerReserve(reserves);

		// 包厢总数量（表）
		int totalCounts = boxservice.countBox(roomNumber);
		if (null != currentPageNo && currentPageNo > 0) {
			Integer currentPageNos = new PageUtil().Page(totalCounts, currentPageNo, Constants.pageSizes);
			// 查询出当前页的包厢
			box = boxservice.listBox(currentPageNos, Constants.pageSizes, roomNumber);
		} else {
			// 查询出所有的包厢
			box = boxservice.listBox(1, totalCounts, roomNumber);
		}
		// 给当前页的每个包厢设置预订状态(0预订午餐,1预订晚餐,2午餐和晚餐均预订,3午餐和晚餐均没预订)
		List<Box> boxs = CompareBox.LuncheonDinnerReserve(singlelistbox, pairlistbox, box);

		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("boxs", boxs);
		map.put("totalCounts", totalCounts);
		map.put("pageSize", Constants.pageSizes);
		return map;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// 转换日期
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
	}

	@RequestMapping(value = { "/listbox", "/back/listbox" }, method = RequestMethod.GET)
	public Map<String, Object> listBox(@RequestParam(value = "reserveStatus", required = false) Integer reserveStatus,
			@RequestParam(value = "currentPageNo", required = false) Integer currentPageNo,
			@RequestParam(value = "roomNumber", required = false) Integer roomNumber) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		List<Box> box = new ArrayList<Box>();
		// 总数量（表）
		int totalCount = boxservice.countBox(roomNumber);
		if (null == currentPageNo) {
			box = boxservice.listBox(1, totalCount, roomNumber);
		} else {
			Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo, Constants.pageSizes);
			box = boxservice.listBox(currentPageNos, Constants.pageSizes, roomNumber);
		}
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("boxs", box);
		map.put("totalCount", totalCount);
		map.put("pageSize", Constants.pageSizes);
		return map;
	}

	@RequestMapping(value = { "/back/updatebox" }, method = RequestMethod.POST)
	public Map<String, Object> updateBox(HttpServletRequest request, Box box,
			@RequestParam(value = "boxImg", required = false) MultipartFile file) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = boxservice.updateBox(box, file, staticsPath);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/savebox" }, method = RequestMethod.POST)
	public Map<String, Object> saveBox(HttpServletRequest request, Box box,
			@RequestParam(value = "boxImg", required = false) MultipartFile file) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = boxservice.saveBox(box, file, staticsPath);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/removebox/{id}" }, method = RequestMethod.DELETE)
	public Map<String, Object> removeBox(@PathVariable("id") Integer id,HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		Box box = boxservice.getBox(id);
		boolean flag = boxservice.removeBox(id);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		String absoluteDirectory = ContextUtil.getStaticResourceAbsolutePath(request);
		if(flag)
			codeService.removeBoxMiniProgramCode(box,absoluteDirectory);
		return resultMap;
	}

	@RequestMapping(value = { "/back/getbox" }, method = RequestMethod.GET)
	public Map<String, Object> getBox(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("box", boxservice.getBox(id));
		return resultMap;
	}
	
	@RequestMapping(value = { "/back/boxcheck" }, method = RequestMethod.GET)
	public Map<String, Object> checkIfBoxExist(@RequestParam(value = "roomNumber",required = true) Integer roomNumber) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		Box box = boxservice.getBoxByBoxNumber(roomNumber);
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("existFlag",null == box ? false : true);
		return resultMap;
	}
}
