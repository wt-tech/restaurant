package com.wt.restaurant.servimpl.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.global.IGlobalInfoMapper;
import com.wt.restaurant.entity.GlobalInfo;
import com.wt.restaurant.service.global.IGlobalInfoService;

@Service
public class GlobalInfoServImpl implements IGlobalInfoService{

	@Autowired
	private IGlobalInfoMapper infoMapper;
	@Override
	public GlobalInfo getGlobalInfo(String key) {
		return infoMapper.getGlobalInfo(key);
	}

	@Override
	public boolean updateGlobalInfo(GlobalInfo globalInfo) {
		return infoMapper.updateGlobalInfo(globalInfo);
	}

}
