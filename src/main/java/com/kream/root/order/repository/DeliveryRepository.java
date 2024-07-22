package com.kream.root.order.repository;


import com.kream.root.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Optional<Delivery> findByOrderOrderId(Long orderId);
    List<Delivery> findByDeliveryStatus(String status);
}
