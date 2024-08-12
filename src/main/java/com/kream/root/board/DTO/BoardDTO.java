package com.kream.root.board.DTO;
import com.kream.root.Login.model.UserListDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long boardId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
//    private UserListDTO user;
private UserDTO user; // UserListDTO 대신 UserDTO 사용
}
