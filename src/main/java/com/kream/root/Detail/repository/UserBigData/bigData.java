package com.kream.root.Detail.repository.UserBigData;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.entity.UserBigData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface bigData {
    public Optional<UserBigData> getBigData (LocalDate localDate, UserListDTO userList, Product product);
}
