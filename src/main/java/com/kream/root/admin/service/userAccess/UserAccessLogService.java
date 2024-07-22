package com.kream.root.admin.service.userAccess;

import com.kream.root.admin.domain.UserAccessLog;
import com.kream.root.admin.domain.UserAccessLogDTO.GraphDataDTO;

import java.util.List;

public interface UserAccessLogService {
    void logUserAccess(UserAccessLog log, String refererUrl, String userAgent);
    List<GraphDataDTO> getGraphDataByOs();
    List<GraphDataDTO> getGraphDataByBrowser();
    List<GraphDataDTO> getGraphDataByDeviceType();
    List<GraphDataDTO> getGraphDataByRefererUrl();
    void saveInteraction(String userId, Long productId);
}
