package com.kream.root.admin.repository.userAccess;

import com.kream.root.admin.domain.UserAccessLog;

import java.util.List;

public interface UserAccessLogRepository {
    void save(UserAccessLog userAccessLog);
    UserAccessLog findOne(Long id);
    List<UserAccessLog> findAll();
    List<UserAccessLog> findByUserId(String userId);
}
