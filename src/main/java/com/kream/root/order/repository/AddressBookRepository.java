package com.kream.root.order.repository;


import com.kream.root.entity.AddressBook;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("orderAddressBookRepository")
public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {
    List<AddressBook> findByUserUlid(int ulid);
    @Query("select a from AddressBook a where a.user.ulid = :ulid")
    List<AddressBook> listAddressBook ( int ulid);
}