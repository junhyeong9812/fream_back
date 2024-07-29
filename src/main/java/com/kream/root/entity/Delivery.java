package com.kream.root.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "delivery")
@SequenceGenerator(
        name = "delivery_seq_generator",
        sequenceName = "delivery_seq",
        allocationSize = 1
)
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_seq_generator")
    private Long deliveryId;

    @Column(name = "delivery_status", nullable = false)
    private String deliveryStatus;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "courier_name", length = 100)
    private String courierName;  // 택배사 이름 필드 추가

    @Column(name = "tracking_number", length = 100)
    private String trackingNumber;  // 발송장번호 필드 추가

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;
}
