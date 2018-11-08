package com.wt.restaurant.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;

import com.wt.restaurant.websocket.entity.Message;

/**
 * 在控制器中被调用.用来连接controller和websockethandler
 * 这个类在容器内必须声明为prototy类型
 * @author Daryl
 */
public class ControllerHandlerBridge extends Thread{

	@Autowired
	private NewReservationNotifyHandler notifyHandler;
	private TextMessage message;
	
	@Override
	public void run() {
		notifyHandler.addNewMessageAndNotifyManager(message);
	}
	
	public void notifyManager(Message message) {
		this.message = new TextMessage(message.toJSONString());
		this.start();
	}
	
}
