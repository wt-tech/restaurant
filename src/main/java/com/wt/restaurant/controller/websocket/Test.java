package com.wt.restaurant.controller.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import com.wt.restaurant.websocket.NewReservationNotifyHandler;

@RestController
public class Test {
	
	@Autowired
	private NewReservationNotifyHandler handler;
	
	@RequestMapping("test")
	public void sendMessageByWebSocket() {
		handler.sendMessageToAllUsers(new TextMessage("hello websocket"));
	}
}
