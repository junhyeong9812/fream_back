package com.kream.root.entity;

import com.kream.root.Login.model.UserListDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "address_book")
@SequenceGenerator(
        name = "address_book_seq_generator",
        sequenceName = "address_book_seq",
        allocationSize = 1
)
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_book_seq_generator")
    private Long addressId;
    //주소록

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "ulid" ,nullable = false)
    private UserListDTO user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;


    @Column(nullable = false, columnDefinition = "CHAR(1) DEFAULT '0'")
    private char isDefault = '0';
    //처음0 만약 처음 등록한 주소지일 경우 1로 등록
    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private AddressBook addressBook;
}
