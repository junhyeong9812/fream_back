package com.kream.root.Detail.repository;

import com.kream.root.Detail.repository.UserBigData.bigData;
import com.kream.root.entity.UserBigData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBigDataRepository extends JpaRepository<UserBigData, Long>, bigData {

}
