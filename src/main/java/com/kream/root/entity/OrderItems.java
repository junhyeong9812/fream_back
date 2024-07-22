package com.kream.root.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kream.root.MainAndShop.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "order")
@Table(name = "order_items")
@SequenceGenerator(
        name = "order_items_seq_generator",
        sequenceName = "order_items_seq",
        allocationSize = 1
)
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_items_seq_generator")
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private int price;

}
