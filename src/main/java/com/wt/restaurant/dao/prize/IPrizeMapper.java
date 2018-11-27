package com.wt.restaurant.dao.prize;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.restaurant.entity.Prize;

public interface IPrizeMapper {
	
	List<Prize> listPrize();
	
	Prize getPrize(@Param("id") Integer id);
	
	boolean savePrize(@Param("prize") Prize prize);
	
	boolean deletePrize(@Param("prize") Prize prize);
	
	boolean updatePrize(@Param("prize") Prize prize);
	
	
}
