package com.wt.restaurant.controller.banner;

import java.util.HashMap;
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

import com.wt.restaurant.entity.Banner;
import com.wt.restaurant.service.banner.IBannerService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.ContextUtil;
import com.wt.restaurant.tool.MapUtils;

@RestController("")
@RequestMapping("/banner")
public class BannerCtrl {
	@Autowired
	private IBannerService bannerService;

	@RequestMapping(value = { "/listbanner", "/back/listbanner" }, method = RequestMethod.GET)
	public Map<String, Object> listBanner() throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		List<Banner> banners = bannerService.listBanner();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("banners", banners);
		return map;
	}

	@RequestMapping(value = { "/back/updatebanner" }, method = RequestMethod.POST)
	public Map<String, Object> updateBanner(HttpServletRequest request,
			@RequestParam(value = "bannerImg", required = false) MultipartFile file, Banner banner) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = bannerService.updateBanner(banner, file, staticsPath);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/savebanner" }, method = RequestMethod.POST)
	public Map<String, Object> saveBanner(HttpServletRequest request,
			@RequestParam(value = "imgName", required = false) String imgName,
			@RequestParam(value = "file") MultipartFile[] file) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		// 获取图片的公共存储路径（例如：D:\ApacheTomcat7\apache-tomcat-7.0.53\webapps\statics）
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = bannerService.saveBanner(file, staticsPath, imgName);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/removebanner/{id}" }, method = RequestMethod.DELETE)
	public HashMap<String, String> removeBanner(@PathVariable Integer id) throws Exception {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		boolean flag = bannerService.removeBanner(id);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping(value = { "/back/getbanner" }, method = RequestMethod.GET)
	public Banner getBanner(@RequestParam("id") Integer id) {
		return bannerService.getBanner(id);
	}
}
