package com.kream.root.style.repository;

import com.kream.root.entity.StyleLike;
import com.kream.root.entity.Style;
import com.kream.root.Login.model.UserListDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.*;
import java.util.Optional;

@Repository
public class StyleLikeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public StyleLike save(StyleLike styleLike) {
        if (styleLike.getId() == null) {
            entityManager.persist(styleLike);
            return styleLike;
        } else {
            return entityManager.merge(styleLike);
        }
    }

    @Transactional
    public void delete(StyleLike styleLike) {
        entityManager.remove(styleLike);
    }

    public Optional<StyleLike> findByUserAndStyle(UserListDTO user, Style style) {
        String query = "SELECT sl FROM StyleLike sl WHERE sl.user = :user AND sl.style = :style";
        return entityManager.createQuery(query, StyleLike.class)
                .setParameter("user", user)
                .setParameter("style", style)
                .getResultList()
                .stream()
                .findFirst();
    }
}
