package com.kream.root.MainAndShop.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendOrdersDTO {

    private LocalDate date;

    private int quantity; // gender, age 별 count

    private int paidAmount;
//    private int click;
//    private int wish;
//    private int style;
//    private String gender; //userList
//    private String age;

}
//CR