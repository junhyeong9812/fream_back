package com.kream.root.entity;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.MainAndShop.domain.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@ToString
@Table(name = "product_qna")
@SequenceGenerator(
        name = "product_qna_seq_generator",
        sequenceName = "product_qna_seq",
        allocationSize = 1
)
public class ProductQna {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_qna_seq_generator")
    private Long qnaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserListDTO user;

    @Lob
    @Column(name = "QUESTION", columnDefinition = "CLOB", nullable = false)
    private String question;

    @Lob
    @Column(name = "ANSWER", columnDefinition = "CLOB")
    private String answer;

    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "ANSWERED_DATE")
    private LocalDateTime answeredDate;

    @Column(name = "IS_ANSWERED", columnDefinition = "CHAR(1) DEFAULT '0'")
    private char isAnswered = '0';
}
