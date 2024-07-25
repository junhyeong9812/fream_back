package com.kream.root.admin.service.dashboard;

import java.time.LocalDate;
import java.util.Map;

public interface DashboardService {
    Map<String, Map<String, Long>> getOrdersDailyComparison();
    Map<String, Map<String, Long>> getUsersDailyComparison();
    Map<String, Map<String, Long>> getStylesDailyComparison();
    Map<String, Object> getTodayOrderStatistics();
    Map<String, Object> getTodayUserStatistics();
    Map<String, Object> getTodayStyleStatistics();

}
