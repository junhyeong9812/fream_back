package com.kream.root.Detail.repository;

import com.kream.root.Detail.repository.UserBigData.bigData;
import com.kream.root.entity.UserBigData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserBigDataRepository extends JpaRepository<UserBigData, Long>, bigData {
    public List<UserBigData> findByUbDate(LocalDateTime dateTime);
}
