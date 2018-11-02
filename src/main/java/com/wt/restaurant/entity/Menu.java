package com.wt.restaurant.entity;

import java.util.List;

public class Menu {
	private Integer id;
	private Classification classification;// 菜品分类
	private String name;// 菜品名字
	private double largePrice;// 大份价格
	private double mediumPrice;// 中份价格
	private double smallPrice;// 小份价格
	private int salesVolume;// 销量
	private double discount;// 折扣
	private double discountPrice;// 折扣价
	private String introduction;// 菜品介绍
	private String specifications;//选中的菜品规格
	private double choosePrice;//选中的菜品价格
	private int menuCount;//菜品的数量
	private int sort;// 排序
	private List<MenuImage> menuimage;// 菜品图片

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Classification getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLargePrice() {
		return largePrice;
	}

	public void setLargePrice(double largePrice) {
		this.largePrice = largePrice;
	}

	public double getMediumPrice() {
		return mediumPrice;
	}

	public void setMediumPrice(double mediumPrice) {
		this.mediumPrice = mediumPrice;
	}

	public double getSmallPrice() {
		return smallPrice;
	}

	public void setSmallPrice(double smallPrice) {
		this.smallPrice = smallPrice;
	}

	public int getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public double getChoosePrice() {
		return choosePrice;
	}

	public void setChoosePrice(double choosePrice) {
		this.choosePrice = choosePrice;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getMenuCount() {
		return menuCount;
	}

	public void setMenuCount(int menuCount) {
		this.menuCount = menuCount;
	}

	public List<MenuImage> getMenuimage() {
		return menuimage;
	}

	public void setMenuimage(List<MenuImage> menuimage) {
		this.menuimage = menuimage;
	}

}
