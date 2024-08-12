package com.kream.root.board.controller;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.board.DTO.BoardDTO;
import com.kream.root.board.service.BoardService;
import com.kream.root.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private UserListRepository userListRepository;



    @PostMapping
    public ResponseEntity<Board> createBoard(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("user") String userId) throws IOException {

        // 파일 처리 로직
        String fileName = file != null ? file.getOriginalFilename() : null;
        // 실제 파일 저장 로직 필요

        // 사용자 정보 로직
        UserListDTO user = userListRepository.getByUserId(userId);
//        user.setUserId(userId); // 실제 사용자 ID로 설정

        // Board 엔티티 생성
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setCreatedDate(LocalDateTime.now());
        board.setUser(user);

        // 파일 이름을 게시글에 설정
        // board.setFileName(fileName); // 필요에 따라 설정

        Board savedBoard = boardService.saveOrUpdateBoard(board);
        return ResponseEntity.ok(savedBoard);
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
//        Optional<Board> board = boardService.getBoardById(id);
//        return board.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }

//    @GetMapping
//    public ResponseEntity<List<Board>> getAllBoards() {
//        return ResponseEntity.ok(boardService.getAllBoards());
//    }

    @GetMapping
    public ResponseEntity<List<BoardDTO>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoardsWithUserDetails());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getBoardById(@PathVariable Long id) {
        Optional<BoardDTO> board = boardService.getBoardDTOById(id);
        return board.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(
            @PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("user") String userId) throws IOException {

        // 파일 처리 로직
        String fileName = file != null ? file.getOriginalFilename() : null;
        // 실제 파일 저장 로직 필요

        // 사용자 정보 로직
        UserListDTO user = userListRepository.getByUserId(userId);

        // Board 엔티티 수정
        Optional<Board> optionalBoard = boardService.getBoardById(id);
        if (!optionalBoard.isPresent()) {
            return ResponseEntity.notFound().build();
        }


        Board board = optionalBoard.get();
        board.setTitle(title);
        board.setContent(content);
        board.setUser(user);
//        board.setUpdatedDate(LocalDateTime.now());

        if (fileName != null) {
            // board.setFileName(fileName); // 필요에 따라 설정
        }

        Board updatedBoard = boardService.saveOrUpdateBoard(board);
        return ResponseEntity.ok(updatedBoard);
    }
}
