package com.wt.restaurant.servimpl.dishorder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.dishorder.IDishOrderMapper;
import com.wt.restaurant.entity.DishOrder;
import com.wt.restaurant.service.dishorder.IDishOrderService;

@Service
public class DishOrderServiceImpl implements IDishOrderService {
	@Autowired
	private IDishOrderMapper dishordermapper;

	@Override
	public List<DishOrder> listDishOrder(Integer currentPageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return dishordermapper.listDishOrder(currentPageNo, pageSize);
	}

	@Override
	public List<DishOrder> listDishOrderByCustomerId(Integer customerId) {
		// TODO Auto-generated method stub
		return dishordermapper.listDishOrderByCustomerId(customerId);
	}

	@Override
	public Integer countDishOrder() {
		// TODO Auto-generated method stub
		return dishordermapper.countDishOrder();
	}

}
