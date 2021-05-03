package com.intigral.weatherapi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Objects;

@Slf4j
@EnableCaching
@Configuration
@EnableScheduling
public class CacheConfiguration {

    private final CacheManager cacheManager;

    public CacheConfiguration(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Scheduled(fixedDelay = 1000 * 60)
    public void cacheEvict() {

        cacheManager.getCacheNames()
                .forEach(cacheName -> {
                    Cache cache = cacheManager.getCache(cacheName);
                    if (Objects.nonNull(cache)) {
                        cache.clear();
                    }
                });
        log.info("All the entries from all the caches are evicted.");
    }
}
