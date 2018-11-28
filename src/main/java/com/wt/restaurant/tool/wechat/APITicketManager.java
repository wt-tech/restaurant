package com.wt.restaurant.tool.wechat;

import com.alibaba.fastjson.JSON;
import com.wt.restaurant.entity.wechat.ApiTicket;
import com.wt.restaurant.tool.http.HttpRequest;

/**
 * 小程序端调用wx.addCard需要传递cardExt参数.<br/>
 * cardExt参数需要进行signature验证.<br/>
 * signature需要获取api_ticket.<br/>
 * 
 * @author Daryl
 */
public class APITicketManager {

	private static ApiTicket ticket = null;

	public static ApiTicket fetchApiTicket() {
		if (ticket == null) {
			synchronized (APITicketManager.class) {
				if (ticket == null) {
					APITicketManager.fetchApiTicketThroughWeChatApi();
				}
			}
		} else if (!ticket.isValid()) {
			synchronized (APITicketManager.class) {
				APITicketManager.fetchApiTicketThroughWeChatApi();
			}
		}
		return ticket;
	}

	private static void fetchApiTicketThroughWeChatApi() {
		String accessToken = AccessTokenManager.fetchFWHAccessToken().getAccess_token();
		String apiTicketURL = WeChatFWH.API_TICKET_URL + accessToken;
		HttpRequest httpReq = new HttpRequest(apiTicketURL);
		httpReq.connectAndFetchResult();
		String resString = (String) httpReq.getResponseContent();
		ApiTicket tempTicket = JSON.parseObject(resString, ApiTicket.class);
		tempTicket.setExpires_in(WeChatFWH.ACCESS_TOKEN_EXPIRES_SECONDS);
		ticket = tempTicket;
	}
}
