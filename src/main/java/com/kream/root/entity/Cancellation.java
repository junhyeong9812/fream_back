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


    @Column(name = "imp_uid", nullable = false)
    private String impUid;

    @Column(name = "merchant_uid", nullable = false)
    private String merchantUid;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "cancelled_at", nullable = false)
    private LocalDateTime cancelledAt;

    @Column(name = "refund_holder", nullable = false)
    private String refundHolder;

    @Column(name = "refund_bank", nullable = false)
    private String refundBank;

    @Column(name = "refund_account", nullable = false)
    private String refundAccount;

    @Column(name = "refund_tel", nullable = false)
    private String refundTel;
}
