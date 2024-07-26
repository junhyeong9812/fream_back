package com.kream.root.style.repository;

import com.kream.root.entity.Style;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.List;
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


    public List<Style> findByStyleDateBetween(LocalDateTime start, LocalDateTime end) {
        TypedQuery<Style> query = entityManager.createQuery(
                "SELECT s FROM Style s WHERE s.styleDate BETWEEN :start AND :end", Style.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
    }
    public long countByStyleDateBetween(LocalDateTime start, LocalDateTime end) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(s) FROM Style s WHERE s.styleDate BETWEEN :start AND :end", Long.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getSingleResult();
    }
    public List<Style> findAll() {
        TypedQuery<Style> query = entityManager.createQuery(
                "SELECT s FROM Style s", Style.class);
        return query.getResultList();
    }

    public List<Style> findByStyleDate(LocalDateTime styleDate){ // date 기준 데이터 불러오기
        String jpql = "SELECT s FROM Style s WHERE s.styleDate = :styleDate";
        return entityManager.createQuery(jpql, Style.class)
                .setParameter("styleDate", styleDate)
                .getResultList();
    }

    public long count() {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(s) FROM Style s", Long.class);
        return query.getSingleResult();
    }


}