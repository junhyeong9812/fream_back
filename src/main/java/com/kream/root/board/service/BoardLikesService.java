package com.kream.root.board.service;

import com.kream.root.board.repository.BoardLikesRepository;
import com.kream.root.entity.BoardLikes;
import com.kream.root.entity.BoardLikesId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardLikesService {

    @Autowired
    private BoardLikesRepository boardLikesRepository;

    public BoardLikes saveLike(BoardLikes boardLikes) {
        return boardLikesRepository.save(boardLikes);
    }

    public Optional<BoardLikes> getLike(BoardLikesId id) {
        return boardLikesRepository.findById(id);
    }

    public void deleteLike(BoardLikesId id) {
        boardLikesRepository.deleteById(id);
    }
    // 기타 서비스 메소드
}