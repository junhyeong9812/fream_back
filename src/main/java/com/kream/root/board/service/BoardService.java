package com.kream.root.board.service;


import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.board.DTO.BoardDTO;
import com.kream.root.board.DTO.UserDTO;
import com.kream.root.board.repository.BoardRepository;
import com.kream.root.entity.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserListRepository userListRepository;

    public Board saveOrUpdateBoard(Board board) {
        return boardRepository.save(board);
    }

    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }
@Transactional
public Optional<BoardDTO> getBoardDTOById(Long id) {
    return boardRepository.findById(id).map(this::convertToDTO);
}


    //    @Transactional
////    public List<Board> getAllBoards() {
////        return boardRepository.findAll();
//////        List<Board> boards = boardRepository.findAll();
//////        return boards.stream().map(this::convertToDTO).collect(Collectors.toList());
////    }
@Transactional
public List<BoardDTO> getAllBoardsWithUserDetails() {
    List<Board> boards = boardRepository.findAll();
    return boards.stream().map(this::convertToDTO).collect(Collectors.toList());
}
    private UserDTO convertToUserDTO(UserListDTO user) {
        if (user == null) return null;

        UserDTO userDTO = new UserDTO();
        userDTO.setUlid(user.getUlid());
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    private BoardDTO convertToDTO(Board board) {
        UserListDTO user = userListRepository.findById(board.getUser().getUlid()).orElse(null);
//                .orElseThrow(() -> new RuntimeException("User not found"));

        return new BoardDTO(
                board.getBoardId(),
                board.getTitle(),
                board.getContent(),
                board.getCreatedDate(),
                convertToUserDTO(user)
        );
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
//    private BoardDTO convertToDTO(Board board) {
//        // 명시적으로 user 엔티티 로드
//        UserListDTO user = userListRepository.findUserByBoardId(board.getBoardId());
//
//        return new BoardDTO(
//                board.getBoardId(),
//                board.getTitle(),
//                board.getContent(),
//                board.getCreatedDate(),
//                user != null ? user : null // 작성자 정보 포함, 없으면 null
//        );
//    }
}
