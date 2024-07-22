package com.kream.root.style.repository;

import com.kream.root.entity.Style;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.*;

import java.util.Optional;


@Repository
public class StyleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Style save(Style style) {
        if (style.getId() == null) {
            entityManager.persist(style);
            return style;
        } else {
            return entityManager.merge(style);
        }
    }

    @Transactional
    public void updateStyleContent(Long styleId, String content) {
        Style style = entityManager.find(Style.class, styleId);
        if (style != null) {
            style.setContent(content);
            entityManager.merge(style);
        }
    }

    @Transactional
    public void deleteStyle(Long styleId) {
        Style style = entityManager.find(Style.class, styleId);
        if (style != null) {
            entityManager.remove(style);
        }
    }

    public Optional<Style> findById(Long styleId) {
        Style style = entityManager.find(Style.class, styleId);
        return Optional.ofNullable(style);
    }
}