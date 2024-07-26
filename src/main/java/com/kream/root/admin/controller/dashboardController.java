package com.kream.root.admin.controller;

import com.kream.root.admin.service.dashboard.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class dashboardController {
    @Autowired
    private DashboardService dashboardService;
//    @GetMapping("/orders-daily-comparison")
//    public Map<String, Map<String, Long>> getOrdersDailyComparison() {
//        return dashboardService.getOrdersDailyComparison();
//    }
//
//    @GetMapping("/users-daily-comparison")
//    public Map<String, Map<String, Long>> getUsersDailyComparison() {
//        return dashboardService.getUsersDailyComparison();
//    }
//
//    @GetMapping("/styles-daily-comparison")
//    public Map<String, Map<String, Long>> getStylesDailyComparison() {
//        return dashboardService.getStylesDailyComparison();
//    }
    @GetMapping("/orders-daily-comparison")
    public Map<String, Object> getOrdersDailyComparison() {
        return dashboardService.getTodayOrderStatistics();
    }

    @GetMapping("/users-daily-comparison")
    public Map<String, Object> getUsersDailyComparison() {
        return dashboardService.getTodayUserStatistics();
    }

    @GetMapping("/styles-daily-comparison")
    public Map<String, Object> getStylesDailyComparison() {
        return dashboardService.getTodayStyleStatistics();
    }
}
