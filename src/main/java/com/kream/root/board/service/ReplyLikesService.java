package com.kream.root.board.service;

import com.kream.root.board.repository.ReplyLikesRepository;
import com.kream.root.entity.ReplyLikes;
import com.kream.root.entity.ReplyLikesId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReplyLikesService {
    @Autowired
    private ReplyLikesRepository replyLikesRepository;

    public ReplyLikes saveLike(ReplyLikes replyLikes) {
        return replyLikesRepository.save(replyLikes);
    }

    public Optional<ReplyLikes> getLike(ReplyLikesId id) {
        return replyLikesRepository.findById(id);
    }

    public void deleteLike(ReplyLikesId id) {
        replyLikesRepository.deleteById(id);
    }
}
