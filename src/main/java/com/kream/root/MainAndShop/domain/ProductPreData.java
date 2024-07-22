package com.kream.root.MainAndShop.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "product_predata")
@SequenceGenerator(
        name = "product_predata_seq_generator",
        sequenceName = "product_predata_seq",
        allocationSize = 1
)
public class ProductPreData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_predata_seq_generator")
    private Long rwid;
    @Column
    private String reg_date;
    @Column
    private Long ranking;
    @Column
    private Long prid ;
    @Column
    private String name_kor;
    @Column
    private String name_eng;
    @Column
    private String brand;
    @Column
    private String color;
    @Column
    private String cat;
    @Column
    private String gender;
    @Column
    private String age;
    @Column
    private Long click;
    @Column
    private Double cr;
    @Column
    private Long trading_volume;
    @Column
    private Long total_price;

}

//rwid number primary key,
//reg_date Varchar2(20),
//ranking number,
//prid number,
//name_kor VARCHAR2(100),
//name_eng VARCHAR2(100),
//brand VARCHAR2(50),
//color VARCHAR2(10),
//cat VARCHAR2(10),
//gender VARCHAR2(10),
//age Varchar2(10),
//click number,
//cr number,
//trading_volume number,
//total_price number
