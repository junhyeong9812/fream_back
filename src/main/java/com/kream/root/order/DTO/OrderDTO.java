package com.kream.root.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    private String orderCode;
    private String user;
    private LocalDateTime orderDate;
    private int price;
    private String deliveryStatus;
    private LocalDateTime deliveryDate;

    // Product-related fields
    private Long productId;
    private String productNameKor;
    private String productNameEng;
    private String productCategory;
    private String productBrand;
    private String productColor;
    private String productGender;
    private int productPrice;
    private String productMainImageUrl; // 단일 이미지 URL
}
