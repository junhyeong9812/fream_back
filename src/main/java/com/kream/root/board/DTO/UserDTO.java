package com.kream.root.board.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int ulid;
    private String userId;
    private String userName;
    private String email;

    // Getters and Setters
}
