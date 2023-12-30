package com.gettingusedtoit.springpractice;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledPushMessages {

//    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        System.out.println("Stuff");
    }
}
