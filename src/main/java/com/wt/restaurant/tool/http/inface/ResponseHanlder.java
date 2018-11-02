package com.wt.restaurant.tool.http.inface;

import java.io.IOException;
import java.net.HttpURLConnection;

import com.wt.restaurant.tool.BusinessUtils;

public abstract class ResponseHanlder {

	protected HttpURLConnection connection;
	
	private Integer code = -1;
	private Object content = null;
	
	public void fetchStatusCode() {
		Integer code = -1;
		try {
			code = this.connection.getResponseCode();
		} catch (IOException e) {
			BusinessUtils.throwNewBusinessException("获取statusCode失败"+e.getMessage());
		}
		this.setCode(code);
	}
	public abstract void fetchContent();
	
	public void fetchInfo() {
		this.fetchStatusCode();
		this.fetchContent();
	}
	
	
	public Integer getCode() {
		return code;
	}
	public Object getContent() {
		return content;
	}
	protected void setCode(Integer code) {
		this.code = code;
	}
	protected void setContent(Object content) {
		this.content = content;
	}
	
	public void setConnection(HttpURLConnection connection) {
		this.connection = connection;
	}
	
}
