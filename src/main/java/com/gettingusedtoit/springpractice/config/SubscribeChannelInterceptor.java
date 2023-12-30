package com.gettingusedtoit.springpractice.config;

import com.gettingusedtoit.springpractice.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

//@Component
public class SubscribeChannelInterceptor implements ChannelInterceptor {

//    @Autowired
//    StockService stockService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        MessageHeaderAccessor mha = new MessageHeaderAccessor(message);
//        System.out.println("MHA "  + mha);
        SimpMessageType smt = (SimpMessageType) message.getHeaders().get("simpMessageType");
        String stomptype = smt.toString();
        System.out.println("Stomp type: " + stomptype );
        System.out.println(message.getHeaders());
        if (stomptype.equals("SUBSCRIBE")) {
//            System.out.println(message.getHeaders());
//            System.out.println(message.getPayload());
//        System.out.println(channel);
            LinkedMultiValueMap o = (LinkedMultiValueMap) message.getHeaders().get("nativeHeaders");
            String newer = (String) o.get("destination").get(0);
            String stockName = newer.split("/")[2];
            System.out.println("PRESEND ADD " + stockName);
            StockService.addStock(stockName);
            System.out.println("SUBSCRIBE STOCKS: " + StockService.getStocks());

//            System.out.println(newer);
//            System.out.println(Arrays.deepToString(newer.split("/")));
            System.out.println("SUBSCRIBE: " + stockName);
        }
        else if (stomptype.equals("UNSUBSCRIBE")) {
            LinkedMultiValueMap o = (LinkedMultiValueMap) message.getHeaders().get("nativeHeaders");
            String stockName = (String) o.get("id").get(0);
//            String stockName = newer.split("/")[2];
            System.out.println("UNSUBSCRIBE ADD " + stockName);
            StockService.removeStock(stockName);
            System.out.println("UNSUBSCRIBE STOCKS: " + StockService.getStocks());

//            System.out.println(newer);
//            System.out.println(Arrays.deepToString(newer.split("/")));
            System.out.println("UNSUBSCRIBE: " + stockName);
        }
        return message;
    }
}