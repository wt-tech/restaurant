package com.wt.restaurant.entity;

/**
 * 生成二维码需要的参数,和后台数据库表无关,说明:<br/>
 * https://developers.weixin.qq.com/miniprogram/dev/api/open-api/qr-code/getWXACodeUnlimit.html
 * @author Daryl
 */
public class MiniProgramCodeParam {
	
	private String access_token;
	private String scene;
	private String page;
	private String width;
	private Boolean auto_color;
	private Boolean is_hyaline;
	private LineColor line_color;
	/**
	 * 该字段用来给最终生成的二维码命名.
	 * 由于MiniProgramCodeParam对象本身是需要发给微信提供的API接口的.
	 * 并且不可以有多余的字段,因此imgName字段在发送时会被移除.
	 * 他的作用仅仅是给二维码命名,没有其他意义.
	 * @see com.wt.restaurant.servimpl.minicode.MiniProgramCodeServImpl#prepareParams
	 */
	private String imgName;
	
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getScene() {
		return scene;
	}
	public void setScene(String scene) {
		this.scene = scene;
	}
	

	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public Boolean getAuto_color() {
		return auto_color;
	}
	public void setAuto_color(Boolean auto_color) {
		this.auto_color = auto_color;
	}
	public Boolean getIs_hyaline() {
		return is_hyaline;
	}
	public void setIs_hyaline(Boolean is_hyaline) {
		this.is_hyaline = is_hyaline;
	}
	public LineColor getLine_color() {
		return line_color;
	}
	public void setLine_color(LineColor line_color) {
		this.line_color = line_color;
	}
	
}
