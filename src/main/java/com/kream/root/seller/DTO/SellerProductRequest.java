package com.kream.root.seller.DTO;

import lombok.Data;

@Data
public class SellerProductRequest {
    private Long productId;
    private String proSize;
    private String address;
    private String accountHolder;
    private String bankName;
    private String accountNumber;
    private String name;
    private String phoneNumber;
}
