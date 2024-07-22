package com.kream.root.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kream.root.Login.model.UserListDTO;
import com.kream.root.MainAndShop.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WISHES")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wish_seq_gen")
    @SequenceGenerator(name = "wish_seq_gen", sequenceName = "WISH_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "USERID")
    @JsonBackReference
    private UserListDTO user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @Column(name = "Pro_Size")
    private String size;  // 제품의 사이즈를 저장
}