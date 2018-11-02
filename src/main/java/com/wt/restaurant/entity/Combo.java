package com.wt.restaurant.entity;

import java.util.List;

public class Combo {
	private Integer id;
	private String comboName;// 套餐名字
	private double comboPrice;// 套餐价格
	private String comboIntroduction;// 套餐介绍
	private String comboClassification;// 套餐类别
	private int comboSort;// 套餐排序
	private List<ComboImage> comboimage;// 套餐图片

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

	public double getComboPrice() {
		return comboPrice;
	}

	public void setComboPrice(double comboPrice) {
		this.comboPrice = comboPrice;
	}

	public String getComboIntroduction() {
		return comboIntroduction;
	}

	public void setComboIntroduction(String comboIntroduction) {
		this.comboIntroduction = comboIntroduction;
	}

	public String getComboClassification() {
		return comboClassification;
	}

	public void setComboClassification(String comboClassification) {
		this.comboClassification = comboClassification;
	}

	public int getComboSort() {
		return comboSort;
	}

	public void setComboSort(int comboSort) {
		this.comboSort = comboSort;
	}

	public List<ComboImage> getComboimage() {
		return comboimage;
	}

	public void setComboimage(List<ComboImage> comboimage) {
		this.comboimage = comboimage;
	}

}
