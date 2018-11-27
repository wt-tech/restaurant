package com.wt.restaurant.entity;

import java.util.Date;

public class AccessToken {
	
	private String access_token;
	private Integer expires_in;
	private Date timeBeginTakeEffect;//开始生效的时间.
	
	public AccessToken() {
		
	}
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	public Date getTimeBeginTakeEffect() {
		return timeBeginTakeEffect;
	}
	public void setTimeBeginTakeEffect(Date timeBeginTakeEffect) {
		this.timeBeginTakeEffect = timeBeginTakeEffect;
	}
	
	/**
	 * 判断当前AccessToken是否有效.
	 * @return
	 */
	public boolean isValid() {
		return this.getExpires_in() > ((new Date().getTime() - this.getTimeBeginTakeEffect().getTime())/1000);
	}
	
	
}
