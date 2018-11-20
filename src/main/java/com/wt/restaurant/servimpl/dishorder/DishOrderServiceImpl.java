package com.wt.restaurant.servimpl.dishorder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.dishorder.IDishOrderMapper;
import com.wt.restaurant.entity.DishOrder;
import com.wt.restaurant.entity.DishOrderLine;
import com.wt.restaurant.service.dishorder.IDishOrderService;

@Service
public class DishOrderServiceImpl implements IDishOrderService {
	@Autowired
	private IDishOrderMapper dishordermapper;

	@Override
	public List<DishOrder> listDishOrder(Integer currentPageNo, Integer pageSize,DishOrder dishorder) {
		// TODO Auto-generated method stub
		return dishordermapper.listDishOrder(currentPageNo, pageSize,dishorder);
	}

	@Override
	public List<DishOrder> listDishOrderByCustomerId(Integer customerId) {
		// TODO Auto-generated method stub
		return dishordermapper.listDishOrderByCustomerId(customerId);
	}

	@Override
	public Integer countDishOrder(DishOrder dishorder) {
		// TODO Auto-generated method stub
		return dishordermapper.countDishOrder(dishorder);
	}

	@Override
	public List<DishOrderLine> listDishOrderMenu(Integer dishorderId) {
		// TODO Auto-generated method stub
		return dishordermapper.listDishOrderMenu(dishorderId);
	}

	@Override
	public boolean updateDishOrderRemark(Integer id, String remark) throws Exception {
		// TODO Auto-generated method stub
		return dishordermapper.updateDishOrderRemark(id, remark)>0;
	}

	@Override
	public DishOrder getDishOrderRemark(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return dishordermapper.getDishOrderRemark(id);
	}

	@Override
	public boolean updateDishOrderStatus(Integer id, double totalPayAmount) throws Exception {
		// TODO Auto-generated method stub
		return dishordermapper.updateDishOrderStatus(id, totalPayAmount)>0;
	}

}
