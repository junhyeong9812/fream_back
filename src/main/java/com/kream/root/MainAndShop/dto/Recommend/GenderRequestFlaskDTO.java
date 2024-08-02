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
public class GenderRequestFlaskDTO {

    private String gender;
    private List<GenderAgeRecommendDTO> recommendList;

}
