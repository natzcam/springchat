package demo.springchat.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import demo.springchat.dto.User;

/**
 * Created by nathaniel.a.camomot on 1/11/2016.
 */
@Component
public class SessionListener {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        Principal principal = getPrincipal(event);
        User user = new User();
        user.setUsername(principal.getName());
        messagingTemplate.convertAndSend("/topic/login", user);
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        Principal principal = getPrincipal(event);
        User user = new User();
        user.setUsername(principal.getName());
        messagingTemplate.convertAndSend("/topic/logout", user);
    }

    private Principal getPrincipal(AbstractSubProtocolEvent event){
        return SimpMessageHeaderAccessor.wrap(event.getMessage()).getUser();
    }
}
