package demo.springchat.web;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import demo.springchat.dto.ChatMessage;
import demo.springchat.dto.User;

/**
 * Created by nathaniel.a.camomot
 */
@Controller
public class ChatController {

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @SubscribeMapping("/users")
    public Set<User> getUsers(Principal principal) {
        //get all users except principal
        return simpUserRegistry.getUsers().stream()
                .filter(chatUser -> !chatUser.getName().equals(principal.getName()))
                .map(simpUser -> {
                    User user = new User();
                    user.setUsername(simpUser.getName());
                    return user;
                })
                .collect(Collectors.toSet());
    }

    @MessageMapping("/message.global")
    public ChatMessage globalMessage(ChatMessage chatMessage, Principal principal) {
        chatMessage.setSender(principal.getName());
        return chatMessage;
    }

    @MessageMapping("/message.private.{username}")
    public void privateMessage(ChatMessage chatMessage, @DestinationVariable("username") String recipient, Principal principal) {
        chatMessage.setSender(principal.getName());

        messagingTemplate.convertAndSend("/user/" + recipient + "/queue/message.private", chatMessage);
    }
}
