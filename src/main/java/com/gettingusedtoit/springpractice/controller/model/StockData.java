package com.gettingusedtoit.springpractice.controller.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
//if dont work, use explicit constructor
public class StockData {
    String name;
    double bidPrice;
    int bidSize;
    double askPrice;
    int askSize;
//
//    public StockData(String n, double bp, int bs, double ap, int as) {
//        name = n;
//        bidPrice = bp;
//        bidSize = bs;
//        askPrice = ap;
//        askSize = as;
//    }



}
