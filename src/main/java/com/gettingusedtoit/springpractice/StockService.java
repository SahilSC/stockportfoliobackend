package com.gettingusedtoit.springpractice;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

@Getter
//@Service
//@ToString
public class StockService {

    //        System.out.println("GOT STOCK");
    @Getter
    static public TreeMap<String, Integer> stocks = new TreeMap<>();
    static {
//        stocks.add("AAPL");
//        stocks.add("MSFT");
//        stocks.add("TSLA");
    }


    public static void addStock(String stock) {
//        System.out.println("ADDED STOCK");
        if (!stocks.containsKey(stock)) {
            stocks.put(stock, 1);
        }
        else {
            stocks.put(stock, stocks.get(stock) + 1);
        }
    }

    public static void removeStock(String stock) {
        if (!stocks.containsKey(stock)) {
            System.out.println("ERROR. STOCK NOT TRACKED");
            return;
        }
        if (stocks.get(stock) == 1) {
            stocks.remove(stock);
        }
        else {
            stocks.put(stock, stocks.get(stock) - 1);
        }
    }


}
