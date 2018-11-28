package com.wt.restaurant.entity.wechat;

import com.wt.restaurant.entity.Customer;

/**
 * 指一个用户的会员卡信息
 * @author Daryl
 *
 */
public class VIPCard {
	
	private Customer customer;
	private String cardId;
	private String code;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "VIPCard [customer=" + customer + ", cardId=" + cardId + ", code=" + code + "]";
	}
	
	
	
}
