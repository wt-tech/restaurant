package com.wt.restaurant.service.prize;

import java.util.List;

import com.wt.restaurant.entity.Prize;

public interface IPrizeService {
	List<Prize> listPrize();
	
	Prize getPrize(Integer id);
	
	boolean savePrize(Prize prize);
	
	boolean deletePrize(Prize prize);
	
	boolean updatePrize(Prize prize);
	
}
