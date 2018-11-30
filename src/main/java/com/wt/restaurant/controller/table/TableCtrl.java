package com.wt.restaurant.controller.table;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.restaurant.entity.Table;
import com.wt.restaurant.service.minicode.IMiniProgramCodeServ;
import com.wt.restaurant.service.table.ITableService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.ContextUtil;
import com.wt.restaurant.tool.MapUtils;
import com.wt.restaurant.tool.PageUtil;

@RestController("")
@RequestMapping("/table")
public class TableCtrl {
	@Autowired
	private ITableService tableservice;
	@Autowired
	private IMiniProgramCodeServ codeService;

	@RequestMapping(value = { "/listtable", "/back/listtable" }, method = RequestMethod.GET)
	public Map<String, Object> listTable(@RequestParam(value = "currentPageNo", required = false) Integer currentPageNo,
			@RequestParam(value = "tableNumber", required = false) String number) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		List<Table> table = new ArrayList<Table>();
		if (number != null && number.length() > 0) {
			// 考虑到后期number可能会添加汉字.前端使用了encodeURI进行包装.
			number = URLDecoder.decode(number, "UTF-8");
		}

		// 总数量（表）
		int totalCount = tableservice.countTable(number);
		if (null == currentPageNo) {
			table = tableservice.listTable(1, totalCount, number);
		} else {
			Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo, Constants.pageSizes);
			table = tableservice.listTable(currentPageNos, Constants.pageSizes, number);
		}
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("tables", table);
		map.put("totalCount", totalCount);
		map.put("pageSize", Constants.pageSizes);
		return map;
	}

	@RequestMapping(value = { "/back/updatetable" }, method = RequestMethod.POST)
	public Map<String, Object> updateTable(HttpServletRequest request, Table table) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = tableservice.updateTable(table);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/savetable" }, method = RequestMethod.POST)
	public Map<String, Object> saveTable(HttpServletRequest request, Table table) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		// String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = tableservice.saveTable(table);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/removetable/{id}" }, method = RequestMethod.DELETE)
	public Map<String, Object> removeTable(@PathVariable("id") Integer id,HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		Table table = tableservice.getTable(id);
		String absoluteDirectory = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = tableservice.removeTable(id);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		if(flag)
			codeService.removeTableMiniProgramCode(table,absoluteDirectory);
			
		return resultMap;
	}

	@RequestMapping(value = { "/back/gettable" }, method = RequestMethod.GET)
	public Map<String, Object> getTable(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("table", tableservice.getTable(id));
		return resultMap;
	}
	
	@RequestMapping(value = { "/back/tablecheck" }, method = RequestMethod.GET)
	public Map<String, Object> checkIfTableExist(@RequestParam(value = "tableNumber",required = true) String tableNumber) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		Table table = tableservice.getTableByTableNumber(tableNumber);
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("existFlag",null == table ? false : true);
		return resultMap;
	}
	
}
