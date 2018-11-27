package com.wt.restaurant.servimpl.prize;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.prize.IPrizeRecordMapper;
import com.wt.restaurant.entity.PrizeRecord;
import com.wt.restaurant.service.prize.IPrizeRecordService;

@Service
public class PrizeRecordServImpl implements IPrizeRecordService{

	@Autowired
	private IPrizeRecordMapper recordMapper;

	@Override
	public PrizeRecord getPrizeRecord(Integer id) {
		return recordMapper.getPrizeRecord(id);
	}

	@Override
	public boolean savePrizeRecord(PrizeRecord prizeRecord) {
		return recordMapper.savePrizeRecord(prizeRecord);
	}

	@Override
	public List<PrizeRecord> listPrizeRecordByCustomerId(Integer customerId) {
		return recordMapper.listPrizeRecordByCustomerId(customerId);
	}
	
}
