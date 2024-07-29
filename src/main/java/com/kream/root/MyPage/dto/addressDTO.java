package com.kream.root.MyPage.dto;

import com.kream.root.Login.model.UserListDTO;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class addressDTO {
    private Long address_id ;
    private String userId ;
    private String name;
    private String phone;
    private String postalCode;
    private String city;
    private String street;
//    private String detailAddress;
    private char isDefault;

//    public void setStreet(){
//        List<String> cityList =  Arrays.stream(city.split("시 ")).toList();
//        this.city = cityList.get(0) + "시";
//        this.street = cityList.get(1);
//    }
}
