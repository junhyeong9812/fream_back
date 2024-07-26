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
import org.springframework.format.datetime.DateFormatter;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
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
public void dataAdd() throws IOException {
    String fileName = "/Users/82104/final_project/database/kream_new_product_v2_recommend_orderUser.csv";
    log.info(fileName);
    List<OrderData> orderDataList;
    try {
        orderDataList = orderDataReadLineContext.readByLine(fileName);
        log.info("파싱 완료 " + orderDataList);

        orderDataList.forEach(data -> {
            try {
                UserListDTO user = userRepository.findById(data.getUser())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                Product product = productRepository.findById(data.getPrId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                SellerProduct sellerProduct = sellerProductRepository.findById(1L)
                        .orElseThrow(() -> new RuntimeException("Seller not found"));

                // Create OrderItems
                OrderItems orderItem = OrderItems.builder()
                        .product(product)
                        .quantity(1)
                        .price(product.getPrice())
                        .build();
                log.info("orderItem : {}", orderItem);

                List<OrderItems> orderItemsList = new ArrayList<>();
                orderItemsList.add(orderItem);

                // Create Orders
                Orders order = Orders.builder()
                        .orderCode(data.getOrderCode())
                        .orderDate(data.getOrderDate())
                        .user(user)
                        .sellerProduct(sellerProduct)
                        .orderItems(orderItemsList)
                        .applyNum("applyNum_example")
                        .bankName("bankName_example")
                        .buyerAddr("buyerAddr_example")
                        .buyerEmail("buyerEmail@example.com")
                        .buyerName("buyerName_example")
                        .buyerPostcode("buyerPostcode_example")
                        .buyerTel("010-1234-5678")
                        .cardName("cardName_example")
                        .cardNumber("1234-5678-9012-3456")
                        .cardQuota(3)
                        .currency("KRW")
                        .customData("customData_example")
                        .impUid("impUid_example")
                        .merchantUid("merchantUid_example")
                        .productName(product.getNameKor())
                        .paidAmount(data.getPriceAmount())
                        .paidAt(System.currentTimeMillis())
                        .payMethod("credit_card")
                        .pgProvider("pgProvider_example")
                        .pgTid("pgTid_example")
                        .pgType("pgType_example")
                        .receiptUrl("http://example.com/receipt")
                        .status("status_example")
                        .success(true)
                        .build();

                orderItemsList.forEach(item -> item.setOrder(order));
                ordersRepository.save(order);
            } catch (Exception e) {
                log.error("Error processing order data: {}", e.getMessage());
                e.printStackTrace();
            }
        });
    } catch (Exception e) {
        log.error("Error reading order data file: {}", e.getMessage());
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
        String date = "2024-07-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime startDate = LocalDateTime.parse(date + " 00:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse(date + " 23:59:59", formatter);

        List<Orders> orderList = ordersRepository.findByOrderDateBetween(startDate, endDate);
        orderList.forEach(data -> {
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
//        List<UserBigData> clickDate= userBigDataRepository.findByUbDate(dateTime);
//        clickDate.forEach(data -> {
//            log.info("click info : {}", data);
//        });
    }

    @Autowired
    StyleRepository styleRepository;

    @Transactional
    @Test
    public void styleDateSelect (){
        String date = "2024-07-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime startDate = LocalDateTime.parse(date + " 00:00:00", formatter);
        LocalDateTime endDate = LocalDateTime.parse(date + " 23:59:59", formatter);
        List<Style> styleDate= styleRepository.findByStyleDateBetween(startDate, endDate);
        styleDate.forEach(data -> {
            log.info("style info : {}", data);
        });
    }

}
