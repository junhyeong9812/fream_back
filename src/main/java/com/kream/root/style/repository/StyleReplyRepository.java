package com.kream.root.style.repository;

import com.kream.root.entity.StyleReply;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.*;

@Repository
public class StyleReplyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public StyleReply save(StyleReply styleReply) {
        if (styleReply.getId() == null) {
            entityManager.persist(styleReply);
            return styleReply;
        } else {
            return entityManager.merge(styleReply);
        }
    }

    @Transactional
    public void delete(StyleReply styleReply) {
        entityManager.remove(styleReply);
    }

    public StyleReply findById(Long replyId) {
        return entityManager.find(StyleReply.class, replyId);
    }
}