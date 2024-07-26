package com.kream.root.admin.service.AdminSign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TokenWhitelistService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String WHITELIST_PREFIX = "whitelist:";

    public void addTokenToWhitelist(String token, Duration duration) {
        redisTemplate.opsForValue().set(WHITELIST_PREFIX + token, "true", duration);
    }

    public boolean isTokenInWhitelist(String token) {
        return redisTemplate.hasKey(WHITELIST_PREFIX + token);
    }
    public void removeTokenFromWhitelist(String token) {
        redisTemplate.delete(WHITELIST_PREFIX + token);
    }
}
