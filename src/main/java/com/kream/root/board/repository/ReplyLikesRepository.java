package com.kream.root.board.repository;

import com.kream.root.entity.ReplyLikes;
import com.kream.root.entity.ReplyLikesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyLikesRepository extends JpaRepository<ReplyLikes, ReplyLikesId> {
}
