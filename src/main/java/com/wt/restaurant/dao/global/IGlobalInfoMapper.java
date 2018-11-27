package com.wt.restaurant.dao.global;

import org.apache.ibatis.annotations.Param;

import com.wt.restaurant.entity.GlobalInfo;

public interface IGlobalInfoMapper {

	GlobalInfo getGlobalInfo(@Param("key") String key);

	boolean updateGlobalInfo(@Param("globalInfo") GlobalInfo globalInfo);
	
}
