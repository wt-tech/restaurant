package com.wt.restaurant.entity;

import java.util.Date;
import java.util.List;

/*
 * 包厢预订
 *
 */
public class Reserve {
	private Integer id;
	private Customer customer;// 用户id
	private String reservationsName;// 预订人姓名
	private String reservationsSex;// 性别
	private String reservationsTel;// 预订人联系方式
	private Integer reservationsNum;// 预订人数
	private Date reservationsStartTime;// 预订开始时间
	private String reservationType;// 预订类型
	private Date reserveTime;// 预订时间
	private String reservationsMode;// 预约模式
	private String remarks;// 备注
	private List<Box> box;// 包厢
	private List<Menu> menu;// 菜品

	/************ 只用于后台管理的时间段筛选功能，方便mybatis使用 ************/
	private String EatStartTime;// 就餐开始时间
	private String EatEndTime;// 就餐结束时间
	private String reserveStartTime;// 预订开始时间
	private String reserveEndTime;// 预订结束时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReservationsNum() {
		return reservationsNum;
	}

	public void setReservationsNum(Integer reservationsNum) {
		this.reservationsNum = reservationsNum;
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


	public Date getReservationsStartTime() {
		return reservationsStartTime;
	}

	public void setReservationsStartTime(Date reservationsStartTime) {
		this.reservationsStartTime = reservationsStartTime;
	}

	public String getReservationType() {
		return reservationType;
	}

	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}

	public Date getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}

	public String getReservationsMode() {
		return reservationsMode;
	}

	public void setReservationsMode(String reservationsMode) {
		this.reservationsMode = reservationsMode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<Box> getBox() {
		return box;
	}

	public void setBox(List<Box> box) {
		this.box = box;
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	public String getEatStartTime() {
		return EatStartTime;
	}

	public void setEatStartTime(String eatStartTime) {
		EatStartTime = eatStartTime;
	}

	public String getEatEndTime() {
		return EatEndTime;
	}

	public void setEatEndTime(String eatEndTime) {
		EatEndTime = eatEndTime;
	}

	public String getReserveStartTime() {
		return reserveStartTime;
	}

	public void setReserveStartTime(String reserveStartTime) {
		this.reserveStartTime = reserveStartTime;
	}

	public String getReserveEndTime() {
		return reserveEndTime;
	}

	public void setReserveEndTime(String reserveEndTime) {
		this.reserveEndTime = reserveEndTime;
	}

}
