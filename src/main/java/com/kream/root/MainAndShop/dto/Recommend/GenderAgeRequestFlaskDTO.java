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
public class GenderAgeRequestFlaskDTO {

    private String loginUser_gender;
    private int loginUser_age;
    private List<GenderAgeRecommendDTO> recommendList;

}
