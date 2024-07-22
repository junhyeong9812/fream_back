package com.kream.root.Detail.service;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.entity.UserBigData;

public interface ProductBigDataService {

    public UserBigData ClickUp (String User_id, Long PrId);
}
