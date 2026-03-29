package com.demo.rate_limiter.utils;

import com.demo.rate_limiter.service.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimiterService service;

    public RateLimitInterceptor(RateLimiterService service) {
        this.service = service;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String key = request.getRemoteAddr();

        if (!service.isAllowed(key)) {
            response.setStatus(429);
            response.getWriter().write("Too many requests");
            return false;
        }

        return true;
    }
}