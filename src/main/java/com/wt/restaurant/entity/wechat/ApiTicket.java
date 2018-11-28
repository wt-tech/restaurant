package com.wt.restaurant.entity.wechat;

import java.util.Date;

import com.alibaba.fastjson.JSON;

public class ApiTicket {

	private Integer errcode;
	private String errmsg ;
	private String ticket ;
	private Integer expires_in ;
	private Date timeBeginTakeEffect;//开始生效的时间.
	
	
	public ApiTicket() {
		this.setTimeBeginTakeEffect(new Date());
	}
	
	
	public Date getTimeBeginTakeEffect() {
		return timeBeginTakeEffect;
	}
	private void setTimeBeginTakeEffect(Date timeBeginTakeEffect) {
		this.timeBeginTakeEffect = timeBeginTakeEffect;
	}
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	
	
	/**
	 * 判断当前AccessToken是否有效.
	 * @return
	 */
	public boolean isValid() {
		return this.getExpires_in() > ((new Date().getTime() - this.getTimeBeginTakeEffect().getTime())/1000);
	}


	@Override
	public String toString() {
		return "ApiTicket [errcode=" + errcode + ", errmsg=" + errmsg + ", ticket=" + ticket + ", expires_in="
				+ expires_in + ", timeBeginTakeEffect=" + timeBeginTakeEffect + "]";
	}
	
	public String toJSONString() {
		return JSON.toJSONString(this);
	}
	
	
}
