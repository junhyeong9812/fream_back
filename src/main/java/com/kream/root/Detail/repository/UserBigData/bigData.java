package com.kream.root.Detail.repository.UserBigData;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.entity.UserBigData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface bigData {
<<<<<<< HEAD
    public Optional<UserBigData> getBigData (LocalDateTime localDateTime, UserListDTO userList, Product product);
=======
    public Optional<UserBigData> getBigData (LocalDate localDate, UserListDTO userList, Product product);
>>>>>>> 742c961135f8a214312833f60e330e5bf8417fe8
}
