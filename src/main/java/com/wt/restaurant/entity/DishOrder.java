package com.wt.restaurant.entity;

import java.util.Date;
import java.util.List;

import com.wt.restaurant.tool.StringUtils;

public class DishOrder {
	private Integer id;
	private Reserve reserve;// 预定
	private Customer customer;// 客户
  //private int discountId;// 优惠券
	private String orderNumber;// 订单编号
	private String reserveType;// 预订类型
	private Date createTime;// 订单创建时间
	private int totalCount;// 菜品总数量
	private double totalAmount;// 总金额
	private double discountAmount;// 优惠金额
	private double totalPayAmount;// 实付总金额
	private int orderStatus;// 订单状态
	private List<DishOrderLine> dishorderline;// 订单项

	// 无参构造函数,初始化一个订单编号
	public DishOrder() {
		this.setOrderNumber(StringUtils.createOrderNumber());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Reserve getReserve() {
		return reserve;
	}

	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getReserveType() {
		return reserveType;
	}

	public void setReserveType(String reserveType) {
		this.reserveType = reserveType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getTotalPayAmount() {
		return totalPayAmount;
	}

	public void setTotalPayAmount(double totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<DishOrderLine> getDishorderline() {
		return dishorderline;
	}

	public void setDishorderline(List<DishOrderLine> dishorderline) {
		this.dishorderline = dishorderline;
	}

}
