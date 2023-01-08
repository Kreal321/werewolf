package me.kreal.werewolf.api.controller;

import me.kreal.werewolf.api.dto.MessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {

    // Handles messages from /app/chat. (Note the Spring adds the /app prefix for us).
    @MessageMapping("/new")
    // Sends the return value of this method to /topic/messages
    @SendTo("/api/broadcast")
    public MessageDto getMessages(MessageDto msg){
        System.out.println("received message: " + msg.getMessage());

        return new MessageDto();
    }

}
