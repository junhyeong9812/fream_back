package com.kream.root.MainAndShop.dto;

import com.kream.root.MainAndShop.domain.ProductInfo.ProductInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class productDTO {
    private long prid;
    private String nameKor;
    private String nameEng;
    private String category;
    private String brand;
    private String color;
    private String gender;
    private int price;
    private ProductInfo info;
    private LocalDateTime regDate;

}
//create table PRODUCT(
//        PRID NUMBER PRIMARY KEY,
//        PRODUCT_NAME_KOR VARCHAR2(1000) NOT NULL,
//PRODUCT_NAME_ENG VARCHAR2(1000) NOT NULL,
//PRODUCT_CATEGORY VARCHAR2(50) NOT NULL,
//PRODUCT_BRAND VARCHAR2(100),
//PRODUCT_COLOR VARCHAR2(50) NOT NULL,
//PRODUCT_PRICE NUMBER NOT NULL,
//PRODUCT_INFO VARCHAR2(100) ,
//PRODUCT_GENDER VARCHAR2(50) NOT NULL,     -- MAN,WOMAN,UNIVERSAL
//REG_DATE TIMESTAMP
//);