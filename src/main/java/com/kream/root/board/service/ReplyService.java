package com.kream.root.board.service;

import com.kream.root.board.repository.ReplyRepository;
import com.kream.root.entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    public Reply saveOrUpdateReply(Reply reply) {
        return replyRepository.save(reply);
    }

    public Optional<Reply> getReplyById(Long id) {
        return replyRepository.findById(id);
    }

    public List<Reply> getAllReplies() {
        return replyRepository.findAll();
    }

    public void deleteReply(Long id) {
        replyRepository.deleteById(id);
    }

}
