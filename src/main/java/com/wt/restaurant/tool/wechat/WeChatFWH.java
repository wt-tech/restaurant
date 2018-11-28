package com.wt.restaurant.tool.wechat;

/**
 * 服务号相关的信息
 * @author Daryl
 */
public class WeChatFWH {
	
	
	public static final String CARDID = "pJ_0W51M0hJAYlmTgcpLIsx3lJro";
	public static final String APPID = "wx8217c885b8beaf36";
	public static final String APPSECRET = "ffd0bccdde813a013881aa66a7d7085d";
	/*公众号官方文档讲ACESS_TOKEN的过期时间时7200s,
	 * 这里设置3600s,即超过1小时,就重新调用接口获取*/
	public static final Integer ACCESS_TOKEN_EXPIRES_SECONDS = 3600;
	
	/*公众号用于获取AccessToken的URL地址*/
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token"
			+ "?grant_type=client_credential"
			+ "&appid=" + APPID
			+ "&secret=" + APPSECRET;
	
	/*公众号用于获取API_TICKET的URL地址*/
	public static final String API_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket"
			+ "?type=wx_card"
			+ "&access_token=";
	
	/*公众号用于解析Code的URL地址*/
	public static final String DECRYPT_CODE_URL = "https://api.weixin.qq.com/card/code/decrypt"
			+ "?access_token=";
	
	/*公众号用于获取用户卡券列表的URL地址*/
	public static final String GET_CARD_LIST_URL = "https://api.weixin.qq.com/card/user/getcardlist"
			+ "?access_token=";
	
}
