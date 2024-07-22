package com.kream.root.MainAndShop.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OneProductDTO {
    private long prid;
    private String nameKor;
    private String brand;
    private int price;
    private String color;
    private String gender;
    private String imgName;

    public void setImgName(String imgName){
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> imgList = new ArrayList<>();
        try {
            imgName = imgName.replace("'", "\"");
            imgList = objectMapper.readValue(imgName, new TypeReference<List<String>>() {
            });
            this.imgName = imgList.get(0);
        } catch (Exception e ){
            e.printStackTrace();
        }

    }
}
