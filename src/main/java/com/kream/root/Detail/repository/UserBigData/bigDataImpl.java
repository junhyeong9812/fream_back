package com.kream.root.Detail.repository.UserBigData;

import com.kream.root.Detail.dto.UserBigDataDTO;
import com.kream.root.Login.model.UserListDTO;
import com.kream.root.MainAndShop.domain.Product;

import com.kream.root.entity.QUserBigData;
import com.kream.root.entity.UserBigData;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class bigDataImpl extends QuerydslRepositorySupport implements bigData{

    public bigDataImpl(){ super(UserBigData.class); }

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Optional<UserBigData> getBigData(LocalDateTime LocalDateTime, UserListDTO userList, Product product) {

        QUserBigData userBigData = QUserBigData.userBigData;

        JPQLQuery<UserBigData> query = from(userBigData);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(userBigData.userListDTO.eq(userList));
        booleanBuilder.and(userBigData.product.eq(product));
<<<<<<< HEAD
        booleanBuilder.and(userBigData.ubDate.eq(LocalDateTime));
=======
        booleanBuilder.and(userBigData.ubDate.eq(localDate));
>>>>>>> 742c961135f8a214312833f60e330e5bf8417fe8

        query.where(booleanBuilder);

        List<UserBigData> dataList = query.fetch();
        Optional<UserBigData> data = null;
        if (!dataList.isEmpty()) {
            data = Optional.ofNullable(dataList.get(0));
        }
        return data;
    }
}
