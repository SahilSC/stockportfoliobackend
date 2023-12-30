package com.gettingusedtoit.springpractice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//@Configuration
//@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Delivers messages to correct recipients
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        System.out.println("configureMessageBroker");
        config.setApplicationDestinationPrefixes("/app");
        //message destinations
        config.enableSimpleBroker("/chatroom", "/user");

        //app destinations
        config.setUserDestinationPrefix("/user");
    }

    /**
     * Entry point for websocket communication
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //sets endpoint
        System.out.println("registerStompEndpoints");
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
