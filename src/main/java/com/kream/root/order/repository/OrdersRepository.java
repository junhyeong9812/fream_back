package com.kream.root.order.repository;

import com.kream.root.entity.Orders;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
//    @EntityGraph(attributePaths = {"orderItems", "orderItems.product"})
//    List<Orders> findAllWithItemsAndProducts();

    List<Orders> findByOrderDate(LocalDateTime dateTime);
}
