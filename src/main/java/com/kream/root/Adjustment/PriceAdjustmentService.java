package com.kream.root.Adjustment;

import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.repository.ProductRepository;
import com.kream.root.entity.PriceHistory;
import com.kream.root.order.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class PriceAdjustmentService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;
    public void adjustPricesForDateRange(LocalDate startDate, LocalDate endDate) {
        List<Product> products = productRepository.findAll();

        for (Product product : products) {
            adjustPriceForProduct(product, startDate, endDate);
        }
    }

    private void adjustPriceForProduct(Product product, LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            LocalDateTime startOfDay = currentDate.atStartOfDay();
            LocalDateTime endOfDay = currentDate.atTime(LocalTime.MAX);

            Integer totalQuantity = ordersRepository.sumQuantityByProductAndDate(product.getPrid(), startOfDay, endOfDay);
            int totalQuantityValue = (totalQuantity != null) ? totalQuantity : 0;

            // 전날 가격 가져오기
            PriceHistory lastPriceHistory = priceHistoryRepository.findTopByProductAndHistoryDateBeforeOrderByHistoryDateDesc(product, currentDate);
            double lastPrice = (lastPriceHistory != null) ? lastPriceHistory.getNewPrice() : product.getPrice();

            double priceChange = calculatePriceChange(totalQuantityValue, lastPrice);
            double newPrice = lastPrice + priceChange;

            // 기본 가격의 ±30% 범위 내에서 조정
            double maxPrice = product.getPrice() * 1.3;
            double minPrice = product.getPrice() * 0.7;
            newPrice = Math.min(maxPrice, Math.max(minPrice, newPrice));
            // 백의 자리까지 반올림
            newPrice = Math.round(newPrice / 100.0) * 100.0;

            // PriceHistory 엔티티 저장
            PriceHistory priceHistory = PriceHistory.builder()
                    .product(product)
                    .historyDate(currentDate)
                    .priceChange(priceChange)
                    .newPrice(newPrice)
                    .build();

            priceHistoryRepository.save(priceHistory);

            // Product 엔티티의 가격 업데이트
            product.setPrice((int) newPrice);
            productRepository.save(product);

            currentDate = currentDate.plusDays(1);
        }
    }

    private double calculatePriceChange(int totalQuantity, double lastPrice) {
        if (totalQuantity == 0) {
            return -0.03 * lastPrice;
        } else if (totalQuantity >= 5) {
            return 0.06 * lastPrice;
        } else if (totalQuantity >= 2) {
            return 0.03 * lastPrice;
        } else {
            return -0.03 * lastPrice;
        }
    }


//    public void adjustPricesForDateRange(LocalDate startDate, LocalDate endDate) {
//        List<Product> products = productRepository.findAll();
//
//        LocalDate currentDate = startDate;
//        while (!currentDate.isAfter(endDate)) {
//            LocalDateTime startOfDay = currentDate.atStartOfDay();
//            LocalDateTime endOfDay = currentDate.atTime(LocalTime.MAX);
//
//            for (Product product : products) {
//                Integer totalQuantity = ordersRepository.sumQuantityByProductAndDate(product.getPrid(), startOfDay, endOfDay);
//                int totalQuantityValue = (totalQuantity != null) ? totalQuantity : 0;
//
//                // 전날 가격 가져오기
//                PriceHistory lastPriceHistory = priceHistoryRepository.findTopByProductAndHistoryDateBeforeOrderByHistoryDateDesc(product, currentDate);
//                double lastPrice = (lastPriceHistory != null) ? lastPriceHistory.getNewPrice() : product.getPrice();
//
//                double priceChange = calculatePriceChange(totalQuantityValue, lastPrice);
//                double newPrice = lastPrice + priceChange;
//
//                // 기본 가격의 ±30% 범위 내에서 조정
//                double maxPrice = product.getPrice() * 1.3;
//                double minPrice = product.getPrice() * 0.7;
//                newPrice = Math.min(maxPrice, Math.max(minPrice, newPrice));
//
//                // PriceHistory 엔티티 저장
//                PriceHistory priceHistory = PriceHistory.builder()
//                        .product(product)
//                        .historyDate(currentDate)
//                        .priceChange(priceChange)
//                        .newPrice(newPrice)
//                        .build();
//
//                priceHistoryRepository.save(priceHistory);
//
//                // Product 엔티티의 가격 업데이트
//                product.setPrice((int) newPrice);
//                productRepository.save(product);
//            }
//            currentDate = currentDate.plusDays(1);
//        }
//    }
//    public void adjustPricesForDateRange(LocalDate startDate, LocalDate endDate) {
//        List<Product> products = productRepository.findAll();
//
//        LocalDate currentDate = startDate;
//        while (!currentDate.isAfter(endDate)) {
//            LocalDateTime startOfDay = currentDate.atStartOfDay();
//            LocalDateTime endOfDay = currentDate.atTime(LocalTime.MAX);
//
//            for (Product product : products) {
//                int totalQuantity = ordersRepository.sumQuantityByProductAndDate(product.getPrid(), startOfDay, endOfDay);
//
//                // 전날 가격 가져오기
//                PriceHistory lastPriceHistory = priceHistoryRepository.findTopByProductAndDateBeforeOrderByDateDesc(product, currentDate);
//                double lastPrice = (lastPriceHistory != null) ? lastPriceHistory.getNewPrice() : product.getPrice();
//
//                double priceChange = calculatePriceChange(totalQuantity, lastPrice);
//                double newPrice = lastPrice + priceChange;
//
//                // 기본 가격의 ±30% 범위 내에서 조정
//                double maxPrice = product.getPrice() * 1.3;
//                double minPrice = product.getPrice() * 0.7;
//                newPrice = Math.min(maxPrice, Math.max(minPrice, newPrice));
//
//                // PriceHistory 엔티티 저장
//                PriceHistory priceHistory = PriceHistory.builder()
//                        .product(product)
//                        .date(currentDate)
//                        .priceChange(priceChange)
//                        .newPrice(newPrice)
//                        .build();
//
//                priceHistoryRepository.save(priceHistory);
//
//                // Product 엔티티의 가격 업데이트
//                product.setPrice((int) newPrice);
//                productRepository.save(product);
//            }
//            currentDate = currentDate.plusDays(1);
//        }
//    }

//    private double calculatePriceChange(int totalQuantity, double lastPrice) {
//        if (totalQuantity == 0) {
//            return -0.03 * lastPrice;
//        } else if (totalQuantity >= 5) {
//            return 0.06 * lastPrice;
//        } else if (totalQuantity >= 2) {
//            return 0.03 * lastPrice;
//        } else {
//            return -0.03 * lastPrice;
//        }
//    }

//    public void adjustPricesForDateRange(LocalDate startDate, LocalDate endDate) {
//        List<Product> products = productRepository.findAll();
//
//        LocalDate currentDate = startDate;
//        while (!currentDate.isAfter(endDate)) {
//            for (Product product : products) {
//                int totalQuantity = ordersRepository.sumQuantityByProductAndDate(product.getPrid(), currentDate, currentDate);
//
//                // 전날 가격 가져오기
//                PriceHistory lastPriceHistory = priceHistoryRepository.findTopByProductAndDateBeforeOrderByDateDesc(product, currentDate);
//                double lastPrice = (lastPriceHistory != null) ? lastPriceHistory.getNewPrice() : product.getPrice();
//
//                double priceChange = calculatePriceChange(totalQuantity, lastPrice);
//                double newPrice = lastPrice + priceChange;
//
//                // 기본 가격의 ±30% 범위 내에서 조정
//                double maxPrice = product.getPrice() * 1.3;
//                double minPrice = product.getPrice() * 0.7;
//                newPrice = Math.min(maxPrice, Math.max(minPrice, newPrice));
//
//                // PriceHistory 엔티티 저장
//                PriceHistory priceHistory = PriceHistory.builder()
//                        .product(product)
//                        .date(currentDate)
//                        .priceChange(priceChange)
//                        .newPrice(newPrice)
//                        .build();
//
//                priceHistoryRepository.save(priceHistory);
//
//                // Product 엔티티의 가격 업데이트
//                product.setPrice((int) newPrice);
//                productRepository.save(product);
//            }
//            currentDate = currentDate.plusDays(1);
//        }
//    }
//
//    private double calculatePriceChange(int totalQuantity, double lastPrice) {
//        if (totalQuantity == 0) {
//            return -0.03 * lastPrice;
//        } else if (totalQuantity >= 5) {
//            return 0.06 * lastPrice;
//        } else if (totalQuantity >= 2) {
//            return 0.03 * lastPrice;
//        } else {
//            return -0.03 * lastPrice;
//        }
//    }

//    @Scheduled(cron = "0 0 0 * * ?")  // 매일 오전 9시 실행
//    public void adjustPrices() {
//        List<Product> products = productRepository.findAll();
//
//        for (Product product : products) {
//            LocalDate yesterday = LocalDate.now().minusDays(1);
//            int totalQuantity = ordersRepository.sumQuantityByProductAndDate(product.getPrid(), yesterday);
//
//            // 전날 가격 가져오기
//            PriceHistory lastPriceHistory = priceHistoryRepository.findTopByProductAndDateBeforeOrderByDateDesc(product, LocalDate.now());
//            double lastPrice = (lastPriceHistory != null) ? lastPriceHistory.getNewPrice() : product.getPrice();
//
//            double priceChange = calculatePriceChange(totalQuantity, lastPrice);
//            double newPrice = lastPrice + priceChange;
//
//            // 기본 가격의 ±30% 범위 내에서 조정
//            double maxPrice = product.getPrice() * 1.3;
//            double minPrice = product.getPrice() * 0.7;
//            newPrice = Math.min(maxPrice, Math.max(minPrice, newPrice));
//
//            // PriceHistory 엔티티 저장
//            PriceHistory priceHistory = PriceHistory.builder()
//                    .product(product)
//                    .date(LocalDate.now())
//                    .priceChange(priceChange)
//                    .newPrice(newPrice)
//                    .build();
//
//            priceHistoryRepository.save(priceHistory);
//
//            // Product 엔티티의 가격 업데이트
//            product.setPrice((int) newPrice);
//            productRepository.save(product);
//        }
//    }
//
//    private double calculatePriceChange(int totalQuantity, double lastPrice) {
//        if (totalQuantity == 0) {
//            return -0.03 * lastPrice;
//        } else if (totalQuantity >= 5) {
//            return 0.06 * lastPrice;
//        } else if (totalQuantity >= 2) {
//            return 0.03 * lastPrice;
//        } else {
//            return -0.03 * lastPrice;
//        }
//    }

}