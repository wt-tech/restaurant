package com.wt.restaurant.servimpl.wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.wechat.IVIPCardMapper;
import com.wt.restaurant.entity.Customer;
import com.wt.restaurant.entity.wechat.VIPCard;
import com.wt.restaurant.service.wechat.IVIPCardService;


@Service
public class VIPCardServImpl implements IVIPCardService {

	@Autowired
	private IVIPCardMapper cardMapper;
	
	
	@Override
	public boolean addCard(VIPCard card) {
		return cardMapper.addVIPCard(card);
	}

	@Override
	public Boolean removeVIPCard(Customer customer) {
		return cardMapper.removeVIPCard(customer);
	}
	
}
