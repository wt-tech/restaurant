package com.wt.restaurant.service.customer;

import com.wt.restaurant.entity.Customer;

public interface ICustomerServ {

	Customer getCustomerByOpenId(String openId);
	
	boolean saveCustomer(Customer customer);
	
	Customer getCustomerById(Integer id);
	
	boolean updateCustomer(Customer customer);
}
