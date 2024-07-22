package com.kream.root.board.service;


import com.kream.root.board.repository.BoardRepository;
import com.kream.root.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public Board saveOrUpdateBoard(Board board) {
        return boardRepository.save(board);
    }

    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
