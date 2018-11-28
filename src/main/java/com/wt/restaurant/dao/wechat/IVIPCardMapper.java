package com.wt.restaurant.dao.wechat;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.wt.restaurant.entity.Customer;
import com.wt.restaurant.entity.wechat.VIPCard;

public interface IVIPCardMapper {
	
	@Insert("INSERT INTO vip_card ( customer_id, card_code, cardId ) " + 
			"VALUES  (#{card.customer.id},#{card.code},#{card.cardId})")
	boolean addVIPCard(@Param("card") VIPCard vipCard);
	
	@Delete("DELETE FROM vip_card WHERE customer_id = #{customer.id}")
	Boolean removeVIPCard(@Param("customer") Customer customer);
}
