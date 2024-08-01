package com.kream.root.main.CSVParser;

import com.kream.root.Detail.repository.UserBigDataRepository;
import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.repository.ProductRepository;
import com.kream.root.entity.OrderItems;
import com.kream.root.entity.Orders;
import com.kream.root.entity.Style;
import com.kream.root.entity.UserBigData;
import com.kream.root.order.repository.OrdersRepository;
import com.kream.root.style.repository.StyleRepository;
import com.kream.root.style.service.StyleService;
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
public class StyleDataService {
    private final ReadLineContext<StyleData> styleDataReadLineContext;

    public StyleDataService(ReadLineContext<StyleData> styleDataReadLineContext){
        this.styleDataReadLineContext = styleDataReadLineContext;
    }

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    StyleRepository styleRepository;


    public void styleAdd() throws IOException {
        String fileName = "C:/Users/sorae/Fintech/Fintech_last_project/Recommender_system/DataBaseETLProcessing/kream_new_product_v2_recommend_styleUser_database.csv";
        log.info(fileName);
        List<StyleData> styleDataList;
        try {
            styleDataList = styleDataReadLineContext.readByLine(fileName);
            log.info("파싱 완료 : " + styleDataList.size());
            int[] cnt = {0};
            List<Style> styleSaveList = new ArrayList<>();
            styleDataList.forEach(data -> {
                try {
                    log.info("now data : {}", data);
                    LocalDate localDate = data.getStyleDate().toLocalDate();
                    LocalDateTime start = localDate.atTime(0, 0, 0);
                    LocalDateTime end = localDate.atTime(23, 59, 59);
                    Product product = productRepository.findById(data.getPrId())
                            .orElseThrow(() -> new RuntimeException("Product not found"));

                    List<UserListDTO> orderUserList = ordersRepository.findByOrderDateBetween(start, end)
                            .stream().flatMap(orders -> orders.getOrderItems().stream()
                                    .filter(prd -> prd.getProduct().getPrid() == product.getPrid())
                                    .map(OrderItems::getOrder).map(Orders::getUser)).toList();

                    log.info("Order User Info : {}", orderUserList);

                        for(UserListDTO orderUser : orderUserList){
//                            Style styleBuilder = Style.builder()
//                                    .styleDate(start.plusDays(2)) // 2일 뒤에 style 작성
//                                    .user(orderUser)
//                                    .content("style is good!")
//                                    .product(product).build();
                            Style styleBuilder = new Style();
                            styleBuilder.setStyleDate(start.plusDays(2));
                            styleBuilder.setUser(orderUser);
                            styleBuilder.setContent("style is good");
                            styleBuilder.setProduct(product);

                            log.info("styleBuilder : {}", styleBuilder);

//                            styleSaveList.add(styleBuilder);
                        styleRepository.save(styleBuilder);
                        }

                    cnt[0] = cnt[0] + 1;
                    log.info("style data 수 : " + cnt[0]);

                } catch (Exception e) {
                    log.error("Error processing order data: {}", e.getMessage());
                    e.printStackTrace();
                }
            });
//            styleRepository.saveAll(styleSaveList);
        } catch (Exception e) {
            log.error("Error reading order data file: {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
