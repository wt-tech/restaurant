package com.wt.restaurant.entity;

public class Banner {

	private int id;
	private String imgName;// 图片名称
	private String url;// 图片路径
	private String uploadTime;// 上传时间
	private boolean onUse;// 是否使用

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public boolean isOnUse() {
		return onUse;
	}

	public void setOnUse(boolean onUse) {
		this.onUse = onUse;
	}

	public Banner() {

	}

}
