package com.example.demo.controllers;

import com.example.demo.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class SocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private static final String ROOM = "/secured/room";

    private static final String DESTINATION = "/secured/user/queue/specific-user";

    @MessageMapping("/private")
    public void sendSpecific(@Payload Message msg, Principal user) {
        simpMessagingTemplate.convertAndSendToUser(msg.getTo(), "/specific", msg);
    }
}
