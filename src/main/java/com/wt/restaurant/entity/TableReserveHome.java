package com.wt.restaurant.entity;

import java.util.Date;
import java.util.List;

public class TableReserveHome {
	private Integer id;
	private Customer customer;// 用户id
	private String reservationsName;// 预订人姓名
	private String reservationsSex;// 性别
	private String reservationsTel;// 预订人联系方式
	private int reservationsNum;// 预订人数
	private Date reservationsStartTime;// 预订开始时间
	private Date reserveTime;// 预订时间
	private String remarks;// 备注
	private List<Menu> menu;// 菜品

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

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

}
