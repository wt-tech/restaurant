package com.wt.restaurant.servimpl.banquetreserve;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.banquetreserve.IBanquetReserveMapper;
import com.wt.restaurant.entity.BanquetReserve;
import com.wt.restaurant.service.banquetreserve.IBanquetReserveService;

@Service
public class BanquetReserveServiceImpl implements IBanquetReserveService {
	@Autowired
	private IBanquetReserveMapper banquetreservemapper;

	@Override
	public List<BanquetReserve> listBanquetReserve(Integer currentPageNo, Integer pageSize,BanquetReserve banquetreserve) {
		// TODO Auto-generated method stub
		return banquetreservemapper.listBanquetReserve(currentPageNo, pageSize,banquetreserve);
	}

	@Override
	public boolean updateBanquetReserve(BanquetReserve banquetreserve) throws Exception {
		// TODO Auto-generated method stub
		return banquetreservemapper.updateBanquetReserve(banquetreserve) > 0;
	}

	@Override
	public boolean saveBanquetReserve(BanquetReserve banquetreserve) throws Exception {
		// TODO Auto-generated method stub
		return banquetreservemapper.saveBanquetReserve(banquetreserve) > 0;
	}

	@Override
	public boolean removeBanquetReserve(int id) throws Exception {
		// TODO Auto-generated method stub
		return banquetreservemapper.removeBanquetReserve(id) > 0;
	}

	@Override
	public BanquetReserve getBanquetReserve(int id) {
		// TODO Auto-generated method stub
		return banquetreservemapper.getBanquetReserve(id);
	}

	@Override
	public List<BanquetReserve> listBanquetReserveByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return banquetreservemapper.listBanquetReserveByCustomerId(customerId);
	}

	@Override
	public Integer countBanquetReserve(BanquetReserve banquetreserve) {
		// TODO Auto-generated method stub
		return banquetreservemapper.countBanquetReserve(banquetreserve);
	}

}
