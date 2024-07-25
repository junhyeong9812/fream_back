package com.kream.root.Detail.repository;

import com.kream.root.entity.OrderItems;
import com.kream.root.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceChartRepository extends JpaRepository<Orders, Long> {

}