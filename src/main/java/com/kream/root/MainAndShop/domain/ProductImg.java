package com.kream.root.MainAndShop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "product_img")
@SequenceGenerator(
        name = "productImg_seq_generator",
        sequenceName = "product_img_seq",
        allocationSize = 1
)
public class ProductImg {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productImg_seq_generator")
    private Long priid;
    @Column(name = "prid", insertable = false, updatable = false)
    private Long prid;

    @Column(name="PRODUCT_IMGNAME")
    private String imgName;
    @Column(name="PRODUCT_LINKED_IMGNAME")
    private String linkedImgName;

    private LocalDateTime reg_date ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prid", nullable = false)
    @JsonBackReference  // Add this line
    private Product product;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "prid", nullable = false)
//    private Product product;
}

//create table PRODUCT_IMG(
//        PRIID NUMBER PRIMARY KEY,
//        PRID NUMBER NOT NULL,
//        PRODUCT_IMGNAME VARCHAR2(200),
//    -- PRODUCT_IMGPATH VARCHAR2(500) NOT NULL,
//REG_DATE TIMESTAMP,
//CONSTRAINT FK_PI_PRID FOREIGN KEY (PRID) REFERENCES PRODUCT (PRID)
//
//        );
