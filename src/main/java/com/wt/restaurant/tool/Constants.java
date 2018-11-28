package com.wt.restaurant.tool;

import java.util.HashSet;
import java.util.Set;

public class Constants {
	
	//session相关
	public final static String USER_SESSION = "userSession";
	public final static String JSESSIONID = "JSESSIONID";
	public final static String COOKIE = "COOKIE";

	
	//状态相关
	public final static String SYS_MESSAGE = "message";
	public final static String SUCCESS = "success";
	public final static String STATUS = "status";
	public final static String FAIL = "fail";
	public final static int HTTP_STATUS_302 = 302;
	public final static int HTTP_STATUS_200 = 200;
	
	
	
	//异常同一处理相关
	public final static String EXCEPTION_CLASS = "exception";
	public final static String ERRORS = "errors";
	public final static String TIPS = "tips";
	public final static String DATA_ACCESS_DESCRIPTION = "数据处理异常";
	public final static String EXCEPTION_DESCRIPTION = "未知错误";
	public final static String BUSINESS_DESCRIPTION = "业务逻辑错误";
	public final static String ARGUMENT_ILLEGAL = "参数不合法";
	public final static String DEFAULT_ERROR_INFO = "服务器错误,请联系后台管理员";

	
	

	//小程序APPID
	private final static String APPID = "wxcb9fa37aa44f29a4";
	//小程序SECRET
	private final static String SECRET = "6b50c215d453e72c45ad8fe2fbc7752e";
	
	//小程序官方提供的code转openID接口
	public final static String CODE2OPENIDURL = "https://api.weixin.qq.com/sns/jscode2session"
			+ "?appid="+APPID
			+ "&secret="+SECRET
			+ "&grant_type=authorization_code"
			+ "&js_code=";//参数js_code每次从前台传入
	
	public final static int pageSize = 3;
	public final static int pageSizes = 6;
	public final static int pageSizess = 12;
	public static final String DOT = ".";
	
	//小程序官方提供的 获取小程序Access_token
	public final static  String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token"
			+ "?grant_type=client_credential"
			+ "&appid="+APPID
			+ "&secret="+SECRET;
	
	//小程序官方提供的 获取小程序码URL
	public static String MINI_PROGRAM_URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit"
			+ "?access_token="; 
	
	
	//服务端存放相应表中图片的文件夹名称
	public static final String MENU_IMG = "menu-img";
	public static final String BANNER_IMG = "banner-img";

	public static final String BOX_IMG = "box-img";
	
	public static final String COMBO_IMG = "combo-img";

	
	//存放小程序码的路径
	public static final String MINIQRCODE = "mini-program-code";
	public static final String MINIQRCODESUFFIX = ".jpeg";

	
	//DOCBASEPATH指Tomcat的server.xml配置文件中host下的context的docBase属性
	public static final String IMGBASEPATH = "statics";
	//ContextPath指当前Web应用的名称
	public static final String ContextPath = "restaurant";
	
	//等部署到服务器上后,再修改
	  public static final String imgServerDomain = "https://www.qghls.com/statics/";
//    public static final String imgServerDomain = "http://192.168.0.109:8080/statics/";
//    public static final String imgServerDomain = "http://192.168.0.177:8888/statics/";


	public static final String ORDER_NUMBER_PREFIX = "XTXZSMF";
	
	//小程序端拥有商家入口权限的用户id集合.
	public static final Set<Integer> managerList = new HashSet<Integer>();
	static {
		managerList.add(new Integer(188));
	}
}
