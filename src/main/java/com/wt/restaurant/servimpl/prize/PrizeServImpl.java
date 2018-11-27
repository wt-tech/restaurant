package com.wt.restaurant.servimpl.prize;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.prize.IPrizeMapper;
import com.wt.restaurant.entity.Prize;
import com.wt.restaurant.service.prize.IPrizeService;

@Service
public class PrizeServImpl implements IPrizeService {

	@Autowired
	private IPrizeMapper mapper;
	
	@Override
	public List<Prize> listPrize() {
		return mapper.listPrize();
	}

	@Override
	public Prize getPrize(Integer id) {
		return mapper.getPrize(id);
	}

	@Override
	public boolean savePrize(Prize prize) {
		return mapper.savePrize(prize);
	}

	@Override
	public boolean deletePrize(Prize prize) {
		return mapper.deletePrize(prize);
	}

	@Override
	public boolean updatePrize(Prize prize) {
		return mapper.updatePrize(prize);
	}

}
