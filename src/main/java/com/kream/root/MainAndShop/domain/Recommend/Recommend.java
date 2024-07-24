package com.kream.root.MainAndShop.domain.Recommend;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kream.root.Login.model.UserListDTO;
import com.kream.root.MainAndShop.domain.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "recommend")
@SequenceGenerator(name = "recommend_seq_gen", sequenceName = "recommend_seq", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Recommend {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recommend_age_gender_seq")
    private Long recommend_id;

    private LocalDateTime date;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "prId", nullable = false, referencedColumnName = "prid")
    @JsonBackReference
    private Product product;

//    @ManyToOne(targetEntity = RecommendOrders.class, fetch = FetchType.LAZY)
//    @JoinColumn(name = "orderId", referencedColumnName = "id")
//    @JsonBackReference
//    private RecommendOrders RecommendOrders;// gender, age 별 count
//
//    @ManyToOne(targetEntity = RecommendClick.class, fetch = FetchType.LAZY)
//    @JoinColumn(name = "clickId", referencedColumnName = "id")
//    @JsonBackReference
//    private RecommendClick recommendClick;
//
////    @ManyToOne(targetEntity = RecommendWish.class, fetch = FetchType.LAZY)
////    private int wish;
//
//    @ManyToOne(targetEntity = RecommendWish.class, fetch = FetchType.LAZY)
//    @JoinColumn(name = "styleId", referencedColumnName = "id")
//    @JsonBackReference
//    private int style;

    private int totPrice;

    @ManyToOne(targetEntity = UserListDTO.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "userid")
    @JsonBackReference
    private UserListDTO user;


}
