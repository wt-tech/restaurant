package com.wt.restaurant.controller.menu;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.wt.restaurant.entity.Menu;
import com.wt.restaurant.service.menu.IMenuService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.ContextUtil;
import com.wt.restaurant.tool.MapUtils;
import com.wt.restaurant.tool.PageUtil;

@RestController("")
@RequestMapping("/menu")
public class MenuCtrl {
	@Autowired
	private IMenuService menuservice;

	/**
	 * 小程序端 没有做分页.
	 */
	@RequestMapping(value = { "/listmenu", "/back/listmenu" }, method = RequestMethod.GET)
	public Map<String, Object> listMenu(
			@RequestParam(value="classificationId",required = true) Integer classificationId,
			@RequestParam(value="menuName",required = false) String name,
			@RequestParam(value = "currentPageNo", required = false) Integer currentPageNo
			) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		
		if(name != null && name.length()>0)
			name = URLDecoder.decode(name, "UTF-8");
		
		Integer currentPageNos = null;
		if(currentPageNo != null) {
			int totalCount = menuservice.countMenu(classificationId,name);
			currentPageNos = new PageUtil().Page(totalCount, currentPageNo, Constants.pageSizes);
			map.put("totalCount", totalCount);		
			map.put("pageSize", Constants.pageSizes);
		}
		
		List<Menu> menu = menuservice.listMenu(classificationId,currentPageNos,name);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("menus", menu);
		return map;
	}

	@RequestMapping(value = { "/back/updatemenu" }, method = RequestMethod.POST)
	public Map<String, Object> updateMenu(HttpServletRequest request, String jsonMenu,
			@RequestParam(value = "menuImg", required = false) MultipartFile file) throws Exception {
		String rs = URLDecoder.decode(jsonMenu, "UTF-8");
		Menu menu = JSON.parseObject(rs, Menu.class);
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = menuservice.updateMenu(menu, file, staticsPath);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/savemenu" }, method = RequestMethod.POST)
	public Map<String, Object> saveMenu(HttpServletRequest request, String jsonMenu,
			@RequestParam(value = "menuImg", required = false) MultipartFile file) throws Exception {
		String rs = URLDecoder.decode(jsonMenu, "UTF-8");
		Menu menu = JSON.parseObject(rs, Menu.class);
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = menuservice.saveMenu(menu, file, staticsPath);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/removemenu/{id}" }, method = RequestMethod.DELETE)
	public Map<String, Object> removeMenu(@PathVariable("id") Integer id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		boolean flag = menuservice.removeMenu(id);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/getmenu" }, method = RequestMethod.GET)
	public Map<String, Object> getMenu(@RequestParam("id") int id) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		resultMap.put(Constants.STATUS, Constants.SUCCESS);
		resultMap.put("menu", menuservice.getMenu(id));
		return resultMap;
	}
	
	/**
	 * 根据菜名模糊查询
	 * @param pageNo
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/back/listmenubyname" }, method = RequestMethod.GET)
	public Map<String, Object> listMenuByName(
			@RequestParam(value="currentPageNo",required = true) Integer pageNo,
			@RequestParam(value="menuName",required = true) String name) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		List<Menu> menus = null;
		if(null != name && name.length() > 0)
			name = URLDecoder.decode(name, "UTF-8");

		Integer totalCount = menuservice.listMenuCountByName(name);
		Integer currentPageNos = new PageUtil().Page(totalCount, pageNo, Constants.pageSizes);
		menus = menuservice.listMenuByName(name,currentPageNos,Constants.pageSizes);
		map.put("totalCount", totalCount);
		map.put("pageSize", Constants.pageSizes);
		
		map.put("menus", menus);
		return map;
	}
}
