package com.wt.restaurant.entity;

public class Customer {

	private int id;
	private String openid;
	private String nickname;
	private String tel;
	private String firstVistTime;
	private String registerTime;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFirstVistTime() {
		return firstVistTime;
	}

	public void setFirstVistTime(String firstVistTime) {
		this.firstVistTime = firstVistTime;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public Customer() {
	}
	
}
