package com.wt.restaurant.tool.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wt.restaurant.tool.http.HttpRequest;
import com.wt.restaurant.tool.http.HttpRequestMethod;
import com.wt.restaurant.tool.http.inface.RequestContentType;
import com.wt.restaurant.tool.http.requestcontenttype.ApplicationJsonContentType;

/**
 * 处理会员卡相关的操作.
 * @author Daryl
 */
public class CardManager {
	
	
	/**
	 * 解析加密code
	 * @param encryptCode
	 * @return
	 */
	public static JSONObject decryptCode(String encryptCode) {
		if(encryptCode == null || encryptCode.length() == 0)
			return null;
		return CardManager.decryptCodeThroughWeChatAPI(CardManager.assembleParam(encryptCode));
	}
	
	/**
	 * 获取用户的卡券列表.(这里只有会员卡)详情见下链接<br/>
	 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025272 获取用户已领取卡券接口
	 * @param params
	 * 形如:<br/>
	 * {<br/>
	 * 	&nbsp;&nbsp;&nbsp;&nbsp;"openid": "12312313",<br/>
  	 *	&nbsp;&nbsp;&nbsp;&nbsp;"card_id": "xxxxxxxxxx"<br/>
	 * }
	 * @return
	 */
	public static JSONObject getCardList(JSONObject params) {
		return getCardListThroughWeChatAPI(params);
	}
	
	
	
	
	
	
	
	
	
	private static JSONObject assembleParam(String encryptCode) {
		String params = "{\"encrypt_code\" "
				+ ":\"" + encryptCode
				+"\"}";
		return (JSONObject) JSON.parse(params);
	}

	private static JSONObject decryptCodeThroughWeChatAPI(Object encryptCode) {
		String accessToken = AccessTokenManager.fetchFWHAccessToken().getAccess_token();
		HttpRequest httpRequest = new HttpRequest(WeChatFWH.DECRYPT_CODE_URL + accessToken);
		RequestContentType contentType = new ApplicationJsonContentType(encryptCode);
		httpRequest.setContentType(contentType);
		httpRequest.setMethod(HttpRequestMethod.POST);
		httpRequest.connectAndFetchResult();
		String result = (String)httpRequest.getResponseContent();
		return (JSONObject) JSON.parse(result);
	}
	
	
	private static JSONObject getCardListThroughWeChatAPI(JSONObject params) {
		
		String url = WeChatFWH.GET_CARD_LIST_URL + AccessTokenManager.fetchFWHAccessToken().getAccess_token();
		HttpRequest httpRequest = new HttpRequest(url);
		httpRequest.setContentType(new ApplicationJsonContentType(params));
		httpRequest.setMethod(HttpRequestMethod.POST);
		httpRequest.connectAndFetchResult();
		String result = (String) httpRequest.getResponseContent();
		return (JSONObject) JSON.parse(result) ;
	}
	
	
}
