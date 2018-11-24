package com.wt.restaurant.servimpl.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.sequence.ISequenceMapper;
import com.wt.restaurant.service.sequence.ISequenceService;

@Service
public class SequenceServImpl implements ISequenceService{

	@Autowired
	private ISequenceMapper mapper;
	
	/**
	 * 避免序列号重复,添加了synchronized修饰
	 */
	@Override
	public synchronized String updateAndGetNextSequence() {
		return mapper.updateAndGetNextSequence();
	}

}
