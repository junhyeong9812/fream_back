package com.kream.root.Wish.repository;

import com.kream.root.entity.Wish;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class WishRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Wish> findByUserIdAndProductId(String userId, Long productId) {
        TypedQuery<Wish> query = entityManager.createQuery(
                "SELECT w FROM Wish w WHERE w.user.userId = :userId AND w.product.prid = :productId",
                Wish.class
        );
        query.setParameter("userId", userId);
        query.setParameter("productId", productId);

        return query.getResultList().stream().findFirst();
    }

    public void save(Wish wish) {
        entityManager.persist(wish);
    }

    public void delete(Wish wish) {
        entityManager.remove(entityManager.contains(wish) ? wish : entityManager.merge(wish));
    }
}
