package com.kream.root.main.CSVParser;



import lombok.extern.log4j.Log4j2;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

@Log4j2
public class OrderDataParser implements Parser<OrderData> {
    @Override
    public OrderData parse(String str) {
        log.info("str" + str);
        String[] splitted = str.split(",");
        log.info("splitted" + splitted[0]);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        OrderData orderData = new OrderData();
        orderData.setOrderCode(splitted[0]);
        orderData.setOrderDate(LocalDateTime.parse(splitted[1], dateTimeFormatter));
        orderData.setPrId(Long.parseLong(splitted[2]));
        orderData.setPriceAmount(Integer.parseInt(splitted[3]));
        orderData.setUser(Integer.parseInt(splitted[4]));

        log.info(orderData);

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
