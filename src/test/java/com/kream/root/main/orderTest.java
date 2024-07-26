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
import com.kream.root.order.PaymentInfo;
import com.kream.root.order.repository.DeliveryRepository;
import com.kream.root.order.repository.OrdersRepository;
import com.kream.root.order.service.OrderService;
import com.kream.root.style.repository.StyleRepository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.Rollback;

import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private DeliveryRepository deliveryRepository;

    @Autowired
    private OrderService orderService;


    @Autowired
    ReadLineContext<OrderData> orderDataReadLineContext;

    @Autowired
    SellerProductRepository sellerProductRepository;

    private static final Random random = new Random();

@Transactional
@Rollback(false)
@Test
public void dataAdd() throws IOException {
    String fileName = "C:\\project/DataBaseETLProcessing/kream_new_product_v2_recommend_orderUser_randomized_updated.csv";
    log.info(fileName);
    List<OrderData> orderDataList;
    try {
        orderDataList = orderDataReadLineContext.readByLine(fileName);
        log.info("파싱 완료 " + orderDataList);

        orderDataList.forEach(data -> {
            try {
                int ulid = data.getUser();
                log.info("Processing userId: " + ulid);
                UserListDTO user = userRepository.findById(ulid)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                log.info("Found user: " + user);
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

<<<<<<< HEAD
//    @Test
//    @Transactional
//    public void testCreateDummyOrders() {
//        for (int i = 0; i < 1000; i++) {
//            PaymentInfo paymentInfo = generateDummyPaymentInfo();
//            UserListDTO user = getRandomUser();
//            orderService.createOrder(paymentInfo, user);
//        }
//    }
//    private PaymentInfo generateDummyPaymentInfo() {
//        List<Long> productIds = new ArrayList<>();
//        List<Integer> quantities = new ArrayList<>();
//        for (int i = 0; i < random.nextInt(5) + 1; i++) {
//            productIds.add((long) (random.nextInt(299) + 1));
//            quantities.add( 1);
//        }
//
//        return PaymentInfo.builder()
//                .productIds(productIds)
//                .quantities(quantities)
//                .applyNum(randomString(10))
//                .bankName("Bank " + randomString(5))
//                .buyerAddr("Address " + randomString(10))
//                .buyerEmail(randomString(10) + "@example.com")
//                .buyerName("Buyer " + randomString(5))
//                .buyerPostcode("12345")
//                .buyerTel("010-" + random.nextInt(9000) + 1000 + "-" + random.nextInt(9000) + 1000)
//                .cardName("Card " + randomString(5))
//                .cardNumber(randomString(16))
//                .cardQuota(random.nextInt(12) + 1)
//                .currency("KRW")
//                .customData(null)
//                .impUid(randomString(10))
//                .merchantUid(randomString(10))
//                .productName("Product " + randomString(10))
//                .paidAmount(random.nextDouble() * 1000)
//                .paidAt(System.currentTimeMillis() / 1000)
//                .payMethod("Credit Card")
//                .pgProvider("PG Provider " + randomString(5))
//                .pgTid(randomString(10))
//                .pgType("PG Type " + randomString(5))
//                .receiptUrl("http://example.com/receipt/" + randomString(10))
//                .status("Paid")
//                .success(true)
//                .build();
//    }
//    private UserListDTO getRandomUser() {
//        Integer userId = random.nextInt(500) + 1;
//        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
//    }
//    private String randomString(int length) {
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        StringBuilder result = new StringBuilder();
//        while (length-- > 0) {
//            result.append(characters.charAt(random.nextInt(characters.length())));
//        }
//        return result.toString();
//    }



=======
>>>>>>> 742c961135f8a214312833f60e330e5bf8417fe8
}
