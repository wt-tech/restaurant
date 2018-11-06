package com.wt.restaurant.controller.table;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wt.restaurant.entity.Table;
import com.wt.restaurant.service.table.ITableService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.MapUtils;
import com.wt.restaurant.tool.PageUtil;

@RestController("")
@RequestMapping("/table")
public class TableCtrl {
	@Autowired
	private ITableService tableservice;

	@RequestMapping(value = { "/listtable","/back/listtable" }, method = RequestMethod.GET)
	public Map<String, Object> listTable(@RequestParam("currentPageNo") Integer currentPageNo) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// 总数量（表）
		int totalCount = tableservice.countTable();
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo, Constants.pageSizes);
		List<Table> table = tableservice.listTable(currentPageNos, Constants.pageSizes);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("tables", table);
		map.put("totalCount", totalCount);
		map.put("pageSize", Constants.pageSizes);
		return map;
	}

	@RequestMapping(value = { "/back/updatetable" }, method = RequestMethod.PUT)
	public Map<String, Object> updateTable(HttpServletRequest request, Table table,
			@RequestParam(value = "tableImg", required = false) MultipartFile[] file) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = tableservice.updateTable(table);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/savetable" }, method = RequestMethod.POST)
	public Map<String, Object> saveTable(HttpServletRequest request, Table table,
			@RequestParam(value = "tableImg", required = false) MultipartFile[] file) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		//String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = tableservice.saveTable(table);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/removetable" }, method = RequestMethod.DELETE)
	public Map<String, Object> removeTable(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = tableservice.removeTable(id);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/gettable" }, method = RequestMethod.GET)
	public Map<String, Object> getTable(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("table", tableservice.getTable(id));
		return resultMap;
	}
}
