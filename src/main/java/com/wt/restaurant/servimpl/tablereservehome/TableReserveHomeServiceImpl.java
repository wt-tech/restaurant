package com.wt.restaurant.servimpl.tablereservehome;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.sequence.ISequenceMapper;
import com.wt.restaurant.dao.tablereservehome.ITableReserveHomeMapper;
import com.wt.restaurant.entity.Customer;
import com.wt.restaurant.entity.DishOrder;
import com.wt.restaurant.entity.DishOrderLine;
import com.wt.restaurant.entity.Menu;
import com.wt.restaurant.entity.Reserve;
import com.wt.restaurant.entity.TableReserveHome;
import com.wt.restaurant.service.tablereservehome.ITableReserveHomeService;

@Service
public class TableReserveHomeServiceImpl implements ITableReserveHomeService {
	@Autowired
	private ITableReserveHomeMapper tablereservehomemapper;
	@Autowired
	private ISequenceMapper sequenceMapper;

	@Override
	public List<TableReserveHome> listTableReserveHome(Integer currentPageNo, Integer pageSize,
			TableReserveHome tablereservehome) {
		// TODO Auto-generated method stub
		return tablereservehomemapper.listTableReserveHome(currentPageNo, pageSize, tablereservehome);
	}

	@Override
	public boolean updateTableReserveHome(TableReserveHome tablereservehome) throws Exception {
		// TODO Auto-generated method stub
		return tablereservehomemapper.updateTableReserveHome(tablereservehome) > 0;
	}

	@Override
	public boolean saveTableReserveHome(TableReserveHome tablereservehome) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = tablereservehomemapper.saveTableReserveHome(tablereservehome) > 0;
		if (flag && tablereservehome.getId() > 0) {
			flag = false;
			if (null != tablereservehome.getMenu() && tablereservehome.getMenu().size() > 0) {
				for (Menu menu : tablereservehome.getMenu()) {
					menu.setReserveId(tablereservehome.getId());
				}
				flag = tablereservehomemapper.saveMenu(tablereservehome.getMenu()) > 0;
			}
		}
		if (flag)
			return saveDishOrder(tablereservehome);
		return false;
	}

	@Override
	public boolean saveDishOrder(TableReserveHome tablereservehome) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int sumPrice = 0;
		int sumCount = 0;
		DishOrder dishorder = new DishOrder();
		Reserve reserve = new Reserve();
		reserve.setId(tablereservehome.getId());
		dishorder.setReserve(reserve);
		Customer customer = new Customer();
		customer.setId(tablereservehome.getCustomer().getId());
		dishorder.setCustomer(customer);
		dishorder.setReserveType("店外点餐");
		if (null != tablereservehome.getMenu() && tablereservehome.getMenu().size() > 0) {
			for (int i = 0; i < tablereservehome.getMenu().size(); i++) {
				sumCount += tablereservehome.getMenu().get(i).getMenuCount();// 总数量
				if ("可预订".equals(tablereservehome.getMenu().get(i).getChoosePrice())
						|| "时价".equals(tablereservehome.getMenu().get(i).getChoosePrice())) {
					continue;
				}
				sumPrice += Double.parseDouble(tablereservehome.getMenu().get(i).getChoosePrice())
						* tablereservehome.getMenu().get(i).getMenuCount();// 总价格
			}
			dishorder.setTotalAmount(sumPrice);
			dishorder.setTotalCount(sumCount);
		}
		synchronized (ISequenceMapper.class) {
			dishorder.setOrderNumber(sequenceMapper.updateAndGetNextSequence());
			if (tablereservehomemapper.saveDishOrder(dishorder) > 0) {
				DishOrderLine dishorderline = new DishOrderLine();
				List<DishOrderLine> dishorderlinelist = new ArrayList<DishOrderLine>();
				for (int i = 0; i < tablereservehome.getMenu().size(); i++) {
					dishorderline = new DishOrderLine();
					dishorderline.setMenu(tablereservehome.getMenu().get(i));
					dishorderline.setDishorder(dishorder);
					dishorderline.setDishCount(tablereservehome.getMenu().get(i).getMenuCount());
					dishorderline.setUnitPrice(tablereservehome.getMenu().get(i).getChoosePrice());
					dishorderline.setSpecifications(tablereservehome.getMenu().get(i).getSpecifications());
					dishorderlinelist.add(dishorderline);
				}
				flag = tablereservehomemapper.saveDishOrderLine(dishorderlinelist) > 0;
			}
		}
		return flag;
	}

	@Override
	public boolean removeTableReserveHome(int id) throws Exception {
		// TODO Auto-generated method stub
		return tablereservehomemapper.removeTableReserveHome(id) > 0;
	}

	@Override
	public TableReserveHome getTableReserveHome(int id) {
		// TODO Auto-generated method stub
		return tablereservehomemapper.getTableReserveHome(id);
	}

	@Override
	public List<TableReserveHome> listTableReserveHomeByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return tablereservehomemapper.listTableReserveHomeByCustomerId(customerId);
	}

	@Override
	public Integer countTableReserveHome(TableReserveHome tablereservehome) {
		// TODO Auto-generated method stub
		return tablereservehomemapper.countTableReserveHome(tablereservehome);
	}

	@Override
	public boolean updateTableNum(Integer id, String tableNum, String type) throws Exception {
		// TODO Auto-generated method stub
		return tablereservehomemapper.updateTableNum(id, tableNum, type) > 0;
	}

}
