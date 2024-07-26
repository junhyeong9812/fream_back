package com.kream.root.Detail.service;

import com.kream.root.Detail.dto.OneProductDTO;
import com.kream.root.Detail.repository.ProductDetailRepository;
import com.kream.root.Detail.repository.UserBigDataRepository;
import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.entity.UserBigData;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Log4j2
public class ProductBigDataServiceImpl implements ProductBigDataService{

    @Autowired
    UserBigDataRepository userBigDataRepository;
    @Autowired
    UserListRepository userListRepository;
    @Autowired
    ProductDetailRepository productDetailRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserBigData ClickUp(String User_id, Long PrId) {
        LocalDateTime currentDate = LocalDateTime.now();

        UserListDTO userList = userListRepository.findByUserId(User_id).orElseGet(
                ()-> {
                    throw new IllegalArgumentException("없는 정보 입니다.");
                });
        OneProductDTO dto = productDetailRepository.getProductDetail(PrId).get(0);
        Product product = modelMapper.map(dto, Product.class);
//                orElseGet(
//                ()-> {
//                    throw new IllegalArgumentException("없는 상품 입니다");
//                }
//        );

        log.info(userList);
        log.info(product);

        Optional<UserBigData> data = userBigDataRepository.getBigData(currentDate, userList, product);

        if (data.isEmpty()){
            UserBigData yes = addList(userList, product);
            return yes;
        }
        data.get().setUb_clickCount(data.get().getUb_clickCount() + 1);

        UserBigData yes2 = userBigDataRepository.save(data.orElseThrow());
        return yes2;
    }

    private UserBigData addList(UserListDTO userListDTO, Product product) {
        // 오늘의 날짜와 userId, prId 기준으로 데이터를 생성

        UserBigData userBigDataBuilder = UserBigData.builder()
                                .userListDTO(userListDTO)
                                .product(product)
                                .ub_clickCount(1)
                                .build();
        log.info(userBigDataBuilder.getProduct());
        UserBigData yes = userBigDataRepository.save(userBigDataBuilder);
        return yes;
    }
}
