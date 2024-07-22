package com.kream.root.entity;

import com.kream.root.Login.model.UserListDTO;
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
@Table(name = "cancellations")
@SequenceGenerator(
        name = "cancellations_seq_generator",
        sequenceName = "cancellations_seq",
        allocationSize = 1
)
public class Cancellation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cancellations_seq_generator")
    private Long cancellationId;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserListDTO user;

    @Column(nullable = false)
    private String impUid;

    @Column(nullable = false)
    private String merchantUid;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private LocalDateTime cancelledAt;

    @Column(nullable = false)
    private String refundHolder;

    @Column(nullable = false)
    private String refundBank;

    @Column(nullable = false)
    private String refundAccount;

    @Column(nullable = false)
    private String refundTel;
}
