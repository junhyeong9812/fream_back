package com.kream.root.style.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StyleDTO {
    private Long id;
    private String imageUrl;
    private String profileUrl;
    private String username;
    private String content;
}
