//package com.kream.root.MyPage.repository;
//
//
//import com.kream.root.entity.AddressBook;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {
//
//    @Query("select a from AddressBook a where a.userListDTO.ulid = :ulid")
//    List<AddressBook> listAddressBook (int ulid);
////
//}
