package com.wt.restaurant.servimpl.customer;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wt.restaurant.service.customer.ICode2OpenIdServ;
import com.wt.restaurant.tool.BusinessUtils;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.http.HttpRequest;
import com.wt.restaurant.tool.http.HttpRequestMethod;
import com.wt.restaurant.tool.http.requestcontenttype.ApplicationJsonContentType;

@Service
public class Code2OpenIdServImpl implements ICode2OpenIdServ {

	@Override
	public String getOpenID(String code) {
		String url = Constants.CODE2OPENIDURL+code;
		
		HttpRequest request = new HttpRequest(url, HttpRequestMethod.GET, new ApplicationJsonContentType());
		request.connectAndFetchResult();
		request.getResponseContent();
		JSONObject result = (JSONObject) JSON.toJSON(new Object());
		String openID = null;
		if(new Integer(Constants.HTTP_STATUS_200).equals(request.getResponseStatusCode())) {
			String content = (String) request.getResponseContent();
			try {
				result = JSON.parseObject(content);
				openID = result.getString("openid");
			}catch(Exception e) {
				BusinessUtils.throwNewBusinessException("获取openId失败");
			}
		}
		return openID;
	}

}
