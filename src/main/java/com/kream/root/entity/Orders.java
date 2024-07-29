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

    @Column(nullable = false)
    private String buyerAddr;

    @Column(nullable = false)
    private String buyerEmail;

    @Column(nullable = false)
    private String buyerName;

    @Column(nullable = false)
    private String buyerPostcode;

    @Column(nullable = false)
    private String buyerTel;

    @Column(nullable = false)
    private String cardName;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private int cardQuota;

    @Column(nullable = false)
    private String currency;

    @Column
    private String customData;

    @Column(nullable = false)
    private String impUid;

    @Column(nullable = false)
    private String merchantUid;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private double paidAmount;

    @Column(nullable = false)
    private long paidAt;

    @Column(nullable = false)
    private String payMethod;

    @Column(nullable = false)
    private String pgProvider;

    @Column(nullable = false)
    private String pgTid;

    @Column(nullable = false)
    private String pgType;

    @Column(nullable = false)
    private String receiptUrl;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private boolean success;
}
