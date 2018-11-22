package com.wt.restaurant.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.wt.restaurant.tool.BusinessUtils;
import com.wt.restaurant.tool.Constants;

public class NewReservationNotifyHandler extends TextWebSocketHandler {

	private static Map<String,WebSocketSession> users = null;
	
	private static Queue<TextMessage> messages = null; 
	
	private Logger logger = LogManager.getLogger(NewReservationNotifyHandler.class);
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message){
		try {
			super.handleTextMessage(session, message);
		} catch (Exception e) {
			BusinessUtils.throwNewBusinessException("处理心跳包发生异常 : "+e.getMessage());
		}
	}

	public NewReservationNotifyHandler() {
		
	}
	
	@Override
	public  void afterConnectionEstablished(WebSocketSession session){
		try {
			super.afterConnectionEstablished(session);
		} catch (Exception e) {
			BusinessUtils.throwNewBusinessException("连接建立出现异常: "+e.getMessage());
		}
		if(null == NewReservationNotifyHandler.users) {
			synchronized(this){
				if(null == NewReservationNotifyHandler.users) {
					users = new HashMap<String,WebSocketSession>();
				}
			}
		}
		users.put(this.getSessionKey(session), session);
		logger.info(this.getSessionKey(session) + " in queue, there are " + users.size() + " users in total");
		this.sendAllMessagesToAllUsers();//后台管理人员接收离线信息
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		users.remove(this.getSessionKey(session));
		try {
			super.afterConnectionClosed(session, status);
		} catch (Exception e) {
			BusinessUtils.throwNewBusinessException("连接关闭出现异常 : "+e.getMessage());
		}
		logger.info(this.getSessionKey(session) + " out queue, there are " + users.size() + " users left");
	}
	
	private String getSessionKey(WebSocketSession session) {
		if(session.getAttributes().get(Constants.JSESSIONID) == null) {
			String sessionId = this.getJSESSIONIDFromCookie(session);
			session.getAttributes().put(Constants.JSESSIONID, sessionId);
		}
		return (String)session.getAttributes().get(Constants.JSESSIONID);
	}
	
	private String getJSESSIONIDFromCookie(WebSocketSession session) {
		HttpHeaders httpHeaders= session.getHandshakeHeaders(); 
		String sessionId = null;
		for(Entry<String, List<String>> header : httpHeaders.entrySet()) {
			if(Constants.COOKIE.equalsIgnoreCase(header.getKey())) {
				String values = header.getValue().toString();
				Pattern regex = Pattern.compile("(?:JSESSIONID=)([\\d\\D]*?)(?=[\\s,;\\]])");
				Matcher matchers = regex.matcher(values);
				if(matchers.find()) {
					sessionId = matchers.group(1);
				}
			}
		}
		return sessionId;
	}
	
	/**
	 * 向队列里添加新消息,并将消息发送给后台管理人员
	 * @param message
	 */
	protected void addNewMessageAndNotifyManager(TextMessage message) {
		this.messageInQueue(message);
		this.sendAllMessagesToAllUsers();
	}
	
	/**
	 * 发送队列中所有信息给所有用户.
	 */
	private synchronized void sendAllMessagesToAllUsers() {
		if(null == messages) {//没有新消息.此时先不发送.
			return;
		}
		TextMessage notify = null;
		while(true) {
			if(null == users || users.size() == 0) {//后台管理未开启,没有管理员
				break;
			}
			notify = NewReservationNotifyHandler.messages.poll();
			if(notify == null) {//队列中已经没有更多消息了
				break;
			}else {//发送通知
				this.sendMessageToAllUsers(notify);
				try {
					Thread.sleep(1000);//每发送一条消息,间隔1秒钟
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return;
	}
	
	/**
	 * 将notify发送给所有后台管理人员
	 * @param notify
	 */
	private boolean sendMessageToAllUsers(TextMessage notify) {
		if(users == null || users.size() == 0)
			return false;
		for(Entry<String,WebSocketSession> entry : NewReservationNotifyHandler.users.entrySet()) {
			if(entry.getValue().isOpen()) {
				try {
					entry.getValue().sendMessage(notify);
				} catch (IOException e) {
					//如果发送出现异常,让notify重新回到队列? 算了吧 不需要了.					
					BusinessUtils.throwNewBusinessException("向客户端发送信息失败,发送的消息为:" + notify.getPayload());
				}
			}
		}
		logger.info("已向 " + users.size()	+ " 位管理员发送'" + notify.getPayload() + "'信息");
		return true;
	}
	
	
	/**
	 * 消息入队列
	 * @param message
	 */
	private void messageInQueue(TextMessage message) {
		if(null == NewReservationNotifyHandler.messages) {
			synchronized(this){
				if(null == NewReservationNotifyHandler.messages) {
					NewReservationNotifyHandler.messages = new ConcurrentLinkedQueue<TextMessage>();
				}
			}
		}
		NewReservationNotifyHandler.messages.add(message);
	}
}
