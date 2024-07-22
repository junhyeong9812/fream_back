package com.kream.root.board.repository;


import com.kream.root.entity.BoardLikes;
import com.kream.root.entity.BoardLikesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardLikesRepository extends JpaRepository<BoardLikes, BoardLikesId> {
}
