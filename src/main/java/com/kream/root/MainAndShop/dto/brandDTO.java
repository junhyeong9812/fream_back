package com.kream.root.MainAndShop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class brandDTO {

    private String brand ;
    private Long brandCnt;


}
