package com.demo.rate_limiter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.RedisScript;

public class RateLimiterConfig {
    @Bean
    public RedisScript<Long> rateLimiterScript() {
        return RedisScript.of(
                new ClassPathResource("lua/rate_limiter.lua"),
                Long.class
        );
    }
}
