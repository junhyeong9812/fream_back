package com.kream.root.order.repository;

import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.entity.Orders;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
//    @EntityGraph(attributePaths = {"orderItems", "orderItems.product"})
//    List<Orders> findAllWithItemsAndProducts();
    List<Orders> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);
    long countByOrderDateBetween(LocalDateTime start, LocalDateTime end);
    List<Orders> findByUser_UserId(String userId);


    List<Orders> findByOrderDate(LocalDateTime dateTime);
//    @Query("SELECT SUM(oi.quantity) FROM Orders o JOIN o.orderItems oi WHERE oi.product.prid = :productId AND o.orderDate = :orderDate")
//    int sumQuantityByProductAndDate(@Param("productId") Long productId, @Param("orderDate") LocalDate orderDate);
    @Query("SELECT SUM(oi.quantity) FROM OrderItems oi WHERE oi.product.prid = :productId AND oi.order.orderDate BETWEEN :startDate AND :endDate")
    Integer sumQuantityByProductAndDate(Long productId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT oi.product FROM OrderItems oi WHERE oi.order.orderDate BETWEEN :startDate AND :endDate GROUP BY oi.product")
    List<Product> findDistinctProductsByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
