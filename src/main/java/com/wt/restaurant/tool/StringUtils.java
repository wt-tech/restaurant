package com.wt.restaurant.tool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	
	/**
	 * 方便使用SQL的 in 语句.</br>
	 * <B>1,5,7</B> will change to <B>'1','5','7'</B>,
	 */
	public static String changeToReasonableIds(String ids) {
		System.err.println(ids);
		StringBuilder result = new StringBuilder("");
		String[] arrs = ids.split(",");
		
		for(String id : arrs) {
			result.append("'"+ id + "',");
		}
		
		result.deleteCharAt(result.length()-1);
		System.err.println(result.toString());
		
		return result.toString();
	}
	
	public static String shoppingCartJSONStringProcess(Object obj) {
		Pattern regex = Pattern.compile("\"([\\d,]*)\"");
		Matcher match = regex.matcher(obj.toString());
		match.find();
		return match.group(1);
	}

	/**
	 * @return 返回一个订单编号,填充sales_order表中的 order_number字段
	 */
	public static String createOrderNumber() {
		//前缀
		StringBuilder orderNumber = new StringBuilder(Constants.ORDER_NUMBER_PREFIX);
		//时间精确到毫秒
		orderNumber.append(new SimpleDateFormat("yyyymmddHHmmssSSS").format(new Date()));
		//随机字符串
		orderNumber.append(getRandomString(5));
		return orderNumber.toString().toUpperCase();
	}
	
	/**
	 * 获取随机字符串 15 +13 总共28个字符
	 * @return
	 */
	public static String getRandomString(int length) {
		char[] chars = { 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 
				'h', 'i', 'j', 'k', 'l', 'm', 'n', 
				'o', 'p', 'q', 'r', 's','t', 
				'u', 'v', 'w', 'x', 'y', 'z' };
		StringBuffer randomString = new StringBuffer("");
		for (int i = 0; i < length; i++) {
			randomString.append(chars[(int) Math.round(Math.random() * 25)]);
		}
		return randomString.toString();
	}
}
