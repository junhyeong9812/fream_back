package com.kream.root.Login.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoUserDTO {
    private String kakaoEmail;
    private String kakaoNickname;
}
