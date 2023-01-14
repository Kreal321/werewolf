package me.kreal.werewolf.api.controller;

import me.kreal.werewolf.api.dto.MessageDto;
import me.kreal.werewolf.api.response.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public MessageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Handles messages from /app/chat. (Note the Spring adds the /app prefix for us).
    @MessageMapping("/new/{id}")
    public void getMessages(MessageDto msg, @DestinationVariable String id, @Payload MessageDto messageDto){

        System.out.println("received message: " + msg.getMessage() + ". from: " + id);

        messageDto.setMessage("Game " + id + " received message: " + msg.getMessage());

        messagingTemplate.convertAndSend("/api/message/subscription/" + id, messageDto);
    }


}
