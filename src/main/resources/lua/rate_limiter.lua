local key = KEYS[1]
local capacity = tonumber(ARGV[1])
local refill_rate = tonumber(ARGV[2])
local current_time = tonumber(ARGV[3])

local data = redis.call("HMGET", key, "tokens", "timestamp")
local stored_tokens = tonumber(data[1])
local last_time = tonumber(data[2])

if stored_tokens == nil or last_time == nil then
    local tokens = capacity - 1
    redis.call("HMSET", key, "tokens", tokens, "timestamp", current_time)
    redis.call("EXPIRE", key, 3600)
    return 1
else
    local delta = math.max(0, current_time - last_time)
    local refill = delta * refill_rate

    local new_tokens = math.min(capacity, stored_tokens + refill)

    if new_tokens >= 1 then
        new_tokens = new_tokens - 1
        redis.call("HMSET", key, "tokens", new_tokens, "timestamp", current_time)
        redis.call("EXPIRE", key, 3600)
        return 1
    else
        return 0
    end
end