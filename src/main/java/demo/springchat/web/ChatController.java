package demo.springchat.web;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.springchat.req.ChatMessage;
import demo.springchat.res.User;
import demo.springchat.entity.Account;
import demo.springchat.repo.AccountRepo;
import java.net.URL;

/**
 * Created by nathaniel.a.camomot
 */
@Controller
public class ChatController {

  @Autowired
  private SimpUserRegistry simpUserRegistry;

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @Autowired
  private AccountRepo accountRepo;


  @MessageMapping("/message.{url}")
  public ChatMessage privateMessage(ChatMessage chatMessage, @DestinationVariable("url") URL url,
          Principal principal) {

    chatMessage.setSender(principal.getName());

    return chatMessage;
  }
}
