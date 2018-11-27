package com.wt.restaurant.entity;

import java.sql.Date;

public class GlobalInfo {
	
	private Integer id;
	private String keey;
	private String valuue;
	private Date lastModifyTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKeey() {
		return keey;
	}
	public void setKeey(String keey) {
		this.keey = keey;
	}
	public String getValuue() {
		return valuue;
	}
	public void setValuue(String valuue) {
		this.valuue = valuue;
	}
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
}
