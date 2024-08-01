package com.kream.root.Login.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Update_Pw {
    private String phonNum;
    private String email;
    private String temporaryPassword;
}
