package com.kream.root.Detail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneProductDTO {
    private long prid;
    private String nameKor;
    private String nameEng;
    private String category;
    private String brand;
    private String color;
    private int price;
    private String info;
    private String gender;
    private String imgName;
    private String linkedImgName;
}