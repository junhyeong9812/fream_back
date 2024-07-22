//package com.kream.root.MyPage.domain;
//
//import com.kream.root.Login.model.UserListDTO;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Builder
//@Entity
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "Address_Book")
//@SequenceGenerator(
//        name = "Address_seq_generator", sequenceName = "Address_book_seq", allocationSize = 1
//)
//@ToString(exclude = "userListDTO")
//public class AddressBook {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Address_seq_generator")
//    private Long address_id ;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "ulid")
//    private UserListDTO userListDTO ;
//    private String name;
//    private String phone;
//
//    @Column(name = "postal_code")
//    private String postalCode;
//    private String city;
//    private String street;
//    @Column(name = "detail_address")
//    private String detailAddress;
//
//    @Column(name = "is_default", columnDefinition = "default '0'")
//    private String isDefault;
//
//
//}
