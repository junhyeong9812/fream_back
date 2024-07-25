package com.kream.root.MainAndShop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendDTO {

    private Long id;
    private LocalDateTime date;
    private String prId;
    private int price;
    private String category;
    private String clear_color;
    private String brand;
    private String info;
    private int quantity;
    private int ub_click_count;
    private int wish;
    private String gender;
    private int age;
    private int paid_amount;
    private double cr;

    private void setAge(){
        this.age = (int)(age / 10) * 10;
    }
    private void setCr(){
        this.cr = (double) quantity / ub_click_count;
    }

}
//(ORDERS/USER_BIGDATA)date	(PRODUCT)PRID	(PRODUCT)name_eng	(PRODUCT)name_kor	(PRODUCT)price	(PRODUCT)category
// (PRODUCT)clear_color	(PRODUCT)gender	(PRODUCT)brand	(PRODUCT)recent_trading_price	...	(PRODUCT)reg_date	(ORDERS)quantity
    //(USER_BIGDATA)click	(WISH)wish	(REVIEW)review	(USER)gender	(USER)age	(ORDERS)tot_price	CR	orderUser