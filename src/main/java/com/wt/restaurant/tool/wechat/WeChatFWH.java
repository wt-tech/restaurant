package com.wt.restaurant.tool.wechat;

/**
 * 服务号相关的信息
 * @author Daryl
 */
public class WeChatFWH {
	
	public static final String APPID = "wx8217c885b8beaf36";
	public static final String APPSECRET = "ffd0bccdde813a013881aa66a7d7085d";
	/*公众号官方文档讲ACESS_TOKEN的过期时间时7200s,
	 * 这里设置3600s,即超过1小时,就重新调用接口获取*/
	public static final Integer ACCESS_TOKEN_EXPIRES_SECONDS = 3600;
	
	/*公众号用于获取AccessToken的URL地址*/
	public static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token"
			+ "?grant_type=client_credential"
			+ "&appid=" + APPID
			+ "&secret=" + APPSECRET;
	
}
