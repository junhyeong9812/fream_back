package com.kream.root.main;

import com.kream.root.Detail.repository.UserBigDataRepository;
import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.repository.ProductRepository;
import com.kream.root.admin.repository.SellerProductRepository;
import com.kream.root.entity.*;
import com.kream.root.main.CSVParser.OrderData;
import com.kream.root.main.CSVParser.ReadLineContext;
import com.kream.root.order.repository.OrdersRepository;
import com.kream.root.style.repository.StyleRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
public class orderTest {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserListRepository userRepository;


    @Autowired
    ReadLineContext<OrderData> orderDataReadLineContext;

    @Autowired
    SellerProductRepository sellerProductRepository;

    @Transactional
    @Test
    public void dataAdd() throws IOException{
        String fileName = "/Users/sorae/Fintech/Fintech_last_project/Recommender_system/WebCrawling/kream_new_product_v2_recommend_orderUser.csv";
        log.info(fileName);
        OrderData orderData = new OrderData();
        List<OrderData> orderDataList;
        try {
            orderDataList = orderDataReadLineContext.readByLine(fileName);
            log.info("파싱 완료 " + orderDataList);
            orderDataList.stream()
                    .parallel()
                    .forEach(data -> { //수정중
                        try{
                            UserListDTO user = userRepository.findById(data.getUser()).orElseThrow(() -> new RuntimeException("User not found"));
                            Product product = productRepository.findById(data.getPrId()).orElseThrow(() -> new RuntimeException("Product not found"));
                            SellerProduct sellerProduct = sellerProductRepository.findById(1L).orElseThrow(() -> new RuntimeException("seller not found"));
                        // Create OrderItems
                        OrderItems orderItem = OrderItems.builder()
                                .product(product)
                                .quantity(1)
                                .price(product.getPrice())
                                .build();
                        log.info("orderItem : {}" + orderItem);

                        List<OrderItems> orderItemsList = new ArrayList<>();
                        orderItemsList.add(orderItem);

                            // Create Orders
                        Orders order = Orders.builder()
                                .orderCode(data.getOrderCode())
                                .orderDate(data.getOrderDate())
                                .user(user)
                                .sellerProduct(sellerProduct) // 필요하지 않은 경우 null로 설정
                                .orderItems(orderItemsList)
                                .applyNum("applyNum_example") // Placeholder, replace with actual data
                                .bankName("bankName_example") // Placeholder, replace with actual data
                                .buyerAddr("buyerAddr_example") // Placeholder, replace with actual data
                                .buyerEmail("buyerEmail@example.com") // Placeholder, replace with actual data
                                .buyerName("buyerName_example") // Placeholder, replace with actual data
                                .buyerPostcode("buyerPostcode_example") // Placeholder, replace with actual data
                                .buyerTel("010-1234-5678") // Placeholder, replace with actual data
                                .cardName("cardName_example") // Placeholder, replace with actual data
                                .cardNumber("1234-5678-9012-3456") // Placeholder, replace with actual data
                                .cardQuota(3) // Placeholder, replace with actual data
                                .currency("KRW") // Placeholder, replace with actual data
                                .customData("customData_example") // Placeholder, replace with actual data
                                .impUid("impUid_example") // Placeholder, replace with actual data
                                .merchantUid("merchantUid_example") // Placeholder, replace with actual data
                                .productName(product.getNameKor()) // Placeholder, replace with actual data
                                .paidAmount(data.getPriceAmount())
                                .paidAt(System.currentTimeMillis()) // Placeholder, replace with actual data
                                .payMethod("credit_card") // Placeholder, replace with actual data
                                .pgProvider("pgProvider_example") // Placeholder, replace with actual data
                                .pgTid("pgTid_example") // Placeholder, replace with actual data
                                .pgType("pgType_example") // Placeholder, replace with actual data
                                .receiptUrl("http://example.com/receipt") // Placeholder, replace with actual data
                                .status("status_example") // Placeholder, replace with actual data
                                .success(true) // Placeholder, replace with actual data
                                .build();


                        orderItemsList.forEach(item -> item.setOrder(order));
                        ordersRepository.save(order);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void orderSelect (){
        log.info("order info : {}", ordersRepository.findById(1L));
    }
    @Transactional
    @Test
    public void orderDateSelect (){
        String date = "2024-07-01 00:00:00";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, dateTimeFormatter);
        List<Orders> orderDate= ordersRepository.findByOrderDate(dateTime);
        orderDate.forEach(data -> {
            log.info("order info : {}", data);
        });
    }

    @Autowired
    UserBigDataRepository userBigDataRepository;

    @Transactional
    @Test
    public void clickDateSelect (){
        String date = "2024-07-01 00:00:00";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, dateTimeFormatter);
        List<UserBigData> clickDate= userBigDataRepository.findByUbDate(dateTime);
        clickDate.forEach(data -> {
            log.info("click info : {}", data);
        });
    }

    @Autowired
    StyleRepository styleRepository;

    @Transactional
    @Test
    public void styleDateSelect (){
        String date = "2024-07-01 00:00:00";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, dateTimeFormatter);
        List<Style> styleDate= styleRepository.findByStyleDate(dateTime);
        styleDate.forEach(data -> {
            log.info("style info : {}", data);
        });
    }


}
