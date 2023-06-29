package com.example.jinyengandothers.cotroller;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class TickerWebSocketHandler extends TextWebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String request = "[{\"ticket\":\"UNIQUE_TICKET\"}, {\"type\":\"ticker\", \"codes\":[\"KRW-BTC\"]}]";
        session.sendMessage(new TextMessage(request));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String response = message.getPayload();
        System.out.println("가격: " + response);
    }
}