package com.wt.restaurant.entity;

import java.sql.Date;

public class PrizeRecord {
	
	private Integer id;
	private Date prizeTime;
	private Customer customer;
	private Prize prize;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getPrizeTime() {
		return prizeTime;
	}
	public void setPrizeTime(Date prizeTime) {
		this.prizeTime = prizeTime;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Prize getPrize() {
		return prize;
	}
	public void setPrize(Prize prize) {
		this.prize = prize;
	}
}
