package com.wt.restaurant.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.wt.restaurant.tool.BusinessUtils;
import com.wt.restaurant.tool.Constants;

public class NewReservationNotifyHandler extends TextWebSocketHandler {

	private static Map<String,WebSocketSession> users = null;
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
	}

	public NewReservationNotifyHandler() {
		
	}
	
	@Override
	public  void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		synchronized(this){
			if(null == NewReservationNotifyHandler.users) {
				users = new HashMap<String,WebSocketSession>();
			}
		}
		users.put(this.getSessionKey(session), session);
		System.err.println(this.getSessionKey(session) + "established");
	}

	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.err.println(this.getSessionKey(session) + "closed");
		users.remove(this.getSessionKey(session));
		super.afterConnectionClosed(session, status);
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
	

	public void sendMessageToAllUsers(TextMessage message) {
		if(null == users)
			return;
		for(Entry<String,WebSocketSession> entry : users.entrySet()) {
			if(entry.getValue().isOpen()) {
				try {
					entry.getValue().sendMessage(message);
				} catch (IOException e) {
					BusinessUtils.throwNewBusinessException("向客户端发送信息失败,发送的消息为:" + message.getPayload());
				}
			}
		}
	}
}
