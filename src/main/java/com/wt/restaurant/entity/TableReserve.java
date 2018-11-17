package com.wt.restaurant.entity;

import java.util.Date;
import java.util.List;

/*
 * 扫码点餐
 */
public class TableReserve {
	private Integer id;
	private Customer customer;// 用户id
	private Table table;// 桌子id
	private Box box;// 包厢id
	private String type;// 类型(包厢or桌子)
	private Date reserveTime;// 预订时间
	private List<Menu> menu;// 菜品
	
	
	/************ 只用于后台管理的时间段筛选功能，方便mybatis使用 ************/
	private String reserveStartTime;// 预订开始时间
	private String reserveEndTime;// 预订结束时间

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

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Box getBox() {
		return box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
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
