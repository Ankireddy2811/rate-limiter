package com.demo.rate_limiter.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RateLimiterService {

    private final StringRedisTemplate redisTemplate;
    private final RedisScript<Long> script;

    public RateLimiterService(StringRedisTemplate redisTemplate,
                              RedisScript<Long> script) {
        this.redisTemplate = redisTemplate;
        this.script = script;
    }

    public boolean isAllowed(String key) {

        Long result = redisTemplate.execute(
                script,
                Collections.singletonList("rate_limit:" + key),
                "10",   // capacity
                "1",    // refill rate
                String.valueOf(System.currentTimeMillis() / 1000)
        );

        return result != null && result == 1L;
    }
}