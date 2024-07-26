package com.kream.root.admin.service.userAccess;

import com.kream.root.admin.domain.UserAccessLog;
import com.kream.root.admin.domain.UserAccessLogDTO.GraphDataDTO;
import com.kream.root.admin.domain.UserAccessLogDTO.GraphPointDTO;
import com.kream.root.admin.repository.userAccess.UserAccessLogRepository;
import com.kream.root.admin.repository.userAccess.UserProductInteractionRepository;
import com.kream.root.entity.UserProductInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserAccessLogServiceImpl implements UserAccessLogService{
    @Autowired
    private UserAccessLogRepository userAccessLogRepository;

    @Autowired
    private UserProductInteractionRepository userProductInteractionRepository;

    @Override
    public void logUserAccess(UserAccessLog log, String refererUrl, String userAgent) {
        // 운영체제 및 브라우저 정보 추출
        String os = userAgent.contains("Windows") ? "Windows" :
                userAgent.contains("Mac") ? "MacOS" :
                        userAgent.contains("Linux") ? "Linux" : "Other";
        String browser = userAgent.contains("Chrome") ? "Chrome" :
                userAgent.contains("Firefox") ? "Firefox" :
                        userAgent.contains("Safari") ? "Safari" : "Other";
        String deviceType = userAgent.contains("Mobi") ? "Mobile" : "Desktop";

        log.setRefererUrl(refererUrl);
        log.setUserAgent(userAgent);
        log.setOs(os);
        log.setBrowser(browser);
        log.setDeviceType(deviceType);
        log.setAccessTime(LocalDateTime.now());

        userAccessLogRepository.save(log);
    }
//    그룹화 함수 사용 :Function<UserAccessLog, String> groupByFunction
//            로 함수를 받을 때 UserAccessLog라는
//            객체 내부에서 getOs라는 getter를 사용해서 해당 메소드로 반환되는 값들로 데이터를 그룹화하도록 한다.
    public List<GraphDataDTO> getGraphDataByOs() {
        return generateGraphData(userAccessLogRepository.findAll(), UserAccessLog::getOs);

    }

    public List<GraphDataDTO> getGraphDataByBrowser() {
        return generateGraphData(userAccessLogRepository.findAll(), UserAccessLog::getBrowser);
    }

    public List<GraphDataDTO> getGraphDataByDeviceType() {
        return generateGraphData(userAccessLogRepository.findAll(), UserAccessLog::getDeviceType);
    }

    public List<GraphDataDTO> getGraphDataByRefererUrl() {
        return generateGraphData(userAccessLogRepository.findAll(), this::extractDomainFromRefererUrl);
    }

    private List<GraphDataDTO> generateGraphData(List<UserAccessLog> logs, Function<UserAccessLog, String> groupByFunction) {
        // UserAccessLog::getDeviceType 같이 getter로 가져온 데이터를 컬
        Map<String, List<UserAccessLog>> groupedLogs = logs.stream()
                .collect(Collectors.groupingBy(groupByFunction));

        List<GraphDataDTO> graphData = new ArrayList<>();
        for (Map.Entry<String, List<UserAccessLog>> entry : groupedLogs.entrySet()) {
            GraphDataDTO dataDTO = new GraphDataDTO();
            dataDTO.setId(entry.getKey());
            dataDTO.setData(groupLogsByTime(entry.getValue()));
            graphData.add(dataDTO);
        }
        return graphData;
    }

//    private List<GraphPointDTO> groupLogsByTime(List<UserAccessLog> logs) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
//        Map<String, Long> countByTime = logs.stream()
//                .collect(Collectors.groupingBy(log -> log.getAccessTime().format(formatter), Collectors.counting()));
//
//        return countByTime.entrySet().stream()
//                .map(entry -> new GraphPointDTO(entry.getKey(), entry.getValue().intValue()))
//                .collect(Collectors.toList());
//    }
//private List<GraphPointDTO> groupLogsByTime(List<UserAccessLog> logs) {
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    LocalDateTime startTime = logs.stream().map(UserAccessLog::getAccessTime).min(LocalDateTime::compareTo).orElse(LocalDateTime.now());
//    LocalDateTime endTime = logs.stream().map(UserAccessLog::getAccessTime).max(LocalDateTime::compareTo).orElse(LocalDateTime.now());
//
//    Map<String, Long> countByTime = logs.stream()
//            .collect(Collectors.groupingBy(log -> log.getAccessTime().format(formatter), Collectors.counting()));
//
//    List<GraphPointDTO> graphPoints = new ArrayList<>();
//    for (LocalDateTime time = startTime; !time.isAfter(endTime); time = time.plusHours(1)) {
//        String formattedTime = time.format(formatter);
//        Long count = countByTime.getOrDefault(formattedTime, 0L);
//        graphPoints.add(new GraphPointDTO(formattedTime, count.intValue()));
//    }
//    return graphPoints;
//}
    private List<GraphPointDTO> groupLogsByTime(List<UserAccessLog> logs) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 날짜별로 그룹화하고 합계 계산
        Map<String, Long> countByTime = logs.stream()
                .collect(Collectors.groupingBy(
                        log -> log.getAccessTime().format(formatter),
                        Collectors.counting()
                ));

        // 시작 및 종료 시간 계산
        LocalDateTime startTime = logs.stream()
                .map(UserAccessLog::getAccessTime)
                .min(LocalDateTime::compareTo)
                .orElse(LocalDateTime.now());
        LocalDateTime endTime = logs.stream()
                .map(UserAccessLog::getAccessTime)
                .max(LocalDateTime::compareTo)
                .orElse(LocalDateTime.now());

        // 그래프 데이터 생성
        List<GraphPointDTO> graphPoints = new ArrayList<>();
        for (LocalDateTime time = startTime; !time.isAfter(endTime); time = time.plusDays(1)) {
            String formattedTime = time.format(formatter);
            Long count = countByTime.getOrDefault(formattedTime, 0L);
            graphPoints.add(new GraphPointDTO(formattedTime, count.intValue()));
        }

        return graphPoints;
    }

    private String extractDomainFromRefererUrl(UserAccessLog log) {
        try {
            String refererUrl = log.getRefererUrl();
            if (refererUrl != null) {
                URI uri = new URI(refererUrl);
                String domain = uri.getHost();
                if (domain.contains("naver")) return "naver";
                if (domain.contains("google")) return "google";
                if (domain.contains("daum")) return "daum";
                return "other";
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "unknown";
    }
    public void saveInteraction(String userId, Long productId) {
        UserProductInteraction interaction = new UserProductInteraction(userId, productId);
        userProductInteractionRepository.save(interaction);
    }



}
