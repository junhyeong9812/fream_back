package com.kream.root.Login.repository;



import com.kream.root.Login.model.UserListDTO;
import com.kream.root.MyPage.mapping.ProfileNameMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserListRepository extends JpaRepository<UserListDTO, Integer> { 



	UserListDTO getByUserId(String id);
//    Optional<UserListDTO> findByUserId(String id);
    Optional<UserListDTO> findByUserId(String userId);
    List<ProfileNameMapping> findAllByProfileNameIsNotNull();
	@Modifying
    @Transactional
    @Query("UPDATE UserListDTO u SET u.lastLoginTime = CURRENT_TIMESTAMP WHERE u.userId = :userId")
    void updateLastLoginTime(@Param("userId") 	String userId);
}