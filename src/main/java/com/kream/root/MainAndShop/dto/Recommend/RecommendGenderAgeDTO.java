package com.kream.root.MainAndShop.dto.Recommend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendGenderAgeDTO {

    private String loginUser_gender;
    private int loginUser_age;
    private List<RecommendDTO> recommendList;

}
