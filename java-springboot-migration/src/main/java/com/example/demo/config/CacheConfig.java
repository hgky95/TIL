package com.example.demo.config;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager() {
        CacheConfiguration userCache = new CacheConfiguration();
        userCache.setName("userCache");
        userCache.setMemoryStoreEvictionPolicy("LRU");
        userCache.setMaxEntriesLocalHeap(1000);
        userCache.setTimeToLiveSeconds(60);

        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(userCache);
        
        return net.sf.ehcache.CacheManager.newInstance(config);
    }

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }
} 