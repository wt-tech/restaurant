package com.wt.restaurant.controller.wechat;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wt.restaurant.entity.Customer;
import com.wt.restaurant.entity.wechat.VIPCard;
import com.wt.restaurant.service.customer.ICustomerServ;
import com.wt.restaurant.service.wechat.IVIPCardService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.MapUtils;
import com.wt.restaurant.tool.wechat.APITicketManager;
import com.wt.restaurant.tool.wechat.CardManager;
import com.wt.restaurant.tool.wechat.WeChatFWH;

@RestController
@RequestMapping("card")
public class VIPCardCtrl {

	@Autowired
	private IVIPCardService vipCardServImpl;
	
	@Autowired
	private ICustomerServ customerServImpl;
	
	
	@RequestMapping("api-ticket")
	public Map<String,Object> getApiTicket(){
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("ticket", APITicketManager.fetchApiTicket());
		return map;
	}
	
	@RequestMapping(value = "card",method = RequestMethod.POST)
	public Map<String,Object> saveCard(@RequestBody VIPCard card){
		JSONObject obj = CardManager.decryptCode(card.getCode());
		card.setCode(obj.getString("code"));
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.FAIL);
		if(vipCardServImpl.addCard(card)) {
			map.put(Constants.STATUS, Constants.SUCCESS);
			map.put("code", card.getCode());
		}
		return map;
	}
	
	/**
	 * 返回会员卡code
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "code",method = RequestMethod.GET)
	public JSONObject getCardCode(Customer customer) {
		customer = customerServImpl.getCustomerById(customer.getId());
		JSONArray list = this.fetchCardList(customer);
		JSONObject cardInfo = (JSONObject) JSON.parse("{}");
		if(list == null || list.size() == 0) {
			cardInfo.put(Constants.STATUS, Constants.FAIL);
			return cardInfo;
		}
		//默认认为list的长度仅为1 即只有一个会员卡
		cardInfo = list.getJSONObject(0);
		VIPCard card = this.assembleVIPCard(cardInfo,customer);
		cardInfo.remove("card_id");
		if(vipCardServImpl.addCard(card)) {
			cardInfo.put(Constants.STATUS, Constants.SUCCESS);
		}
		return cardInfo;
	}
	
	private VIPCard assembleVIPCard(JSONObject cardInfo,Customer customer) {
		VIPCard card = new VIPCard();
		card.setCustomer(customer);
		card.setCardId(cardInfo.getString("card_id"));
		card.setCode(cardInfo.getString("code"));
		return card;
	}

	private JSONArray fetchCardList(Customer customer) {
		JSONObject params = (JSONObject) JSON.parse("{}");
		params.put("openid", customer.getOpenid());
		params.put("card_id", WeChatFWH.CARDID);
		JSONObject result = CardManager.getCardList(params);
		JSONArray cardList = null;
		if(result.getInteger("errcode")==0 && "ok".equals(result.getString("errmsg"))) {
			cardList = result.getJSONArray("card_list");
		}
		return cardList;
	}
}
