package com.cooper.backend.common.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

@Getter
@RequiredArgsConstructor
public enum RedisKeyConfigs {

    PUPPY_DETAIL("puppy_details",
            RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)));

    private final String cacheName;
    private final RedisCacheConfiguration redisConfiguration;

}
