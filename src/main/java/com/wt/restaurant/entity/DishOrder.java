package com.wt.restaurant.entity;

import java.util.Date;
import java.util.List;

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
	private Boolean orderStatus;// 订单状态
	private String remark;//备注
	private List<DishOrderLine> dishorderline;// 订单项
	
	
	/************ 只用于后台管理的时间段筛选功能，方便mybatis使用 ************/
	private String reserveStartTime;// 预订开始时间
	private String reserveEndTime;// 预订结束时间
	private String tableOrboxNum;// 包厢号or桌号

	// 无参构造函数,初始化一个订单编号
	/**
	 * 订单编号被放在对应的serivceImpl中初始化了.
	 * @author Daryl 2018-11-22
	 */
	public DishOrder() {
		//this.setOrderNumber(SpringContextUtils.getBeanByClass(ISequenceService.class).updateAndGetNextSequence());
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


	public Boolean getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Boolean orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<DishOrderLine> getDishorderline() {
		return dishorderline;
	}

	public void setDishorderline(List<DishOrderLine> dishorderline) {
		this.dishorderline = dishorderline;
	}

	public String getReserveStartTime() {
		return reserveStartTime;
	}

	public void setReserveStartTime(String reserveStartTime) {
		this.reserveStartTime = reserveStartTime;
	}

	public String getReserveEndTime() {
		return reserveEndTime;
	}

	public void setReserveEndTime(String reserveEndTime) {
		this.reserveEndTime = reserveEndTime;
	}

	public String getTableOrboxNum() {
		return tableOrboxNum;
	}

	public void setTableOrboxNum(String tableOrboxNum) {
		this.tableOrboxNum = tableOrboxNum;
	}

}
