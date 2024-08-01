package com.kream.root.Login.repository;



import com.kream.root.Login.model.UserListDTO;
import com.kream.root.MyPage.mapping.ProfileNameMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserListRepository extends JpaRepository<UserListDTO, Integer> {
    Optional<UserListDTO> findByPhone(String phone);
    Optional<UserListDTO> findByUserIdAndPhone(String userId, String phone);
    @Modifying
    @Transactional
    @Query("UPDATE UserListDTO u SET u.userPw = :password WHERE u.userId = :userId")
    int updatePassword(@Param("userId") String userId, @Param("password") String password);

    @Query("SELECT u FROM UserListDTO u JOIN u.styles s WHERE s.id = :styleId")
    Optional<UserListDTO> findByStyleId(@Param("styleId") Long styleId);
	UserListDTO getByUserId(String id);
//    Optional<UserListDTO> findByUserId(String id);
    Optional<UserListDTO> findByUserId(String userId);
    List<ProfileNameMapping> findAllByProfileNameIsNotNull();
    List<UserListDTO> findByJoinDateBetween(LocalDate start, LocalDate end);
    long countByJoinDateBetween(LocalDate start, LocalDate end);
	@Modifying
    @Transactional
    @Query("UPDATE UserListDTO u SET u.lastLoginTime = CURRENT_TIMESTAMP WHERE u.userId = :userId")
    void updateLastLoginTime(@Param("userId") 	String userId);
    Page<UserListDTO> findAll(Pageable pageable);
    @Query("SELECT u FROM UserListDTO u JOIN u.boards b WHERE b.boardId = :boardId")
    UserListDTO findUserByBoardId(@Param("boardId") Long boardId);


}