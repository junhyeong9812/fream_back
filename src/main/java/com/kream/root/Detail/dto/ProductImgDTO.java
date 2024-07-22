package com.kream.root.Detail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImgDTO {
private long prIid;
private long prId;
private String productImgName;
private LocalDateTime regDate;
}