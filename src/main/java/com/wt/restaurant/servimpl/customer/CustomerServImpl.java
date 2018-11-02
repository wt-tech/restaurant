package com.wt.restaurant.servimpl.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.customer.ICustomerMapper;
import com.wt.restaurant.entity.Customer;
import com.wt.restaurant.service.customer.ICustomerServ;
import com.wt.restaurant.tool.BusinessUtils;

@Service()
public class CustomerServImpl implements ICustomerServ{

	@Autowired()
	private ICustomerMapper customerMapper;
	
	
	@Override
	public Customer getCustomerByOpenId(String openId) {
		return customerMapper.getIdByOpenId(openId);
	}

	@Override
	public boolean saveCustomer(Customer customer){
		return customerMapper.saveCustomer(customer);
	}


	@Override
	public Customer getCustomerById(Integer id) {
		return customerMapper.getCustomerById(id);
	}

	@Override
	public boolean updateCustomer(Customer customer){
		if(!customerMapper.updateCustomer(customer)) {
			BusinessUtils.throwNewBusinessException("更新用户信息失败");
		}
		return true;
	}


}
