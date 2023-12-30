package com.gettingusedtoit.springpractice.controller;

import com.gettingusedtoit.springpractice.controller.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import com.gettingusedtoit.springpractice.controller.model.Message;

import java.util.Date;

//@EnableScheduling
//@Controller
public class ChatController {

    //if you want to send a private message dynamically, then necessary to use this as we don't
    //want to hardcode
//    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    //whenever a user needs to send to websocket sends to /app/message
    // /app is because we set setApplicationDestination
    @MessageMapping("/message")
    //if they want to listen to this message, they need to listen to /chatroom/public
    @SendTo("/chatroom/public")
    public Message receivePublicMessage(@Payload Message message) {
        System.out.println(message);
        System.out.println("receivePublicMessage");
        return message;
    }

    @MessageMapping("/private-message")
    public Message receivePrivateMessage(@Payload Message message) {
        System.out.println("receivePrivateMessage");
        //if the user wants to listen to this topic, he needs to listen to /user/David/private
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private",
                message); //AUTOMATICALLY APPENDS the userdestinationprefix (/user)
        return message;
    }

    @SendTo("/chatroom/public")
//    @Scheduled(fixedRate = 5000)
    public void greeting() {
        System.out.println("Works!");
        Message message = new Message("Admin", "No-one", "Helloo!!", "Today", Status.MESSAGE);
        simpMessagingTemplate.convertAndSend("/chatroom/public",message);

    }

}
