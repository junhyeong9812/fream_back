package com.kream.root.main;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.repository.ProductRepository;
import com.kream.root.entity.OrderItems;
import com.kream.root.entity.Orders;
import com.kream.root.order.repository.OrdersRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
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

    @PostConstruct
    public void init() throws IOException {
        readCsvAndInsertIntoDb("C:/Users/sorae/Fintech/Fintech_last_project/Recommender_system/WebCrawling/kream_new_product_v2_recommend_orderUser.csv");
    }

    @Test
    @Transactional
    public void readCsvAndInsertIntoDb(String filePath) throws IOException {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                // Assuming the CSV columns are in the following order:
                // orderCode, orderDate, paidAmount, userId, productId, quantity
                String orderCode = line[0];
                log.info(orderCode);
                Long productId = parseLong(line[2]);
                int ulid = 0;
                if (parseInt(line[4]) != null){
                    ulid = parseInt(line[4]);

                }
                int paidAmount = 0 ;
                if (parseInt(line[3]) != null){
                    paidAmount = parseInt(line[3]);
                }
                LocalDateTime orderDate = parseLocalDateTime(line[1], "yyyy-MM-dd");

                // Fetch User and Product from the database
                UserListDTO user = userRepository.findById(ulid).orElseThrow(() -> new RuntimeException("User not found"));
                Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

                // Create OrderItems
                OrderItems orderItem = OrderItems.builder()
                        .product(product)
                        .quantity(1)
                        .price(product.getPrice())
                        .build();

                List<OrderItems> orderItemsList = new ArrayList<>();
                orderItemsList.add(orderItem);

                // Create Orders
                Orders order = Orders.builder()
                        .orderCode(orderCode)
                        .orderDate(orderDate)
                        .user(user)
                        .sellerProduct(null) // 필요하지 않은 경우 null로 설정
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
                        .productName("productName_example") // Placeholder, replace with actual data
                        .paidAmount(paidAmount)
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
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Long parseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            System.err.println("Invalid Long value: " + value);
            return null; // or throw an exception or return a default value
        }
    }

    private Integer parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            System.err.println("Invalid Integer value: " + value);
            return null; // or throw an exception or return a default value
        }
    }
    private LocalDateTime parseLocalDateTime(String value, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(value, formatter);
        } catch (Exception e) {
            System.err.println("Invalid LocalDateTime value: " + value);
            return null; // or throw an exception or return a default value
        }
    }

}
