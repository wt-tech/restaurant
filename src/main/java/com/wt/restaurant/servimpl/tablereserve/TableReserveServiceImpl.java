package com.wt.restaurant.servimpl.tablereserve;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.tablereserve.ITableReserveMapper;
import com.wt.restaurant.entity.Customer;
import com.wt.restaurant.entity.DishOrder;
import com.wt.restaurant.entity.DishOrderLine;
import com.wt.restaurant.entity.Menu;
import com.wt.restaurant.entity.Reserve;
import com.wt.restaurant.entity.TableReserve;
import com.wt.restaurant.service.tablereserve.ITableReserveService;

@Service
public class TableReserveServiceImpl implements ITableReserveService {
	@Autowired
	private ITableReserveMapper tablereservemapper;

	@Override
	public List<TableReserve> listTableReserve(Integer currentPageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return tablereservemapper.listTableReserve(currentPageNo, pageSize);
	}

	@Override
	public boolean updateTableReserve(TableReserve tablereserve) throws Exception {
		// TODO Auto-generated method stub
		return tablereservemapper.updateTableReserve(tablereserve)>0;
	}

	@Override
	public boolean saveTableReserve(TableReserve tablereserve) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		boolean flags = true;
		flag = tablereservemapper.saveTableReserve(tablereserve) > 0;
		if (flag) {
			if (null != tablereserve.getMenu() && tablereserve.getMenu().size() > 0) {
				List<Menu> menuList = tablereserve.getMenu();
				if (null != menuList && tablereserve.getId() > 0) {
					for (int i = 0; i < menuList.size(); i++) {
						flags = tablereservemapper.saveMenu(tablereserve.getId(), menuList.get(i).getId(),
								menuList.get(i).getSpecifications(), menuList.get(i).getChoosePrice(),
								menuList.get(i).getMenuCount()) > 0;
						if (!flags)
							break;
					}
				}
			}
		}
		if (flag && flags) {
			return saveDishOrder(tablereserve);
		}
		return false;
	}

	@Override
	public boolean saveDishOrder(TableReserve tablereserve) throws Exception {
		// TODO Auto-generated method stub
			boolean flag = false;
			int sumPrice = 0;
			DishOrder dishorder = new DishOrder();
			Reserve reserve = new Reserve();
			reserve.setId(tablereserve.getId());
			dishorder.setReserve(reserve);
			Customer customer = new Customer();
			customer.setId(tablereserve.getCustomer().getId());
			dishorder.setCustomer(customer);
			dishorder.setReserveType("扫码点餐");
			if (null != tablereserve.getMenu() && tablereserve.getMenu().size() > 0) {
				dishorder.setTotalCount(tablereserve.getMenu().size());
				for (int i = 0; i < tablereserve.getMenu().size(); i++) {
					sumPrice += tablereserve.getMenu().get(i).getChoosePrice() * tablereserve.getMenu().get(i).getMenuCount();
				}
				dishorder.setTotalAmount(sumPrice);
			}
			if (tablereservemapper.saveDishOrder(dishorder) > 0) {
				DishOrderLine dishorderline = new DishOrderLine();
				for (int i = 0; i < tablereserve.getMenu().size(); i++) {
					dishorderline.setMenu(tablereserve.getMenu().get(i));
					dishorderline.setDishorder(dishorder);
					dishorderline.setDishCount(tablereserve.getMenu().get(i).getMenuCount());
					dishorderline.setUnitPrice(tablereserve.getMenu().get(i).getChoosePrice());
					dishorderline.setSpecifications(tablereserve.getMenu().get(i).getSpecifications());
					flag = tablereservemapper.saveDishOrderLine(dishorderline) > 0;
					if (!flag)
						break;
				}
			}
			return flag;
		}

	@Override
	public boolean removeTableReserve(int id) throws Exception {
		// TODO Auto-generated method stub
		return tablereservemapper.removeTableReserve(id)>0;
	}

	@Override
	public TableReserve getTableReserve(int id) {
		// TODO Auto-generated method stub
		return tablereservemapper.getTableReserve(id);
	}

	@Override
	public List<TableReserve> listTableReserveByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return tablereservemapper.listTableReserveByCustomerId(customerId);
	}

	@Override
	public Integer countTableReserve() {
		// TODO Auto-generated method stub
		return tablereservemapper.countTableReserve();
	}

}
