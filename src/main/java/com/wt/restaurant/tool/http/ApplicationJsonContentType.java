package com.wt.restaurant.tool.http;

import com.alibaba.fastjson.JSON;

public class ApplicationJsonContentType implements RequestContentType{

	private Object params;
	public ApplicationJsonContentType(Object params) {
		this.params = params;
	}

	
	@Override
	public String assembleParams() {
		return JSON.toJSONString(params);
	}

	@Override
	public String getContentType() {
		return "application/json";
	}
}
