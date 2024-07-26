package com.kream.root.entity;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.MainAndShop.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "seller_product")
@SequenceGenerator(
        name = "seller_product_seq_generator",
        sequenceName = "seller_product_seq",
        allocationSize = 1
)
public class SellerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seller_product_seq_generator")
    private Long sellerProductId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserListDTO user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private String proSize;
    @Column(nullable = false)
    private String Address;
    @Column(nullable = false)
    private String accountHolder;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "CHAR(1) DEFAULT 'N'")
    private char isSold = 'N';
}
