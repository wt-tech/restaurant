package com.wt.restaurant.tool.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.wt.restaurant.entity.AccessToken;
import com.wt.restaurant.tool.http.HttpRequest;

public class AccessTokenManager {
	
	private static final Map<String,AccessToken> ACCESS_TOKEN_MAP = new HashMap<String,AccessToken>();
	private static final String FWH_TOKEN = "FWH_TOKEN";
	private static final String XCX_TOKEN = "XCX_TOKEN";
	
	public static AccessToken fetchFWHAccessToken() {
		if(ACCESS_TOKEN_MAP.get(FWH_TOKEN) == null) {
			synchronized(AccessTokenManager.class) {
				if(ACCESS_TOKEN_MAP.get(FWH_TOKEN) == null) {
					AccessTokenManager.fetchNewFWHAccessTokenAndPutIntoMap();
				}
			}
		}else if(!(ACCESS_TOKEN_MAP.get(FWH_TOKEN).isValid())) {//accesstoken已经失效
			AccessTokenManager.fetchNewFWHAccessTokenAndPutIntoMap();
		}
		return ACCESS_TOKEN_MAP.get(FWH_TOKEN);
	}
	
	public static AccessToken fetchXCXAccessToken() {
		if(ACCESS_TOKEN_MAP.get(XCX_TOKEN) == null) {
			synchronized(AccessTokenManager.class) {
				if(ACCESS_TOKEN_MAP.get(XCX_TOKEN) == null) {
					AccessTokenManager.fetchNewXCXAccessTokenAndPutIntoMap();
				}
			}
		}else if(!(ACCESS_TOKEN_MAP.get(XCX_TOKEN).isValid())) {//accesstoken已经失效
			AccessTokenManager.fetchNewXCXAccessTokenAndPutIntoMap();
		}
		return ACCESS_TOKEN_MAP.get(XCX_TOKEN);
	}
	
	private static void fetchNewXCXAccessTokenAndPutIntoMap() {
		AccessToken accessToken = null;
		accessToken = AccessTokenManager.fetchAccessTokenThroughWeChatAPI(false);
		ACCESS_TOKEN_MAP.put(XCX_TOKEN, accessToken);
	}

	private static AccessToken fetchAccessTokenThroughWeChatAPI(boolean isFWH) {
		HttpRequest httpReq = null;
		if(isFWH)
			httpReq = new HttpRequest(WeChatFWH.ACCESS_TOKEN_URL);
		else
			httpReq = new HttpRequest(WeChatXCX.ACCESS_TOKEN_URL);
		httpReq.connectAndFetchResult();
		String resString  = (String) httpReq.getResponseContent();
		AccessToken accessToken = (AccessToken) JSON.parse(resString);
		accessToken.setExpires_in(WeChatFWH.ACCESS_TOKEN_EXPIRES_SECONDS);
		accessToken.setTimeBeginTakeEffect(new Date());
		return accessToken;
	}

	private static void fetchNewFWHAccessTokenAndPutIntoMap() {
		AccessToken accessToken = null;
		accessToken = AccessTokenManager.fetchAccessTokenThroughWeChatAPI(true);
		ACCESS_TOKEN_MAP.put(FWH_TOKEN, accessToken);
	}

	
}
