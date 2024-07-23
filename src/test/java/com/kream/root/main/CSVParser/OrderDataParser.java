package com.kream.root.main.CSVParser;


import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Locale;

public class OrderDataParser implements Parser<OrderData> {
    @Override
    public OrderData parse(String str) {
        String[] splitted = str.split("\", \"");
        OrderData orderData = new OrderData();
        orderData.setOrderCode(splitted[0].replace("\"", ""));
        orderData.setOrderDate(LocalDateTime.parse(splitted[1]));
        orderData.setPrId(Long.parseLong(splitted[2]));
        orderData.setPriceAmount(Integer.parseInt(splitted[3]));
        orderData.setUser(Integer.parseInt(splitted[4]));

        return orderData;
    }

//    @Override
//    public OrderData parse(String text, Locale locale) throws ParseException {
//        String[] splitted = text.split("\", \"");
//        OrderData orderData = new OrderData();
//        orderData.setOrderCode(splitted[0].replace("\"", ""));
//        orderData.setOrderDate(LocalDateTime.parse(splitted[1]));
//        orderData.setPrId(Long.parseLong(splitted[2]));
//        orderData.setPriceAmount(Integer.parseInt(splitted[3]));
//        orderData.setUser(Integer.parseInt(splitted[4]));
//
//        return orderData;
//    }
}
