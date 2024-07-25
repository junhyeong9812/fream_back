package com.kream.root.MyPage.mapping;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImgMapper {
    MultipartFile file;
}
