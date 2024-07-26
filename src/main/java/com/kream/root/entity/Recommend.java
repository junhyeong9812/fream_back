package com.kream.root.entity;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.MainAndShop.domain.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recommend")
@SequenceGenerator(
        name = "recommend_seq_gen", sequenceName = "recommend_seq", allocationSize = 1
)
@ToString(exclude = {"product", "user"})
public class Recommend {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recommend_seq_gen")
    private Long recommend_id;

    @Column(nullable = false)
    private LocalDate recommend_date;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "prid", referencedColumnName = "prid")
    private Product product;

//    @Column(name = "color")
//    private String clear_color;
//
//    @Column
//    private String brand;

//    @Column
//    private int price;

    @Column(name = "quantity")
    private int quantity;

    @Column
    private int click;

    @Column
    private int style;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserListDTO.class)
    @JoinColumn(name = "ulid", referencedColumnName = "ulid")
    private UserListDTO user;
//    private String gender;
//    private int age;



}
