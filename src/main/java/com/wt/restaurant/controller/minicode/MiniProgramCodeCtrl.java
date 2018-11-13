package com.wt.restaurant.controller.minicode;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wt.restaurant.entity.MiniProgramCodeParam;
import com.wt.restaurant.service.minicode.IMiniProgramCodeServ;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.ContextUtil;
import com.wt.restaurant.tool.ImageUtils;
import com.wt.restaurant.tool.MapUtils;

@RestController
@RequestMapping("code")
public class MiniProgramCodeCtrl {

	@Autowired
	private IMiniProgramCodeServ service;
	
	@RequestMapping(value="/back/accesstoken",method=RequestMethod.GET)
	public Map<String,Object> fetchAccessToken() {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("result", service.fetchAccessToken());
		return map;
	}
	
	@RequestMapping(value="/back/wxcode",method=RequestMethod.POST)
	public Map<String,Object> getImg(
			@RequestBody()
			MiniProgramCodeParam param,HttpServletRequest request){
		System.err.println(param);
		String absoluteDirectory = ContextUtil.getStaticResourceAbsolutePath(request);
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.FAIL);
		if(service.fetchWXACodeUnlimit(param,absoluteDirectory)) {
			map.put(Constants.STATUS, Constants.SUCCESS);
			map.put("url", Constants.imgServerDomain + ImageUtils.getMiniQRVirtualURI(param));
		}
		return map;
	}
	
}