package demo.echo.spring;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
	
/**
 * Created by nathaniel.a.camomot
 */
public class SpringEchoHandler extends TextWebSocketHandler { //BinaryWebSocketHandler
	
    //autowiring works!
    @Autowired
    private EchoService echoService;
	
    private static Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
    	sessions.add(session);
    }
    
    //echo
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	for (WebSocketSession s : sessions) {
 	        if (s.isOpen()) {
 	        	 s.sendMessage(new TextMessage(echoService.getMessage(session.getId(), message.getPayload())));
 	        }
    	 }
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	sessions.remove(session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        //error
    }

}
