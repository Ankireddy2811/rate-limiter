
#  Distributed API Rate Limiter (Token Bucket)

A scalable and distributed rate limiting system built using **Spring Boot, Redis, and Lua scripting**, implementing the **Token Bucket algorithm** to control API traffic efficiently.

---

##  Features

- Token Bucket Rate Limiting (smooth traffic control)
- Redis-based distributed architecture
- Lua scripting for atomic operations
- Centralized request interception using Spring Interceptor
- Handles high concurrency with minimal latency (<5ms overhead)

---

##  Problem Statement

In high-scale systems (1M+ users), uncontrolled API traffic can lead to:

-  Server overload
-  Database bottlenecks
-  Poor user experience

This project solves the problem by **limiting request rate per user/IP** in a distributed and consistent way.

---

## ⚙️ Tech Stack

- Java 17
- Spring Boot
- Redis
- Lua Scripting
- Maven
-------


##  Architecture
Client Request
↓
Spring Boot Application
↓
RateLimitInterceptor
↓
RateLimiterService
↓
Redis (Lua Script Execution)
↓
Allow / Reject Request
