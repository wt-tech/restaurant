package com.wt.restaurant.entity;

import java.util.List;

public class Box {
	private Integer id;
	private int roomNumber;// 包厢号
	private int roomSize;// 包厢大小(可容纳人数)
	private String roomName;// 包厢名称
	private String roomIntroduction;// 包厢介绍
	private int reserveStatus;// 包厢预订情况(0预订午餐,1预订晚餐,2午餐和晚餐均预订,3午餐和晚餐均没预订)
	private List<BoxImage> boximage;// 包厢图片

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomIntroduction() {
		return roomIntroduction;
	}

	public void setRoomIntroduction(String roomIntroduction) {
		this.roomIntroduction = roomIntroduction;
	}

	public int getReserveStatus() {
		return reserveStatus;
	}

	public void setReserveStatus(int reserveStatus) {
		this.reserveStatus = reserveStatus;
	}

	public List<BoxImage> getBoximage() {
		return boximage;
	}

	public void setBoximage(List<BoxImage> boximage) {
		this.boximage = boximage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Box other = (Box) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
