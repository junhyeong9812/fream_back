package com.kream.root.main;

import com.kream.root.Detail.repository.UserBigDataRepository;
import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.repository.ProductRepository;
import com.kream.root.MainAndShop.service.mainService;
import com.kream.root.entity.OrderItems;
import com.kream.root.entity.Orders;
import com.kream.root.entity.Style;
import com.kream.root.entity.UserBigData;
import com.kream.root.main.CSVParser.*;
import com.kream.root.order.repository.OrdersRepository;
import com.kream.root.style.repository.StyleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
public class recommendTest {

    @Autowired
    ClickDataService clickDataService;


    @Test
    @Rollback(true)
    public void userBigDataAdd() throws IOException {
        clickDataService.insertLargeVolumeData();
    }

    @Autowired
    StyleDataService styleDataService;

    @Test
//    @Rollback
    public void styleDataAdd () throws IOException {
        styleDataService.styleAdd();
    }

    @Autowired
    mainService ms;

    @Test
//    @Rollback
    public void recommendDataAdd () throws IOException {
        LocalDate startDate = LocalDate.of(2024, 6, 20);
        LocalDate endDate = LocalDate.of(2024, 7, 19);
        List<LocalDate> dateRange = startDate.datesUntil(endDate.plusDays(1))
                .toList();

        dateRange.forEach(date -> {
            ms.createRecommendTable(date);
        });
    }
}
