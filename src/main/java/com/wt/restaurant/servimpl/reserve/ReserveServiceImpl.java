package com.wt.restaurant.servimpl.reserve;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.reserve.IReserveMapper;
import com.wt.restaurant.dao.sequence.ISequenceMapper;
import com.wt.restaurant.entity.Box;
import com.wt.restaurant.entity.Customer;
import com.wt.restaurant.entity.DishOrder;
import com.wt.restaurant.entity.DishOrderLine;
import com.wt.restaurant.entity.Menu;
import com.wt.restaurant.entity.Reserve;
import com.wt.restaurant.service.reserve.IReserveService;

@Service
// @Transactional(rollbackFor = { Exception.class })
public class ReserveServiceImpl implements IReserveService {
	@Autowired
	private IReserveMapper reservemapper;
	@Autowired
	private ISequenceMapper sequenceMapper;

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
		if (flag && reserve.getId() > 0) {
			flag = false;
			if (null != reserve.getBox() && reserve.getBox().size() > 0) {
				for (Box box : reserve.getBox()) {
					box.setReserveId(reserve.getId());
				}
				flag = reservemapper.saveBox(reserve.getBox()) > 0;
			}
			if (null != reserve.getMenu() && reserve.getMenu().size() > 0) {
				for (Menu menu : reserve.getMenu()) {
					menu.setReserveId(reserve.getId());
				}
				flags = reservemapper.saveMenu(reserve.getMenu()) > 0;
			} else {// 只订座，不生成订单
				return flag && flags;
			}
		}
		if (flag && flags)
			return saveDishOrder(reserve);
		return false;
	}

	@Override
	public boolean saveDishOrder(Reserve reserve) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int sumPrice = 0;
		int sumCount = 0;
		DishOrder dishorder = new DishOrder();
		dishorder.setReserve(reserve);
		Customer customer = new Customer();
		customer.setId(reserve.getCustomer().getId());
		dishorder.setCustomer(customer);
		dishorder.setReserveType("包厢预订");
		dishorder.setRemark(reserve.getRemarks());
		if (null != reserve.getMenu() && reserve.getMenu().size() > 0) {
			for (int i = 0; i < reserve.getMenu().size(); i++) {
				sumCount += reserve.getMenu().get(i).getMenuCount();// 总数量
				if ("可预订".equals(reserve.getMenu().get(i).getChoosePrice())
						|| "时价".equals(reserve.getMenu().get(i).getChoosePrice())) {
					continue;
				}
				sumPrice += Double.parseDouble(reserve.getMenu().get(i).getChoosePrice())
						* reserve.getMenu().get(i).getMenuCount();// 总价格
			}
			dishorder.setTotalAmount(sumPrice);
			dishorder.setTotalCount(sumCount);
		}

		synchronized (ISequenceMapper.class) {
			dishorder.setOrderNumber(sequenceMapper.updateAndGetNextSequence());
			if (reservemapper.saveDishOrder(dishorder) > 0) {
				DishOrderLine dishorderline = new DishOrderLine();
				List<DishOrderLine> dishorderlinelist = new ArrayList<DishOrderLine>();
				for (int i = 0; i < reserve.getMenu().size(); i++) {
					dishorderline = new DishOrderLine();
					dishorderline.setMenu(reserve.getMenu().get(i));
					dishorderline.setDishorder(dishorder);
					dishorderline.setDishCount(reserve.getMenu().get(i).getMenuCount());
					dishorderline.setUnitPrice(reserve.getMenu().get(i).getChoosePrice());
					dishorderline.setSpecifications(reserve.getMenu().get(i).getSpecifications());
					dishorderlinelist.add(dishorderline);
				}
				flag = reservemapper.saveDishOrderLine(dishorderlinelist) > 0;
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
