package com.kream.root.admin.service.dashboard;

import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.order.repository.OrdersRepository;
import com.kream.root.style.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class dashboardServiceImpl implements DashboardService{
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private UserListRepository userListRepository;
    @Autowired
    private StyleRepository styleRepository;

    public Map<String, Long> getOrdersCountByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        long ordersCount = ordersRepository.findByOrderDateBetween(startOfDay, endOfDay).size();
        return Map.of("ordersCount", ordersCount);
    }

    public Map<String, Long> getUsersCountByDate(LocalDate date) {
        long usersCount = userListRepository.findByJoinDateBetween(date, date.plusDays(1)).size();
        return Map.of("usersCount", usersCount);
    }

    public Map<String, Long> getStylesCountByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        long stylesCount = styleRepository.findByStyleDateBetween(startOfDay, endOfDay).size();
        return Map.of("stylesCount", stylesCount);
    }
    public Map<String, Map<String, Long>> getOrdersDailyComparison() {
        LocalDate today = LocalDate.now();
        LocalDate twoDaysAgo = today.minusDays(2);
        LocalDate oneDayAgo = today.minusDays(1);

        Map<String, Long> twoDaysAgoCounts = getOrdersCountByDate(twoDaysAgo);
        Map<String, Long> oneDayAgoCounts = getOrdersCountByDate(oneDayAgo);

        long ordersDifference = oneDayAgoCounts.get("ordersCount") - twoDaysAgoCounts.get("ordersCount");

        Map<String, Long> difference = new HashMap<>();
        difference.put("ordersDifference", ordersDifference);

        Map<String, Map<String, Long>> result = new HashMap<>();
        result.put("twoDaysAgo", twoDaysAgoCounts);
        result.put("oneDayAgo", oneDayAgoCounts);
        result.put("difference", difference);

        return result;
    }

    public Map<String, Map<String, Long>> getUsersDailyComparison() {
        LocalDate today = LocalDate.now();
        LocalDate twoDaysAgo = today.minusDays(2);
        LocalDate oneDayAgo = today.minusDays(1);

        Map<String, Long> twoDaysAgoCounts = getUsersCountByDate(twoDaysAgo);
        Map<String, Long> oneDayAgoCounts = getUsersCountByDate(oneDayAgo);

        long usersDifference = oneDayAgoCounts.get("usersCount") - twoDaysAgoCounts.get("usersCount");

        Map<String, Long> difference = new HashMap<>();
        difference.put("usersDifference", usersDifference);

        Map<String, Map<String, Long>> result = new HashMap<>();
        result.put("twoDaysAgo", twoDaysAgoCounts);
        result.put("oneDayAgo", oneDayAgoCounts);
        result.put("difference", difference);

        return result;
    }

    public Map<String, Map<String, Long>> getStylesDailyComparison() {
        LocalDate today = LocalDate.now();
        LocalDate twoDaysAgo = today.minusDays(2);
        LocalDate oneDayAgo = today.minusDays(1);

        Map<String, Long> twoDaysAgoCounts = getStylesCountByDate(twoDaysAgo);
        Map<String, Long> oneDayAgoCounts = getStylesCountByDate(oneDayAgo);

        long stylesDifference = oneDayAgoCounts.get("stylesCount") - twoDaysAgoCounts.get("stylesCount");

        Map<String, Long> difference = new HashMap<>();
        difference.put("stylesDifference", stylesDifference);

        Map<String, Map<String, Long>> result = new HashMap<>();
        result.put("twoDaysAgo", twoDaysAgoCounts);
        result.put("oneDayAgo", oneDayAgoCounts);
        result.put("difference", difference);

        return result;
    }
    @Override
    public Map<String, Object> getTodayOrderStatistics() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        long newOrdersToday = ordersRepository.countByOrderDateBetween(startOfDay, endOfDay);

        Map<String, Object> stats = new HashMap<>();
        stats.put("newOrdersToday", newOrdersToday);

        return stats;
    }

    @Override
    public Map<String, Object> getTodayUserStatistics() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        long totalUsers = userListRepository.count();
        long newUsersToday = userListRepository.countByJoinDateBetween(today, today.plusDays(1));

        double userGrowthRate = (double) newUsersToday / totalUsers * 100;

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", totalUsers);
        stats.put("newUsersToday", newUsersToday);
        stats.put("userGrowthRate", userGrowthRate);

        return stats;
    }

    @Override
    public Map<String, Object> getTodayStyleStatistics() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        long newStylesToday = styleRepository.countByStyleDateBetween(startOfDay, endOfDay);

        Map<String, Object> stats = new HashMap<>();
        stats.put("newStylesToday", newStylesToday);

        return stats;
    }




}
