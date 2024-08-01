package com.kream.root.main.CSVParser;

import com.kream.root.Detail.repository.UserBigDataRepository;
import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.repository.ProductRepository;
import com.kream.root.entity.OrderItems;
import com.kream.root.entity.Orders;
import com.kream.root.entity.UserBigData;
import com.kream.root.order.repository.OrdersRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class ClickDataService {

    private final ReadLineContext<ClickData> clickDataReadLineContext;

    public ClickDataService(ReadLineContext<ClickData> clickDataReadLineContext) {
        this.clickDataReadLineContext = clickDataReadLineContext;
    }

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    UserListRepository userListRepository;
    @Autowired
    UserBigDataRepository userBigDataRepository;

    @Transactional
    public void insertLargeVolumeData() throws IOException {
        String fileName = "C:/Users/sorae/Fintech/Fintech_last_project/Recommender_system/DataBaseETLProcessing/kream_new_product_v2_recommend_clickUser_database.csv";
        log.info(fileName);
        List<ClickData> clickDataList;


        try {
            clickDataList = clickDataReadLineContext.readByLine(fileName);
            log.info("파싱 완료 " + clickDataList);
            List<UserListDTO> userList = userListRepository.findAll();
            List<UserBigData> clickSaveData = new ArrayList<>();
            int[] cnt = {0};
            clickDataList.forEach(data -> {
                try {


                    LocalDate localDate = data.getUbDate();
                    LocalDateTime start = LocalDateTime.of(localDate, LocalTime.of(0, 0, 0));
                    LocalDateTime end = LocalDateTime.of(localDate, LocalTime.of(23, 59, 59));
                    Product product = productRepository.findById(data.getPrid())
                            .orElseThrow(() -> new RuntimeException("Product not found"));

                    List<UserListDTO> orderUserList = ordersRepository.findByOrderDateBetween(start, end)
                            .stream().flatMap(orders -> orders.getOrderItems().stream()
                                    .filter(prd -> prd.getProduct().getPrid() == product.getPrid())
                                    .map(OrderItems::getOrder).map(Orders::getUser)).toList();

                    log.info("Order User Info : {}", orderUserList);

                    int clickCount = data.getClickCount();
                    log.info("Processing clickCount: " + clickCount);

                    for (UserListDTO orderUser : orderUserList) {
                        UserBigData userBigDataBuilder = UserBigData.builder()
                                .ubDate(localDate)
                                .userListDTO(orderUser)
                                .product(product)
                                .ub_clickCount(1)
                                .build();

                        clickSaveData.add(userBigDataBuilder);
                    }

                    int count = clickCount > orderUserList.size() ? clickCount - orderUserList.size() : 0;
                    if (count > 0) {

                        for (int i = 0; i < count; i++) {
                            UserListDTO user = userList.get((int) (Math.random() * userList.size() - 1));

//                            UserListDTO user = userListRepository.findById(ulid)
//                                    .orElseThrow(() -> new RuntimeException("User not found"));

                            UserBigData userBigDataBuilder = UserBigData.builder()
                                    .ubDate(localDate)
                                    .userListDTO(user)
                                    .product(product)
                                    .ub_clickCount(1)
                                    .build();
                            clickSaveData.add(userBigDataBuilder);
                        }
                    }
                    cnt[0] = cnt[0] + 1;
                    log.info("click data 수 : " + cnt[0]);

                } catch (Exception e) {
                    log.error("Error processing order data: {}", e.getMessage());
                    e.printStackTrace();
                }
            });
            userBigDataRepository.saveAll(clickSaveData);
        } catch (Exception e) {
            log.error("Error reading order data file: {}", e.getMessage());
            e.printStackTrace();
        }

    }
}
