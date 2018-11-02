package com.wt.restaurant.servimpl.reserve;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.reserve.IReserveMapper;
import com.wt.restaurant.entity.Box;
import com.wt.restaurant.entity.Customer;
import com.wt.restaurant.entity.DishOrder;
import com.wt.restaurant.entity.DishOrderLine;
import com.wt.restaurant.entity.Menu;
import com.wt.restaurant.entity.Reserve;
import com.wt.restaurant.service.reserve.IReserveService;

@Service
public class ReserveServiceImpl implements IReserveService {
	@Autowired
	private IReserveMapper reservemapper;

	@Override
	public List<Reserve> listReserve(Integer currentPageNo, Integer pageSize, Reserve reserve) {
		// TODO Auto-generated method stub
		return reservemapper.listReserve(currentPageNo, pageSize, reserve);
	}

	@Override
	public boolean updateReserve(Reserve reserve) throws Exception {
		// TODO Auto-generated method stub
		return reservemapper.updateReserve(reserve) > 0;
	}

	@Override
	public boolean saveReserve(Reserve reserve) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		boolean flags = true;
		flag = reservemapper.saveReserve(reserve) > 0;
		if (flag) {
			if (reserve.getBox().size() > 0) {
				List<Box> boxList = reserve.getBox();
				if (null != boxList && reserve.getId() > 0) {
					flag = false;
					for (int i = 0; i < boxList.size(); i++) {
						flag = reservemapper.saveBox(reserve.getId(), boxList.get(i).getId()) > 0;
						if (!flag)
							break;
					}
				}
			}
			if (null != reserve.getMenu() && reserve.getMenu().size() > 0) {
				List<Menu> menuList = reserve.getMenu();
				if (null != menuList && reserve.getId() > 0) {
					for (int i = 0; i < menuList.size(); i++) {
						flags = reservemapper.saveMenu(reserve.getId(), menuList.get(i).getId(),
								menuList.get(i).getSpecifications(), menuList.get(i).getChoosePrice(),
								menuList.get(i).getMenuCount()) > 0;
						if (!flags)
							break;
					}
				}
			}
		}
		if (flag && flags) {
			return saveDishOrder(reserve);
		}
		return false;
	}

	@Override
	public boolean saveDishOrder(Reserve reserve) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int sumPrice = 0;
		DishOrder dishorder = new DishOrder();
		dishorder.setReserve(reserve);
		Customer customer = new Customer();
		customer.setId(reserve.getCustomer().getId());
		dishorder.setCustomer(customer);
		dishorder.setReserveType("包厢预订");
		if (null != reserve.getMenu() && reserve.getMenu().size() > 0) {
			dishorder.setTotalCount(reserve.getMenu().size());
			for (int i = 0; i < reserve.getMenu().size(); i++) {
				sumPrice += reserve.getMenu().get(i).getChoosePrice() * reserve.getMenu().get(i).getMenuCount();
			}
			dishorder.setTotalAmount(sumPrice);
		}
		if (reservemapper.saveDishOrder(dishorder) > 0) {
			DishOrderLine dishorderline = new DishOrderLine();
			for (int i = 0; i < reserve.getMenu().size(); i++) {
				dishorderline.setMenu(reserve.getMenu().get(i));
				dishorderline.setDishorder(dishorder);
				dishorderline.setDishCount(reserve.getMenu().get(i).getMenuCount());
				dishorderline.setUnitPrice(reserve.getMenu().get(i).getChoosePrice());
				dishorderline.setSpecifications(reserve.getMenu().get(i).getSpecifications());
				flag = reservemapper.saveDishOrderLine(dishorderline) > 0;
				if (!flag)
					break;
			}
		}
		return flag;
	}

	@Override
	public boolean removeReserve(int id) throws Exception {
		// TODO Auto-generated method stub
		return reservemapper.removeReserve(id) > 0;
	}

	@Override
	public Reserve getReserve(int id) {
		// TODO Auto-generated method stub
		return reservemapper.getReserve(id);
	}

	@Override
	public List<Reserve> listReserveByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return reservemapper.listReserveByCustomerId(customerId);
	}

	@Override
	public Integer countReserve(Reserve reserve) {
		// TODO Auto-generated method stub
		return reservemapper.countReserve(reserve);
	}

}
