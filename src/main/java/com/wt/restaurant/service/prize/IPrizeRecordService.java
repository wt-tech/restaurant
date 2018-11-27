package com.wt.restaurant.service.prize;

import java.util.List;

import com.wt.restaurant.entity.PrizeRecord;

public interface IPrizeRecordService {
	
	PrizeRecord getPrizeRecord(Integer id);
	
	boolean savePrizeRecord(PrizeRecord prizeRecord);
	
	List<PrizeRecord> listPrizeRecordByCustomerId(Integer customerId);

}
