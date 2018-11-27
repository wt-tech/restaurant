package com.wt.restaurant.dao.prize;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.restaurant.entity.PrizeRecord;

public interface IPrizeRecordMapper {
	
	PrizeRecord getPrizeRecord(@Param("id") Integer id);
	
	boolean savePrizeRecord(@Param("prizeRecord") PrizeRecord prizeRecord);
	
	List<PrizeRecord> listPrizeRecordByCustomerId(@Param("customerId") Integer customerId);
}
