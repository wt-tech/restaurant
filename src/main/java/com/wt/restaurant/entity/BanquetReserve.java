package com.wt.restaurant.entity;

import java.util.Date;

public class BanquetReserve {
	private Integer id;
	private Customer customer;// 用户id
	private Combo combo;// 套餐id
	private String reservationsName;// 预订人姓名
	private String reservationsSex;// 性别
	private String reservationsTel;// 预订人联系方式
	private int reservationsNum;// 预订桌数
	private Date reservationsStartTime;// 预订开始时间
	private Date reserveTime;// 预订时间
	private String remarks;// 备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

	public String getReservationsName() {
		return reservationsName;
	}

	public void setReservationsName(String reservationsName) {
		this.reservationsName = reservationsName;
	}

	public String getReservationsSex() {
		return reservationsSex;
	}

	public void setReservationsSex(String reservationsSex) {
		this.reservationsSex = reservationsSex;
	}

	public String getReservationsTel() {
		return reservationsTel;
	}

	public void setReservationsTel(String reservationsTel) {
		this.reservationsTel = reservationsTel;
	}

	public int getReservationsNum() {
		return reservationsNum;
	}

	public void setReservationsNum(int reservationsNum) {
		this.reservationsNum = reservationsNum;
	}

	public Date getReservationsStartTime() {
		return reservationsStartTime;
	}

	public void setReservationsStartTime(Date reservationsStartTime) {
		this.reservationsStartTime = reservationsStartTime;
	}

	public Date getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
