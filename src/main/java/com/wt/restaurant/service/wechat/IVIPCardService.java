package com.wt.restaurant.service.wechat;

import com.wt.restaurant.entity.Customer;
import com.wt.restaurant.entity.wechat.VIPCard;

public interface IVIPCardService {
	boolean addCard(VIPCard card);
	Boolean removeVIPCard(Customer customer);
}
