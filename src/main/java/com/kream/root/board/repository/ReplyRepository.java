package com.kream.root.board.repository;

import com.kream.root.entity.Reply;
import com.kream.root.entity.ReplyLikes;
import com.kream.root.entity.ReplyLikesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
