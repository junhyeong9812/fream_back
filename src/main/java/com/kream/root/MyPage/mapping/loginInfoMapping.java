package com.kream.root.MyPage.mapping;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
public class loginInfoMapping {
    private String email;
    private String oldPassword;
    private String newPassword;
    private String phone;
    private String user_size;
    private String receive_email;
    private String receive_message;
}
