package com.wt.restaurant.entity;

public class DishOrderLine {
	private Integer id;
	private DishOrder dishorder;// 订单
	private Menu menu;// 菜品
	private int dishCount;// 菜品数量
	private double unitPrice;// 单价
	private String specifications;// 规格

	public DishOrderLine() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DishOrder getDishorder() {
		return dishorder;
	}

	public void setDishorder(DishOrder dishorder) {
		this.dishorder = dishorder;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getDishCount() {
		return dishCount;
	}

	public void setDishCount(int dishCount) {
		this.dishCount = dishCount;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

}
