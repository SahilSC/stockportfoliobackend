package com.gettingusedtoit.springpractice.controller;

import com.gettingusedtoit.springpractice.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import com.gettingusedtoit.springpractice.controller.model.StockData;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
//@Component
@EnableScheduling
public class StockController {

    //    static String[] stocks = {"AAPL", "MSFT", "TSLA"};
//    static ArrayList<String> stocks = new ArrayList<>();
//    static {
//        stocks.add("AAPL");
////        stocks.add("MSFT");
////        stocks.add("TSLA");
//    }
    static int i = 0;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

//    @Autowired
//    StockService stockService;

//    @MessageMapping("/getAllStocks")
//    @SendTo("/topic")
//    public StockData getPublicStockData(@Payload StockData stockData) {
//        System.out.println(stockData);
//        System.out.println("Get Public Stock Data");
//        return stockData;
//    }

    @Scheduled(fixedRate = 4000)
    public void testing() {

//        String simulatedName = "";
//        for (int i = 0; i < 5; i++) {
//            simulatedName += (char)('A' + (int) (Math.random() * 26));
//        }
//        StockData sampleStock = new StockData(stocks[(int) (Math.random()*4)], Math.round(Math.random()*50000)/100.0, (int) (Math.random()*100),
//                 Math.round(Math.random()*50000)/100.0, (int) (Math.random()*100));
//        StockData sampleStock = new StockData(stocks[(i++) % stocks.length], Math.round(Math.random()*50000)/100.0, (int) (Math.random()*100),
//                 Math.round(Math.random()*50000)/100.0, (int) (Math.random()*100));
        ArrayList<String> stocks = new ArrayList<>(StockService.getStocks().keySet());
        if (stocks.isEmpty()) return;
        System.out.println("TESTING " + StockService.getStocks());
        String name = stocks.get((i++) % stocks.size());
        StockData sampleStock = new StockData(name, Math.round(Math.random() * 50000) / 100.0, (int) (Math.random() * 100),
                Math.round(Math.random() * 50000) / 100.0, (int) (Math.random() * 100));
//        StockData sampleStock = new StockData(stocks[(i++) % stocks.length], 12, (int) (Math.random()*100),
//                 Math.random()*500, (int) (Math.random()*100));
        simpMessagingTemplate.convertAndSend("/topic/" + name, sampleStock);
    }
//    @MessageMapping("/tracking-stock")
//    public StockData getTrackingStockData(@Payload StockData stockData) {
//        simpMessagingTemplate.convertAndSendToUser();
//    }
}

