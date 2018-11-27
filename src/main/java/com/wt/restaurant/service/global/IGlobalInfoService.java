package com.wt.restaurant.service.global;

import com.wt.restaurant.entity.GlobalInfo;

public interface IGlobalInfoService {
	
	GlobalInfo getGlobalInfo(String key);

	boolean updateGlobalInfo(GlobalInfo globalInfo);
	
}
