package com.wt.restaurant.websocket.entity;

public class Message {

	private MessageType messageType;
	
	public Message(MessageType messageType) {
		this.messageType = messageType;
	}

	@Override
	public String toString() {
		return "Message [messageType=" + messageType + "]";
	}
	 
	public String toJSONString() {
		String res = "{\"type\":\""+this.messageType.getName()+"\"}";
		System.out.println(res);
		return res;
	}
	
}
