package com.wt.restaurant.websocket.entity;

public enum MessageType {

	BOX_RESERVE("boxReserve"),BANQUET_RESERVE("banquetReserve"),
	TABLE_RESERVE("tableReserve"),CODE_SCAN_ORDER("codeScanOrder");
	
	private final String name;
	
	private MessageType(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	
}
