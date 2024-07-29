package com.kream.root.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kream.root.Login.model.UserListDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user","orderItems"})
@Table(name = "orders")
@SequenceGenerator(
        name = "orders_seq_generator",
        sequenceName = "orders_seq",
        allocationSize = 1
)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq_generator")
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name="ORDER_CODE",nullable = false)
    private String orderCode;

    @Column(name="ORDER_DATE",nullable = false)
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserListDTO user;

    @ManyToOne
    @JoinColumn(name = "seller_product_id", nullable = true)
    private SellerProduct sellerProduct;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderItems> orderItems;

    // 추가된 필드
    @Column(name="APPLY_NUM",nullable = false)
    private String applyNum;

    @Column(name="BANK_NAME")
    private String bankName;

    @Column(name = "BUYER_ADDR", nullable = false)
    private String buyerAddr;

    @Column(name = "BUYER_EMAIL", nullable = false)
    private String buyerEmail;

    @Column(name = "BUYER_NAME", nullable = false)
    private String buyerName;

    @Column(name = "BUYER_POSTCODE", nullable = false)
    private String buyerPostcode;

    @Column(name = "BUYER_TEL", nullable = false)
    private String buyerTel;

    @Column(name = "CARD_NAME", nullable = false)
    private String cardName;

    @Column(name = "CARD_NUMBER", nullable = false)
    private String cardNumber;

    @Column(name = "CARD_QUOTA", nullable = false)
    private int cardQuota;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    @Column(name = "CUSTOM_DATA")
    private String customData;

    @Column(name = "IMP_UID", nullable = false)
    private String impUid;

    @Column(name = "MERCHANT_UID", nullable = false)
    private String merchantUid;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "PAID_AMOUNT", nullable = false)
    private double paidAmount;

    @Column(name = "PAID_AT", nullable = false)
    private long paidAt;

    @Column(name = "PAY_METHOD", nullable = false)
    private String payMethod;

    @Column(name = "PG_PROVIDER", nullable = false)
    private String pgProvider;

    @Column(name = "PG_TID", nullable = false)
    private String pgTid;

    @Column(name = "PG_TYPE", nullable = false)
    private String pgType;

    @Column(name = "RECEIPT_URL", nullable = false)
    private String receiptUrl;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "SUCCESS", nullable = false)
    private boolean success;
}
