package com.kream.root.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_product_interaction")
public class UserProductInteraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "interaction_time", nullable = false)
    private LocalDateTime interactionTime = LocalDateTime.now();

    public UserProductInteraction(String userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }
}
